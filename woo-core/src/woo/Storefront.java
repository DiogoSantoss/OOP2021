package woo;

import java.io.*;
import woo.exceptions.*;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Storefront: facade for the core classes.
 */
public class Storefront {

  /** Current filename. */
  private String _filename = "";

  /** The actual store. */
  private Store _store = new Store();

  public Storefront(){}

/*---------------------------------------------------------------------------STORE---------------------------------------------------------------------------------------------*/

  public int getAvailableBalance(){
    return _store.getAvailableBalance();
  }

  public int getAccountingBalance(){
    return _store.getAccountingBalance();
  }

/*---------------------------------------------------------------------------DATE---------------------------------------------------------------------------------------------*/

  /**
   * @return date
   */
  public int displayDate(){
    return _store.getDate();
  }

  /**
   * @param days
   * @throws IncorrectDateException
   */
  public void advanceDate(int days) throws IncorrectDateException{
    _store.advanceDate(days);
  }

/*---------------------------------------------------------------------------PRODUCT---------------------------------------------------------------------------------------------*/

  public void registerProductBox(String productKey, String serviceType, String supplierKey, int price, int criticalValue) throws SimilarProductKeyException,NonExistentSupplierKeyException,NonExistentServiceTypeException{
    _store.registerProductBox(productKey, serviceType, supplierKey, price, criticalValue, 0);
  }

  public void registerProductContainer(String productKey, String serviceType, String serviceLevel, String supplierKey, int price, int criticalValue) throws SimilarProductKeyException,NonExistentSupplierKeyException,NonExistentServiceTypeException,NonExistentServiceLevelException{
    _store.registerProductContainer(productKey, serviceType, serviceLevel, supplierKey, price, criticalValue, 0);
  }

  public void registerProductBook(String productKey, String title, String author, String ISBN, String supplierKey, int price, int criticalValue) throws SimilarProductKeyException,NonExistentSupplierKeyException{
    _store.registerProductBook(productKey, title, author, ISBN, supplierKey, price, criticalValue, 0);
  }

  public void changeProductPrice(String productKey,int price) throws NonExistentProductKeyException{
    _store.changeProductPrice(productKey,price);
  }

  /**
   * @return a collection with all the products
   */
  public Collection<Product> showAllProducts(){
    return _store.showAllProducts();
  }

/*---------------------------------------------------------------------------CLIENT---------------------------------------------------------------------------------------------*/

  public void registerClient(String clientKey, String name, String address) throws SimilarClientKeyException{
    _store.registerClient(clientKey,name,address);
  }

  public boolean toggleProductNotification(String clientKey,String productKey) throws NonExistentProductKeyException,NonExistentClientKeyException{
    return _store.toggleProductNotification(clientKey, productKey);
  }
  /**
   * @param clientKey
   * @throws NonExistentClientKeyException
   */
  public String showClient(String clientKey) throws NonExistentClientKeyException{
    return _store.showClient(clientKey);
  }

  public Collection<Notification> showClientNotifications(String clientKey){
    return _store.showClientNotifications(clientKey);
  }

  /**
   * @return a collection with all the clients
   */
  public Collection<Client> showAllClients(){
    return _store.showAllClients();
  }

  public Collection<Sale> showClientTransactions(String clientKey) throws NonExistentClientKeyException{
    return _store.showClientTransactions(clientKey);
  }

/*---------------------------------------------------------------------------SUPPLIER---------------------------------------------------------------------------------------------*/


public void registerSupplier(String supplierKey, String name, String address) throws SimilarSupplierKeyException{
  _store.registerSupplier(supplierKey, name, address);
}

/**
 * Calls the method return all the suppliers as an unmodifiable collection.
 *
 * @return a collection with all the suppliers
 */
public Collection<Supplier> showSuppliers(){
  return _store.showSuppliers();
}

public Collection<Transaction> showSupplierTransaction(String supplierKey) throws NonExistentSupplierKeyException{
  return _store.showSupplierTransaction(supplierKey);
}

public boolean toggleTransactions(String supplierKey) throws NonExistentSupplierKeyException{
  return _store.toggleTransactions(supplierKey);
}

/*--------------------------------------------------------------------------TRANSACTION--------------------------------------------------------------------------------------------*/

  public String showTransaction(int transactionKey) throws NonExistentTransactionKeyException{
    return _store.showTransaction(transactionKey);
  }

  public void registerOrderTransaction(String supplierKey,ArrayList<String> productKeys,ArrayList<Integer> amounts) throws NonExistentSupplierKeyException,ForbiddenSupplierException,NonExistentProductKeyException,IncorrectSupplierException{
    _store.registerOrderTransaction(supplierKey,productKeys,amounts);
  }

  public void registerSaleTransaction(String clientKey, int deadline, String productKey, int amount) throws NonExistentClientKeyException, NonExistentProductKeyException,InsufficientProductException{
    _store.registerSaleTransaction(clientKey, deadline, productKey, amount);
  }

  public void pay(int transactionKey) throws NonExistentTransactionKeyException{
    _store.pay(transactionKey);
  }

/*---------------------------------------------------------------------------LOOKUPS---------------------------------------------------------------------------------------------*/

  public ArrayList<Product> LookupProductsUnderTopPrice(int price){
    return _store.LookupProductsUnderTopPrice(price);
  }

  public ArrayList<Transaction> LookupPaymentsByClient(String clientKey) throws NonExistentClientKeyException{
    return _store.LookupPaymentsByClient(clientKey);
  }

/*---------------------------------------------------------------------------SAVE/LOAD---------------------------------------------------------------------------------------------*/

  /**
   * @return filename
   */
  public String getFileName(){
    return this._filename;
  }

  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    if(_filename.equals(""))
      throw new MissingFileAssociationException();
    
    ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
    out.writeObject(_store);
    out.close();
  }

  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  public void load(String filename) throws UnavailableFileException, FileNotFoundException, IOException, ClassNotFoundException {
    _filename = filename;
    ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
    _store = (Store) in.readObject();
    in.close();
  }

/*---------------------------------------------------------------------------IMPORT---------------------------------------------------------------------------------------------*/

  public void importFile(String textfile) throws ImportFileException{
    try {
      _store.importFile(textfile);
    } catch (IOException | BadEntryException | SimilarClientKeyException | SimilarSupplierKeyException | SimilarProductKeyException | NonExistentSupplierKeyException | NonExistentServiceTypeException | NonExistentServiceLevelException e /* FIXME maybe other exceptions */) {
      throw new ImportFileException(textfile);
    }
  }
}