package woo;

import java.util.*;
import java.io.*;
import woo.exceptions.*;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;

  /**Clients */
  private Map<String,Client> clients = new TreeMap<String,Client>();

  /**Suppliers */
  private Map<String,Supplier> suppliers = new TreeMap<String,Supplier>();

  /**Products */
  private Map<String,Product> products = new TreeMap<String,Product>();

  /**Transactions*/
  private Map<Integer,Transaction> transactions = new TreeMap<Integer,Transaction>();

  /**Date */
  private int date = 0;

  /**Transaction key*/
  private int transactionKey = 0;

  /**
   * Store debt to the suppliers (negative value).
   */
  private int orderDebt = 0;

  /**
   * Available Balance
   * Difference between paid sales and orders.
   */
  private double availableBalance = 0;

  Store(){}

  public Client getClient(String clientKey){
    return this.clients.get(clientKey.toLowerCase());
  }

  public void addClient(Client client){
    this.clients.put(client.getKey().toLowerCase(),client);
  }

  public Supplier getSupplier(String supplierKey){
    return this.suppliers.get(supplierKey.toLowerCase());
  }

  public void addSupplier(Supplier supplier){
    this.suppliers.put(supplier.getKey().toLowerCase(),supplier);
  }

  public Product getProduct(String productKey){
    return this.products.get(productKey.toLowerCase());
  }

  public void addProduct(Product product){
    this.products.put(product.getKey().toLowerCase(),product);
  }

  public Transaction getTransaction(int transactionKey){
    return this.transactions.get(transactionKey);
  }

  public void addTransaction(Transaction transaction){
    this.transactions.put(transaction.getKey(),transaction);
  }

/*--------------------------------------------------------------------------BALANCE-------------------------------------------------------------------------------------------*/

  public int getAvailableBalance(){
    return (int)Math.round(this.availableBalance);
  }

  public int getSalesTotal(){
    int total = 0;
    for(Client client:this.clients.values()){
      for(Sale sale:client.getSales()){
        if(sale.isPaid())
          total += sale.getFinalCost();
        else
          total += sale.currentCost(this.date);
      }
    }
    return total;
  }

/** Difference between sales (with discounts/penalties) and orders. */
  public int getAccountingBalance() {
    int accountingBalance = this.orderDebt;
    accountingBalance += getSalesTotal();

    return accountingBalance;
  }

  /*---------------------------------------------------------------------------DATE---------------------------------------------------------------------------------------------*/

  public int getDate(){
    return this.date;
  }

  public void advanceDate(int days) throws IncorrectDateException{
    if(days < 0)
      throw new IncorrectDateException(days);
    this.date += days;
  }

