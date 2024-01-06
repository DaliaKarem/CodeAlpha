package Task3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


class Room{
   private int numOfRooms;
   private String categories;
   private boolean isAvailable;
   private double price;
   private double totalPrice;
   Room(){}
    Room(int numOfRooms, String categories,double price){
        this.numOfRooms = numOfRooms;
        this.categories =categories;
        this.price=price;
        this.isAvailable = true;
    }
    public double getPrice(){
        return price;
    }
    public void totalPrice(int Days,double price)
    {
        this.totalPrice = price*Days;
    }
    public double getTotalPrice(){
        return this.totalPrice;
    }
     public int getRoomNumber() {
        return numOfRooms;
    }

    public String getCategory() {
        return categories;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom(int Days) {
        this.isAvailable = false;
        totalPrice(Days,this.price);
    }

    public void releaseRoom() {
        this.isAvailable = true;
    }

    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    Room otherRoom = (Room) obj;
    return this.getRoomNumber() == otherRoom.getRoomNumber();
}

@Override
public int hashCode() {
    return Objects.hash(getRoomNumber());
}
}




 class Hotel {
    //one category -->list of rooms
    private Map<String, List<Room>> roomsByCategory;
    Hotel(){
     this.roomsByCategory = new HashMap<>();
    }
    public void addRoom(int roomNum,String category,double price){
        Room room = new Room(roomNum,category,price);
        roomsByCategory.computeIfAbsent(category, k -> new ArrayList<>()).add(room);
    }
    //get free room
    public List<Room> getAvaRooms(String category) {
        return roomsByCategory.getOrDefault(category, new ArrayList<>());
    }
    public double getPrice(int roomNumber, String category) {
        for (List<Room> rooms : roomsByCategory.values()) {
            for (Room room : rooms) {
                if (room.getRoomNumber() == roomNumber && room.getCategory().equalsIgnoreCase(category)) {
                    return room.getPrice();
                }
            }
        }
    
        // Return 0 if the room is not found
        System.out.println("Room not found for room number " + roomNumber + " and category " + category);
        return 0;
    }
}
