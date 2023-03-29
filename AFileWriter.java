import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AFileWriter {
    //takes a list of Person objects, a filename, and a separator
    public void textFileWriter()
    {
        //Print introduction explaining that we are in beta with some constants in the game that are going to be the same.
        System.out.println("Welcome to the game creator! You are about to create your own map for KanyeQuest." +
                "\nNote that we are still in beta. This means that we have hardcoded some aspects of the game." +
                "\nWhen writing your map, you must consider a few things. The underlying story is the same and Kylie is the one who is guilty." +
                "\nAlso note that you must make one of your rooms a Foyer for the game to work." +
                "\nYou should also note that if you don't create a functioning map, we are not able to fix it, so please make all of your rooms connect." +
                "\nThe win condition is that you must have 18 or more points if you want to beat the game." +
                "\nThe capacity for diary is 8 and the capacity for inventory is 6." +
                "\nPlease do not use the ~, :, or % characters when building, as this would break the file reader" +
                "\nMost importantly, have fun map creator!");
        //try-with-resource can throw an IOException
        try(FileWriter myWriter = new FileWriter("Custom"))
        {
            //Create a new string that will be built
            String loopBreak = "";
            //Make a loop so that unless the user types exit, the writer will keep on going
            while (!loopBreak.equals("exit"))
            {
                //Put in the room name and a separator, and add the room description with a separator
                String temp = "";
                Scanner kb = new Scanner(System.in);
                System.out.println("Please input a room name");
                String input = kb.nextLine();
                temp += input + "~";
                System.out.println("Room name successfully added.");
                System.out.println("\nPlease input a room description");
                input = kb.nextLine();
                temp += input + "~";
                System.out.println("Room description successfully added.");
                //Clear the input so that the loop does not break
                input = "";
                //Add the exits, make a while loop with the exit condition being next
                System.out.println("\nPlease input as many exits as you want. When you are done, type next");
                while (! input.equals("next"))
                {
                    input = kb.nextLine();
                    //When next is selected, make a different separator
                    temp += input.equals("next") ? "~" : input + "%";

                }
                //Instructions and verification before we move on to the next step
                System.out.println("Room exits successfully added.");
                input = "";
                System.out.println("\nPlease input as many items as you want. " +
                        "The writer will alternate between item name and description and separate them with a colon." +
                        " Please input item one individually." +
                        " When you are done, type next");
                //Make a loop for the item, the user will provide an item name, description, edibility value, and item relevance
                //All will be separated with colons for parsing, the item adding loop is terminated after next is selected
                while (! input.equals("next"))
                {
                    System.out.println("Please provide an item name.");
                    input = kb.nextLine();
                    temp += input + ":";
                    System.out.println("Please provide an item description.");
                    input = kb.nextLine();
                    temp += input + ":";
                    System.out.println("Please provide an item edibility value. (Y or N)");
                    input = kb.nextLine();
                    temp += input + ":";
                    System.out.println("Provide an item relevance. (0 to 4)");
                    input = kb.nextLine();
                    temp += input;
                    System.out.println("Item description written. Press Enter to move on or type next to finish.");
                    input = kb.nextLine();
                    temp += input.equals("next") ? "~" : "%";
                }
                //Information and verification
                System.out.println("\nItems successfully added.");
                input = "";
                System.out.println("Please input as many people as you want. " +
                        "The writer will alternate between people name and description and separate them with a colon." +
                        " Please input person one individually." +
                        " When you are done, type next");
                //Make a loop for the person, the user will provide an person name, description, dialogue, and person relevance
                //All will be separated with colons for parsing, the person adding loop is terminated after next is selected
                while (! input.equals("next"))
                {
                    System.out.println("Please provide a person's name.");
                    input = kb.nextLine();
                    temp += input + ":";
                    System.out.println("Please provide a person's description.");
                    input = kb.nextLine();
                    temp += input + ":";
                    System.out.println("Please provide a person's dialogue.");
                    input = kb.nextLine();
                    temp += input + ":";
                    System.out.println("Please provide a person's dialogue relevance.");
                    input += kb.nextLine();
                    temp += input;
                    System.out.println("Person description written. Press Enter to move on or type next to finish.");
                    input = kb.nextLine();
                    temp += input.equals("next") ? "" : "%";
                }
                temp += "\n";
                System.out.println("\nPeople successfully added.");
                input = "";
                //See if the user wants to continue, if they do, the loop continues. If not, the loop ends.
                myWriter.write(temp);
                System.out.println("This line and this room is complete." +
                        " If you would like to quit, type exit. If not, press Enter to move on.");
                loopBreak = kb.nextLine();
            }
        }
        //Catch an IOException if something with FileWriting goes wrong
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        //no need to close the file because of "try-with-resource"

    }
}
