import java.util.ArrayList;
import java.util.Scanner;

//The purpose of this class is to unclutter the driver and provide a place for user input validation
public class InputLogic
{
    //Create an inventory and put in the map: this is needed to load the map
    //Create a new diary to instantiate it
    private Map adventureMap;
    private Diary diary = new Diary();
    private Inventory inventory = new Inventory(adventureMap);

    InputLogic(Map map)
    {
        this.adventureMap = map;
        inventory.setMyMap(adventureMap);
    }

    //Start the game by starting the rooms off in the foyer. The player will then move around the mansion (you have to start somewhere)
    public void start()
    {
        displayGameIntro();
        System.out.println("\n" + adventureMap.getRoom("foyer").toString());
        adventureMap.setCurrentRoom("foyer");
    }
    public void input() throws CommandException
    {
        //Create a scanner for user input and a variable to start the for loop
        Scanner kb = new Scanner(System.in);
        boolean bool = true;
        while (bool)
        {
            //Ask for an input, set it to lowercase
            System.out.println("\nPlease input a command");
            String command = kb.nextLine().toLowerCase();
            //this switch statement uses commands from map and displays them when the command is equal to the word
            //Note that the try block is to see if any of the code does not match the expressions that are there
            try
            {
                switch (command)
                {
                    case "move" -> adventureMap.move();

                    //List the people in the current room. This adventureMap.getRoom(adventureMap.getCurrentRoom()) is repeated a lot to get the current room
                    case "display people" -> System.out.println(adventureMap.getRoom(adventureMap.getCurrentRoom()).listPersons());

                    //List the items in the current room.
                    case "display items" -> System.out.println(adventureMap.getRoom(adventureMap.getCurrentRoom()).listItems());

                    case "help" -> commandhelp();

                    //Get the info for a specific item
                    case "get item info" -> {
                        itemInfo(adventureMap.getRoom(adventureMap.getCurrentRoom()), kb);
                    }

                    //Get the info for a specific person
                    case "get person info" -> {
                        peopleInfo(adventureMap.getRoom(adventureMap.getCurrentRoom()), kb);
                    }

                    //Display what is inputted by the user. The default case is the room description (this also helps not to break the code_
                    case "display" -> {
                        System.out.println("\nDisplay people, exits, items, rooms, or room description?");
                        String input = kb.nextLine().toLowerCase();
                        switch (input)
                        {
                            case "people" -> System.out.println(adventureMap.getRoom(adventureMap.getCurrentRoom()).listPersons());
                            case "items" -> System.out.println(adventureMap.getRoom(adventureMap.getCurrentRoom()).listItems());
                            case "exits" -> System.out.println(adventureMap.getRoom(adventureMap.getCurrentRoom()).listExits());
                            default -> System.out.println(adventureMap.getRoom(adventureMap.getCurrentRoom()).toString());
                        }
                    }

                    //Get info of people or items. The default case just makes it so that you put in a new command if something goes wrong.
                    case "get info" -> {
                        System.out.println("\nGet info for people or items?");
                        String input = kb.nextLine().toLowerCase();
                        switch (input)
                        {
                            case "people" -> peopleInfo(adventureMap.getRoom(adventureMap.getCurrentRoom()), kb);
                            case "items" -> itemInfo(adventureMap.getRoom(adventureMap.getCurrentRoom()), kb);
                            default -> input();
                        }
                    }

                    //Get the relevance of people or items. This is all surrounded by the isThisEvidenceGoingToBeTrustworthy method
                    case "is relevant" -> {
                        System.out.println("\nAre you looking to see the relevance of people or items?");
                        String input = kb.nextLine().toLowerCase();
                        switch (input)
                        {
                            //Get the room information, list the people, let the user choose a room, if it matches, they can see if something is relevant
                            //If not, the user will have to just move on to the next input command
                            case "people" -> {
                                System.out.println("\nWell, which person are you looking for in the room currently?");
                                System.out.println((adventureMap.getRoom(adventureMap.getCurrentRoom())).listPersons());
                                input = kb.nextLine().toLowerCase();
                                for (Person p : adventureMap.getRoom(adventureMap.getCurrentRoom()).getPeople())
                                {
                                    if ((p.getName().equalsIgnoreCase(input)))
                                    {
                                        p.isThisEvidenceGoingToBeTrustworthy();
                                    }
                                }
                            }
                            //Get the room information, list the items, let the user choose a room, if it matches, they can see if something is relevant
                            //If not, the user will have to just move on to the next input command
                            case "items" -> {
                                    System.out.println("Well, which item are you looking for in the room currently?");
                                    System.out.println((adventureMap.getRoom(adventureMap.getCurrentRoom())).listItems());
                                    input = kb.nextLine().toLowerCase();
                                    for (Item i : adventureMap.getRoom(adventureMap.getCurrentRoom()).getItems())
                                    {
                                        if ((i.getName().equalsIgnoreCase(input)))
                                        {
                                            i.isThisEvidenceGoingToBeTrustworthy();
                                        }
                                    }
                                }

                            //Display all of the introductory material
                            case "game help" -> displayGameIntro();

                                //If the user did not select item or person, then they get an exception for input validation
                            default -> throw new CommandException("This is not following the instructions!");
                        }
                    }

                    //The goal of this part of the switch statement is to have an inventory menu that can display add or remove
                    case "inventory" -> {
                        System.out.println("\nWhat would you like to do to your inventory? Display, add, or remove?");
                        //Get input and compare it to the commands given
                        String input = kb.nextLine().toLowerCase();
                        switch (input)
                        {
                            //Display the inventory
                            case "display" ->  System.out.println(inventory.display());
                            //Look at what the user wants to add after displaying items in the room. Compare it to what is in the room
                            //Iterate through the items, if one matches, add it to the inventory. If not, then show that in the log
                            //Remove the item from the current room
                            case "add" -> {
                                System.out.println("\nWhich item would you like to add?");
                                System.out.println(adventureMap.getRoom(adventureMap.getCurrentRoom()).listItems());
                                input = kb.nextLine().toLowerCase();
                                for (Item i : adventureMap.getRoom(adventureMap.getCurrentRoom()).getItems())
                                {
                                    if (i.getName().equalsIgnoreCase(input))
                                    {
                                        inventory.add(i);
                                        System.out.println("\nItem " + i.getName() + " successfully added.");
                                        adventureMap.getRoom(adventureMap.getCurrentRoom()).removeItem(i);
                                        break;
                                    }
                                }
                            }

                            //Look at what the user wants to remove and make it lowercase
                            case "remove" -> {
                                System.out.println("\nWhich item would you like to remove?");
                                System.out.println(inventory.display());
                                input = kb.nextLine().toLowerCase();
                                //See if the item is in the inventory
                                for (Item i : inventory.getElements())
                                {
                                    //If the item is in the inventory, remove it from the inventory and add it to the current room
                                    if (i.getName().equalsIgnoreCase(input))
                                    {
                                        inventory.remove(i);
                                        System.out.println("\nItem successfully removed.");
                                        adventureMap.getRoom(adventureMap.getCurrentRoom()).addItem(i);
                                    }
                                }
                            }

                            //If display, add, or remove were not chosen, say that
                            default -> throw new CommandException("\nThat is not one of the given commands");
                        }
                    }


                    case "diary" -> {
                        //Get user input to see if they want to display, add, or remove an item from the diary
                        System.out.println("\nWhat would you like to do to your diary? Display, add, or remove?");
                        String input = kb.nextLine().toLowerCase();
                        switch (input)
                        {
                            //Display the diary
                            case "display" -> System.out.println(diary.display());
                            //See which person's dialogue they want to add
                            case "add" -> {
                                System.out.println("\nWhich person's dialogue would you like to add?");
                                System.out.println(adventureMap.getRoom(adventureMap.getCurrentRoom()).listPersons());
                                input = kb.nextLine().toLowerCase();
                                for (Person p : adventureMap.getRoom(adventureMap.getCurrentRoom()).getPeople())
                                {
                                    //If the input is the same as one of the above iterated people, then add the entry
                                    if (p.getName().equalsIgnoreCase(input))
                                    {
                                        diary.add(p);
                                        System.out.println("\nEntry successfully added.");
                                        break;
                                    }
                                }
                            }

                            case "remove" -> {
                                //See whose entry they would like to remove
                                System.out.println("\nWhich person's entry would you like to remove?");
                                System.out.println(diary.display());
                                input = kb.nextLine().toLowerCase();
                                //Iterate through the people and see if the person matches someone in their diary
                                for (Person p : adventureMap.getRoom(adventureMap.getCurrentRoom()).getPeople())
                                {
                                    if (p.getName().equalsIgnoreCase(input))
                                    {
                                        diary.remove(p.getDialogue());
                                        System.out.println("\nEntry successfully removed.");
                                    }
                                }
                            }
                            //If they do not select add, display, or remove, show them that they are not right
                            default -> throw new CommandException("\nThat is not one of the given commands");
                        }
                    }

                    //Call a meeting
                    case "meeting" -> {
                        Meeting meeting = new Meeting();
                        switch (meeting.callMeeting(inventory, diary))
                        {
                            case "win" -> bool = false;
                            case "loss" -> bool = false;
                        }
                    }

                    //Note that this following case is for the developer's code, and it is not in the directory.
                    //If you get the cheat code, then you will be able to know that you can get evidence strength
                    //Simply just get the items, map them to their strength, and get the strength printed onto the log
                    case "get es" -> {adventureMap.getRoom(adventureMap.getCurrentRoom()).getItems().stream().map(n -> n.getStrength().getStrength()).forEach(o -> System.out.println(o));}

                    //If the user input does not match something from above, then tell them by throwing an exception
                    default -> throw new CommandException("This command is not in our directory");
                }
            }
            //Catch the exception if thrown and tell them why the expression was thrown. Resume the code, however, because nothing was corrupted or changed
            catch (CommandException e)
            {
                System.out.println("This command is not in our directory");
                input();
            }
        }
    }