/*---------------------------------------------------------------------------PRODUCT---------------------------------------------------------------------------------------------*/
  
  public void setAllClientsAsObservers(Subject subject){
    for(Client client: this.clients.values()){
      subject.registerObserver(client.getKey(),client);
    }
  }

  public boolean toggleProductNotification(String clientKey,String productKey) throws NonExistentProductKeyException,NonExistentClientKeyException{
    if (!this.products.containsKey(productKey.toLowerCase()))
      throw new NonExistentProductKeyException(productKey);

    if (!this.clients.containsKey(clientKey.toLowerCase()))
      throw new NonExistentClientKeyException(clientKey);

    Product product = getProduct(productKey);
    Client client = getClient(clientKey);

    if(product.registerObserver(client.getKey(),client) == false){
      product.removeObserver(client.getKey());
      return false;
    }
    return true;
  }

  public void registerProductBox(String productKey, String serviceType, String supplierKey, int price, int criticalValue, int stock) throws SimilarProductKeyException,NonExistentSupplierKeyException,NonExistentServiceTypeException{
    if (this.products.containsKey(productKey.toLowerCase()))
      throw new SimilarProductKeyException(productKey);

    if(!this.suppliers.containsKey(supplierKey.toLowerCase()))
      throw new NonExistentSupplierKeyException(supplierKey);

    if(!serviceType.equals("NORMAL") && !serviceType.equals("AIR") && !serviceType.equals("EXPRESS") && !serviceType.equals("PERSONAL"))
      throw new NonExistentServiceTypeException(serviceType);

    Box box = new Box(productKey, serviceType, getSupplier(supplierKey), price, criticalValue, stock);
    addProduct(box);

    setAllClientsAsObservers(box);
  }

  public void registerProductContainer(String productKey, String serviceType, String serviceLevel, String supplierKey, int price, int criticalValue, int stock) throws SimilarProductKeyException,NonExistentSupplierKeyException,NonExistentServiceTypeException,NonExistentServiceLevelException{
    if (this.products.containsKey(productKey.toLowerCase()))
      throw new SimilarProductKeyException(productKey);

    if(!this.suppliers.containsKey(supplierKey.toLowerCase()))
      throw new NonExistentSupplierKeyException(supplierKey);

    if(!serviceType.equals("NORMAL") && !serviceType.equals("AIR") && !serviceType.equals("EXPRESS") && !serviceType.equals("PERSONAL"))
      throw new NonExistentServiceTypeException(serviceType);

    if(!serviceLevel.equals("B4") && !serviceLevel.equals("C4") && !serviceLevel.equals("C5") && !serviceLevel.equals("DL"))
      throw new NonExistentServiceLevelException(serviceLevel);

    Container container = new Container(productKey,serviceType,serviceLevel,getSupplier(supplierKey),price,criticalValue,stock);
    addProduct(container);

    setAllClientsAsObservers(container);
  }

  public void registerProductBook(String productKey, String title, String author, String ISBN, String supplierKey, int price, int criticalValue, int stock) throws SimilarProductKeyException,NonExistentSupplierKeyException{
    if (this.products.containsKey(productKey.toLowerCase()))
      throw new SimilarProductKeyException(productKey);

    if(!this.suppliers.containsKey(supplierKey.toLowerCase()))
      throw new NonExistentSupplierKeyException(supplierKey);

    Book book = new Book(productKey, title, author, ISBN, getSupplier(supplierKey), price, criticalValue, stock);
    addProduct(book);

    setAllClientsAsObservers(book);
  }

  public void changeProductPrice(String productKey,int price) throws NonExistentProductKeyException{
    if (!this.products.containsKey(productKey.toLowerCase()))
      throw new NonExistentProductKeyException(productKey);

    if(price < 0)
      return;

    Product product = getProduct(productKey);
    int oldPrice = product.getPrice();
    product.setPrice(price);

    if (price < oldPrice){
      product.notifyObservers(product.getKey(),product.getPrice(),"BARGAIN");
    }
  }
  
  /**
   * Return all the products as an unmodifiable collection.
   *
   * @return a collection with all the products
   */
  public Collection<Product> showAllProducts(){
    return Collections.unmodifiableCollection(this.products.values());
  }

/*---------------------------------------------------------------------------CLIENT---------------------------------------------------------------------------------------------*/

  /**
   * Creates and registers a client.
   *
   * @param clientKey
   *      a client's key
   * @param name
   *      a client's name
   * @param address
   *      a client's address
   * @throws SimilarClientKeyException
   */
  public void registerClient(String clientKey, String name, String address) throws SimilarClientKeyException{
    if(this.clients.containsKey(clientKey.toLowerCase()))
      throw new SimilarClientKeyException(clientKey);

    Client client = new Client(clientKey,name,address);
    addClient(client);

    for(Product product : this.products.values())
      product.registerObserver(client.getKey(),client);
  }

  /**
   * Displays a client.
   *
   * @param clientKey
   *      a client's key
   * @return a client
   * @throws NonExistentClientKeyException
   */
  public String showClient(String clientKey) throws NonExistentClientKeyException{
    if(this.clients.containsKey(clientKey.toLowerCase()) != true)
      throw new NonExistentClientKeyException(clientKey);

    return getClient(clientKey).toString();
  }

  public Collection<Notification> showClientNotifications(String clientKey){
    //doesn't verify key because it's only called after showClient
    ArrayList<Notification> notifications = new ArrayList<Notification>(getClient(clientKey).getNotifications());
    getClient(clientKey).resetNotifications();
    return Collections.unmodifiableCollection(notifications);
  }

  public Collection<Client> showAllClients(){
    return Collections.unmodifiableCollection(this.clients.values());
  }

  public Collection<Sale> showClientTransactions(String clientKey) throws NonExistentClientKeyException{
    if(!this.clients.containsKey(clientKey.toLowerCase()))
      throw new NonExistentClientKeyException(clientKey);

    Client client = getClient(clientKey);
    return Collections.unmodifiableCollection(client.getSales());
  }

