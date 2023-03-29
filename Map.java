import java.util.HashMap;
import java.util.Scanner;

public class Map
{
    public HashMap<String,Room> roomList;
    private String currentRoom;

    public Map(HashMap<String, Room> roomList)
    {
        this.roomList = roomList;
    }

    public void addRoom(Room room)
    {
        roomList.put(room.getName().toLowerCase(), room);
    }

    public Room getRoom(String roomName)
    {
        return roomList.get(roomName.toLowerCase());
    }

    public String getCurrentRoom()
    {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom)
    {
        this.currentRoom = currentRoom;
    }

    public void move()
    {
        System.out.println(getRoom(currentRoom).listExits());
        System.out.println("To where?");
        Scanner kb = new Scanner(System.in);
        currentRoom = kb.nextLine();
        for (Room r: roomList.values())
        {
            boolean bool = (getRoom(currentRoom) == r);
            if (bool)
            {
                System.out.println(getRoom(currentRoom).toString());
            }
        }
    }
}
