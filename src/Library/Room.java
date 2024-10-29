package Library;

import java.util.HashMap;
import java.util.Map;

public class Room {
    //Room class storing roomtype, may update later to include more
    private final RoomType roomType;
    private Map<String, Room> exits;

    public Room(RoomType roomType) {
        this.roomType = roomType;
        this.exits = new HashMap<>();
    }

    //for future use
    public RoomType getRoomType() {
        return roomType;
    }
    //for future use
    public Room getExit(String direction) {
        return exits.get(direction);
    }
}