/*---------------------------------------------------------------------------SUPPLIER---------------------------------------------------------------------------------------------*/

public void registerSupplier(String supplierKey, String name, String address) throws SimilarSupplierKeyException{
  if(this.suppliers.containsKey(supplierKey.toLowerCase()))
    throw new SimilarSupplierKeyException(supplierKey);

  addSupplier(new Supplier(supplierKey, name, address));
}

public Collection<Supplier> showSuppliers() {
  return Collections.unmodifiableCollection(this.suppliers.values()); 
}

public Collection<Transaction> showSupplierTransaction(String supplierKey) throws NonExistentSupplierKeyException{
  if (!this.suppliers.containsKey(supplierKey.toLowerCase()))
    throw new NonExistentSupplierKeyException(supplierKey);

  return Collections.unmodifiableCollection(getSupplier(supplierKey).getOrders());
}

public boolean toggleTransactions(String supplierKey) throws NonExistentSupplierKeyException{
  if (!this.suppliers.containsKey(supplierKey.toLowerCase()))
    throw new NonExistentSupplierKeyException(supplierKey);

  Supplier supplier = getSupplier(supplierKey);

  supplier.setState(!supplier.getState());
  
  return supplier.getState();
}

/*-------------------------------------------------------------------------TRANSACTION------------------------------------------------------------------------------------------*/

  /**
   * @param transactionKey
   * the transaction key.
   * @return a transaction.
   */
  public String showTransaction(int transactionKey) throws NonExistentTransactionKeyException{
    if (!this.transactions.containsKey(transactionKey))
      throw new NonExistentTransactionKeyException(transactionKey);

    Transaction transaction = getTransaction(transactionKey);
    transaction.update(date);

    return getTransaction(transactionKey).toString();
  }

  public void registerOrderTransaction(String supplierKey, ArrayList<String> productKeys, ArrayList<Integer> amounts) throws NonExistentSupplierKeyException,ForbiddenSupplierException,NonExistentProductKeyException,IncorrectSupplierException{
    if(!this.suppliers.containsKey(supplierKey.toLowerCase()))
      throw new NonExistentSupplierKeyException(supplierKey);

    if(getSupplier(supplierKey).getState() == false)
      throw new ForbiddenSupplierException(supplierKey);

    //Verifies before doing any changes to the store
    for(int i = 0;i<productKeys.size();i++){
      if(!this.products.containsKey(productKeys.get(i).toLowerCase()))
        throw new NonExistentProductKeyException(productKeys.get(i));

      Product product = getProduct(productKeys.get(i));

      if(!product.getSupplier().getKey().toLowerCase().equals(supplierKey.toLowerCase()))
        throw new IncorrectSupplierException(supplierKey,productKeys.get(i));
    }

    ArrayList<TransactionProduct> tProducts = new ArrayList<TransactionProduct>();

    for(int i = 0;i<productKeys.size();i++){
      Product product = getProduct(productKeys.get(i));

      int oldStock = product.getStock();
      product.setStock(product.getStock() + amounts.get(i));

      if(oldStock == 0 && product.getStock() != 0)
        product.notifyObservers(product.getKey(),product.getPrice(),"NEW");

      TransactionProduct tProduct = new TransactionProduct(getProduct(productKeys.get(i)),amounts.get(i));
      tProducts.add(tProduct);
    }

    Supplier supplier = getSupplier(supplierKey);
    Order order = new Order(transactionKey++,supplier,tProducts,this.getDate());

    orderDebt -= order.getBaseCost();
    availableBalance -= order.getBaseCost();
    
    addTransaction(order);
    supplier.addOrder(order);
  }

  public void registerSaleTransaction(String clientKey, int deadline, String productKey, int amount) throws NonExistentClientKeyException, NonExistentProductKeyException,InsufficientProductException{
    if(!this.clients.containsKey(clientKey.toLowerCase()))
      throw new NonExistentClientKeyException(clientKey);

    if(!this.products.containsKey(productKey.toLowerCase()))
      throw new NonExistentProductKeyException(productKey);

    Product product = getProduct(productKey);

    if(product.getStock() < amount)
      throw new InsufficientProductException(productKey, amount, product.getStock());

    product.setStock(product.getStock() - amount);

    Client client = getClient(clientKey);
    Sale sale = new Sale(transactionKey++, client, deadline, product, amount);

    client.addSpentValue(sale.getBaseCost());

    addTransaction(sale);
    client.addSale(sale);
  }

  public void pay(int transactionKey) throws NonExistentTransactionKeyException{
    if(!this.transactions.containsKey(transactionKey))
      throw new NonExistentTransactionKeyException(transactionKey);

    Transaction transaction = getTransaction(transactionKey);
    this.availableBalance += transaction.pay(this.date);
  }

