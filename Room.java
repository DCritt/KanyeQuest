import java.util.ArrayList;

public class Room
{
    private String name;
    private String description;
    private ArrayList<String> exits;
    private ArrayList<Item> items;
    private ArrayList<Person> people;

    public Room()
    {
        exits = new ArrayList<>();
        items = new ArrayList<>();
        people = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    //The next three methods (setExits, setItems, and setPeople) take the String from the text file,
    //Parse them to separate them, and adds them to an ArrayList
    public void setExits(String exitUnsplit)
    {
        String[] exitSplit = exitUnsplit.split("%");
        for (String s : exitSplit)
        {
            exits.add(s);
        }
    }

    public void setItems(String itemUnsplit)
    {
        String[] itemSplit = itemUnsplit.split("%");
        for(String s : itemSplit)
        {
            Item item = new Item();
            String[] itemInfo = s.split(":");
            item.setName(itemInfo[0]);
            item.setDescription(itemInfo[1]);
            item.setEdible(itemInfo[2]);
            item.setStrengthEnum(Integer.parseInt(itemInfo[3]));
            items.add(item);
        }
    }

    public void setPeople(String personUnsplit)
    {
        String[] personSplit = personUnsplit.split("%");
        for(String s : personSplit)
        {
            Person person = new Person();
            String[] personInfo = s.split(":");
            person.setName(personInfo[0]);
            person.setDescription(personInfo[1]);
            person.setDialogue(personInfo[2]);
            person.setStrengthEnum(Integer.parseInt(personInfo[3]));
            people.add(person);
        }
    }

    //The next three methods (listExits, listItems, and listPersons) lists the respective item that is called
    //It iterates through each list and returns a string of items
    public String listExits()
    {
        String exitList = "\nExits: \n";
        for (String s: exits)
        {
            exitList += s + "\n";
        }
        return exitList;
    }

    public String listItems()
    {
        String itemList = "\nItems: \n";
        for (Item s : items)
        {
            itemList += s.getName() + "\n";
        }

        return itemList;
    }

    public String listPersons()
    {
        String personList = "\nPeople: \n";
        for (Person s : people)
        {
            personList += s.getName() + "\n";
        }

        return personList;
    }

    public String toString()
    {
        return getName() + ": " + getDescription();
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public ArrayList<Person> getPeople()
    {
        return people;
    }

    public void removeItem(Item item)
    {
        if (items.contains(item))
        {
            items.remove(item);
        }
        else
        {
            System.out.println("This person is not in this room. They have been moved or obstructed.");
        }
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

}