    //Take note that the next four classes are helper classes. They were made so that I didn't have to copy and paste code

    //Take in a room and and keyboard, see if they want to person list in the room
    //Take the user's input and see if they have the person by comparing each item in an iteration through the room's people
    //If they do, show the description and see if they trust the person
    //If it does not match a person in the room, then just move on to the next input. They know that they did something wrong.
    private void peopleInfo(Room myRoom, Scanner kb)
    {
        System.out.println("\nGet person list in this room? Type Yes to display, type No to not.");
        String input = kb.nextLine().toLowerCase();
        if (input.equalsIgnoreCase("Yes")) { System.out.println(myRoom.listPersons()); }
        System.out.println("\nPlease enter a person's name in this room");
        input = kb.nextLine().toLowerCase();
        ArrayList<Person> people = myRoom.getPeople();
        for (Person p : people)
        {
            if (p.getName().equalsIgnoreCase(input))
            {
                System.out.println(p.getDescription() + "\n" + p.doITrust());
            }
        }
    }

    //Take in a room and and keyboard, see if they want to item list in the room
    //Take the user's input and see if they have the item by comparing each item in an iteration through the room's items
    //If they do, show the description, edibility, and if they would eat the item after eating a gordita supreme
    //If it does not match an item in the room, then just move on to the next input. They know that they did something wrong.
    private void itemInfo(Room myRoom, Scanner kb)
    {
        System.out.println("\nGet item list in this room? Type Yes to display, type No to not.");
        String input = kb.nextLine().toLowerCase();
        if (input.equalsIgnoreCase("Yes")) { System.out.println(myRoom.listItems()); }
        System.out.println("\nPlease enter an item name in this room");
        input = kb.nextLine().toLowerCase();
        ArrayList<Item> items = myRoom.getItems();
        for (Item i : items)
        {
            if (i.getName().equalsIgnoreCase(input))
            {
                System.out.println("\nDescription: " + i.getDescription() + "\nIs it Edible?: " + i.isEdible() + "\n" + i.wouldIEat());
            }
        }
    }

