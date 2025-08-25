
package com.mycompany.hotel_reservation_system;
import java.io.*;

public class Reservation implements Serializable{
    private int reservationid;
    private int roomid;
    private String guestname;
    private int days;
    private double totalamount;
    
    Reservation(int reservationid,int roomid,String guestname,int days,double totalamount)
    {
        this.reservationid=reservationid;
        this.roomid=roomid;
        this.guestname=guestname;
        this.days=days;
        this.totalamount=totalamount;
    }
    public int getreservationid()
    {
        return reservationid;
    }
    public int getroomid()
    {
        return roomid;
    }
    public String getname()
    {
        return guestname;
    }
    public int getdays()
    {
        return days;
    }
    public double gettotalamount()
    {
        return totalamount;
    }
    
    @Override
    public String toString()
    {
        return 
                "Reservation : "+reservationid+"\n"+
                "Room : "+roomid+"\n"+
                "Guest Name : "+guestname+"\n"+
                "Days : "+days+"\n"+
                "Paid : $"+totalamount;
    }
}
