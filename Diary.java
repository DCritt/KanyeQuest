import java.util.ArrayList;
import java.util.Scanner;

public class Diary implements Openable
{

    private int capacity;
    private ArrayList<String> entries = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();

    //this is a kind of parallel ArrayList that will help when the win condition is tested
    private ArrayList<Integer> strengths = new ArrayList<>();

    //Openable method that just prints all of the entries that player has recorded
    public String display()
    {
        String output = new String();
        for(String e : entries)
        {
            output += e +"\n";
        }
        return output;
    }

    //this method exists for the sole purpose of showing the player what to type when removing an entry
    public String displayNames()
    {
        String output = new String();
        for(String e : names)
        {
            output += e +"\n";
        }
        return output;
    }

// another Openable method that adds a string to the entries ArrayList
    public void add(Object o)
    {
        //here the 8 represents the maximum number of items the player can hold at a time.
        //this could be a variable to adjust difficulty, but we just decided to hard-code it.
        if(capacity >= 8)
        {
            overflow();
        }

        //make sure that you actually fed the method a string
        //this should technically be avoidable with good code, but it's helpful for debugging
        try
        {
            // this could technically be a HashMap, but the architecture that was already in place made two parallel
            // ArrayLists the safer option as far as breaking existing code goes.
            entries.add(((Person) o).getDialogue());
            names.add(((Person) o).getName());
            // Increment the capacity variable to represent the number of objects being carried.
            capacity++;
        }
        catch (Exception n)
        {
            //NOT A STRING REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
            System.out.println(n);
        }

    }

    //Openable method. removes strings from both entries and names depending on the Person object that it is fed.
    //technically could generate an exception by not giving it a Person, but clean code can avoid that.
    public void remove(Object o)
    {
        entries.remove(((Person) o).getDialogue());
        names.remove(((Person) o).getName());
        System.out.println("Entry removed!");
    }

    //called when you try to add another entry when the diary is already full
    private void overflow()
    {
        System.out.println("Your Diary is full! Please enter the name of the person whose entry" +
                " you would like to remove.\n" + displayNames());

        Scanner s = new Scanner(System.in);
        String temp = s.nextLine();
        boolean b = true;
        for(String e : names)
        {
            if (temp.equalsIgnoreCase(e))
            {
                b=false;
            }
        }

        if(b)
        {
            System.out.println("That person is not in the list! please try again.");
            // could technically lead to a stack overflow with enough patience,
            // so with more time would be reworked to utilize a while loop instead
            overflow();
            // return statement so that when the stack comes back down the rest of
            // the code in this method isn't implemented
            return;
        }

        //remove the appropriate strings from both the entries and names ArrayLists
        entries.remove(names.indexOf(temp));
        names.remove(temp);
        capacity = 7;

    }


    //getters
    public ArrayList<String> getEntries()
    {
        return entries;
    }

    public ArrayList<Integer> getStrengths()
    {
        return strengths;
    }
}
