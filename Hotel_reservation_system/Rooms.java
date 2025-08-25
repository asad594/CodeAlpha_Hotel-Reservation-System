
package com.mycompany.hotel_reservation_system;
import java.io.*;

public class Rooms implements Serializable{
    public enum Roomcategory{STANDARD,DELUXE,SUITE};
    
    private int roomid;
    private double price;
    private Roomcategory category;
    private boolean room_available;
    
    Rooms(int roomid,double price,Roomcategory category)
    {
        this.roomid=roomid;
        this.price=price;
        this.category=category;
        this.room_available=true;
    }
    Rooms(int roomid,double price,Roomcategory category,boolean room_available)
    {
        this.roomid=roomid;
        this.price=price;
        this.category=category;
        this.room_available= room_available;
    }
    public int getid()
    {
        return roomid;
    }
    public double getprice()
    {
        return price;
    }
    public Roomcategory getcategory()
    {
        return category;
    }
    public boolean getavailablity()
    {
        return room_available;
    }
    public void setavailabilty(boolean room_available)
    {
        this.room_available=room_available;
    }
    
    @Override
    public String toString()
    {
        return 
                "Room : "+roomid+"\n"+
                "Category : "+category+"\n"+
                "Price : "+price+"\n"+
                (room_available ? "Available ":"Booked");
    }
    
   
    
}