    //Display all of the commands in the game except for get es (that's a cheat code)
    private void commandhelp()
    {
        System.out.println(
                "\nmove- Move to another room" +
                "\ndisplay- Display people, exits, items, room, or room description" +
                "\ninventory- Display your current inventory, add something to your inventory, or remove an item from your inventory" +
                "\ndiary- Display your current diary, add something to your diary, or remove an entry from your diary" +
                "\nget info- Displays the description for an item or a person" +
                "\nhelp- Displays a directory of all possible commands" +
                "\ngame help- Displays the introductory material for the game" +
                "\nis relevant- Helps the user determine if the item / person is relevant to the story" +
                "\nmeeting- Calls a meeting to report evidence");
    }

    //Display all of the introductory material
    private void displayGameIntro()
    {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Objectives: " + "Search through rooms of Kanye's mansion. " +
                "Along the way collect and investigate items used by the Gordita kidnapper.\n");
        System.out.println("\nIntroduction: Kanye West is back from his failed political campaign. He'll get them in 2024." +
                "\nBut as for now, he needs to relax. What a better way to relax than a nice Taco Bell Gordita with Extra Sour Cream?" +
                "\nSo Kanye ordered UberEats and got himself one of those delicious mexican meals from Taco Bell. However, the issue is that" +
                "\nsomeone stole his Gordita. Now you must search his mansion and find enough relevant clues to catch the Gordita Bandit");
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}