/*---------------------------------------------------------------------------LOOKUPS---------------------------------------------------------------------------------------------*/

  public ArrayList<Product> LookupProductsUnderTopPrice(int price){
    ProductLookupStrategy strategy = new PriceUnderStrategy(price);
    return searchProducts(strategy);
  }

  public ArrayList<Transaction> LookupPaymentsByClient(String clientKey) throws NonExistentClientKeyException{
    if(!this.clients.containsKey(clientKey.toLowerCase()))
      throw new NonExistentClientKeyException(clientKey);

    Client client = getClient(clientKey);
    TransactionLookupStrategy strategy = new ClientTransactionPaidStrategy(client);
    return searchTransactions(strategy);
  }

  public ArrayList<Transaction> searchTransactions(TransactionLookupStrategy strategy){
    ArrayList<Transaction> result = new ArrayList<Transaction>();
    for(Transaction transaction:this.transactions.values()){
      if(strategy.ok(transaction))
        result.add(transaction);
    }
    return result;
  }

  public ArrayList<Product> searchProducts(ProductLookupStrategy strategy){
    ArrayList<Product> result = new ArrayList<Product>();
    for(Product product:this.products.values()){
      if(strategy.ok(product))
        result.add(product);
    }
    return result;
  }


/*---------------------------------------------------------------------------IMPORT---------------------------------------------------------------------------------------------*/

  void importFile(String txtfile) throws IOException, BadEntryException, SimilarClientKeyException, SimilarSupplierKeyException, SimilarProductKeyException,NonExistentSupplierKeyException,NonExistentServiceTypeException,NonExistentServiceLevelException{
    BufferedReader reader = new BufferedReader(new FileReader(txtfile));
    String line;
    
    while((line = reader.readLine()) != null){
      String[] fields = line.split("\\|");
      registerFromFields(fields);
    }
    
    reader.close();
  }

  void registerFromFields(String[] fields) throws BadEntryException, SimilarClientKeyException, SimilarSupplierKeyException, SimilarProductKeyException,NonExistentSupplierKeyException,NonExistentServiceTypeException,NonExistentServiceLevelException{

    switch (fields[0]) {
      case "CLIENT":
        registerClient(fields[1], fields[2], fields[3]);
        break;
      case "SUPPLIER":
        registerSupplier(fields[1], fields[2], fields[3]);
        break;
      case "BOX":
        registerProductBox(fields[1], fields[2], fields[3], Integer.parseInt(fields[4]),  Integer.parseInt(fields[5]),Integer.parseInt(fields[6]));
        break;
      case "CONTAINER":
        registerProductContainer(fields[1], fields[2], fields[3], fields[4], Integer.parseInt(fields[5]), Integer.parseInt(fields[6]), Integer.parseInt(fields[7]));
        break;
      case "BOOK":
        registerProductBook(fields[1], fields[2], fields[3], fields[4], fields[5], Integer.parseInt(fields[6]), Integer.parseInt(fields[7]), Integer.parseInt(fields[8]));
        break;
      default:
        throw new BadEntryException(fields[0]);
    }
  }

}