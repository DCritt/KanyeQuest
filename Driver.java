import java.util.HashMap;
import java.util.Scanner;

public class Driver
{
    public static void main(String[] args) throws CommandException
    {
        //Take the scanner in and see if they want to make a custom game or just play the regular KanyeQuest
        Scanner kb = new Scanner(System.in);
        System.out.println("Read file or create file?");
        String input = kb.nextLine();
        //If they read the file, then the filetext reader is created, the roomlist is made by choosing the KanyeQuest file name, and the roomlist is passed into the map
        //The game then initializes with foyer being the first room, and the input starts
        if (input.equalsIgnoreCase("read file"))
        {
            FileTextReader reader = new FileTextReader();
            HashMap<String, Room> roomList = reader.readFile("KanyeQuest");
            Map map = new Map(roomList);
            InputLogic logic = new InputLogic(map);
            logic.start();
            logic.input();
        }
        //If they don't read the file, then a new file is created under the guise of Custom
        //The custom file is created, and the same thing as above happens.
        else if (input.equalsIgnoreCase("create file"))
        {
            AFileWriter writer = new AFileWriter();
            writer.textFileWriter();
            FileTextReader reader = new FileTextReader();
            HashMap<String, Room> roomList = reader.readFile("Custom");
            Map map = new Map(roomList);
            InputLogic logic = new InputLogic(map);
            logic.start();
            logic.input();
        }
    }
}

