package woo;

public interface Observer {
    public String getKey();
    public void update(String key, int price ,String description);
}
