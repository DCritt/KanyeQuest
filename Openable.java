public interface Openable
{
    //This interface exists to abstract the diary and inventory classes
    public String display();
    public void add(Object o);
    public void remove(Object o);
}
