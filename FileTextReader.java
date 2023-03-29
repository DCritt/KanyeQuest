import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileTextReader {

    public HashMap<String, Room> readFile(String path)
    {
        HashMap<String, Room> roomList = new HashMap<>();
        Map map = new Map(roomList);
        Inventory inv = new Inventory(map);
        String entry;

        //try with resource must implement AutoCloseable interface
        try(BufferedReader myFileReader = new BufferedReader(new FileReader(path)))
        {
            //combine assignment to entry with null detection
            while((entry = myFileReader.readLine()) != null)
            {
                //next few lines are the same as the previous text reader
                String[] bookInfo = entry.split("~");
                Room room = new Room();
                room.setName((bookInfo[0]));
                room.setDescription(bookInfo[1]);
                room.setExits(bookInfo[2]);
                room.setItems(bookInfo[3]);
                room.setPeople(bookInfo[4]);
                map.addRoom(room);
                //no need to close the file because of try-with-resource
            }
        }
        //IOException is more general than FileNotFoundException, so this catches both
        catch(IOException ex)
        {
            System.out.println("File not found!");
        }

        return map.roomList;
    }

}