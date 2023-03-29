import java.util.ArrayList;
import java.util.Scanner;

//This class is fairly similar to the Diary class, with a few exceptions
public class Inventory implements Openable
{

    private int capacity;
    private Map myMap;
    private ArrayList<Item> elements = new ArrayList<>();

    //we'll need this map reference later for when we add items to the inventory
    public Inventory(Map m)
    {
        myMap = m;
    }

    //Openable method that just prints out everything that the player is currently carrying
    public String display()
    {
        String output = "";
        for(Item e: elements)
        {
            output += e.getName() + "\n";
        }
        return output;
    }

// another Openable method that just adds a new object to the inventory
    public void add(Object o)
    {
        //here the 6 represents the maximum number of items the player can hold at a time.
        //this could be a variable to adjust difficulty, but we just decided to hard-code it.
        if(capacity >= 6)
        {
            overflow();
        }

        // casting from an Object to an Item, so just make sure that it's actually an Item
        //the potential exception is technically avoidable with good code, but it's helpful for debugging
        try
        {
            elements.add((Item) o);

            // this line uses that map reference we stored earlier to get the room that the player is currently in,
            // and remove the relevant Item from its list of Items
            myMap.getRoom(myMap.getCurrentRoom()).removeItem((Item) o);
        }
        catch (Exception n)
        {
            //YOU DIDN'T GIVE ME AN ITEM OBJECT REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
            System.out.println(n);
        }

    }

    //a final Openable method that removes an item from a given name in the elements ArrayList

    public void remove(Object o)
    {
        elements.remove((Item) o);
        System.out.println("Item removed!");
    }

    //this is called when your inventory is full and you try to pick up another item,
    //forcing the player to drop one
    private void overflow()
    {
        System.out.println("Your Inventory is full! Please enter the name of item you would like to remove.\n" + display());

        Scanner s = new Scanner(System.in);
        String temp = s.nextLine();
        boolean b = true;
        for(Item i : elements)
        {
            if (temp.equalsIgnoreCase(i.getName()))
            {
                //removing the item and setting the capacity to the max - 1, because add() will increment capacity again
                elements.remove(i);
                capacity = 5;
                return;

            }
        }
        System.out.println("That item is not in the list! please try again.");
        //technically this should probably be a while loop instead of recursion, but time was pressing.
        //given enough time and patience this could theoretically generate a stack overflow,
        // but is unlikely to be used by someone willing to do so
        overflow();

    }

    //getter and setter
    public ArrayList<Item> getElements()
    {
        return elements;
    }

    public void setMyMap(Map myMap)
    {
        this.myMap = myMap;
    }
}
