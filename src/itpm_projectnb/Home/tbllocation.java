/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itpm_projectnb.Home;

/**
 *
 * @author Sudarshana
 */
public class tbllocation {
    private int LocationID;
    private String BuildingName;
    private String RoomName;
    private String roomType;
    private int Capacity;

    public tbllocation(int LocationID, String BuildingName, String RoomName, String roomType, int Capacity) {
        this.LocationID = LocationID;
        this.BuildingName = BuildingName;
        this.RoomName = RoomName;
        this.roomType = roomType;
        this.Capacity = Capacity;
    }

    

    public int getLocationID() {
        return LocationID;
    }

    public void setLocationID(int LocationID) {
        this.LocationID = LocationID;
    }

    public String getBuildingName() {
        return BuildingName;
    }

    public void setBuildingName(String BuildingName) {
        this.BuildingName = BuildingName;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }
    
    
    
}
