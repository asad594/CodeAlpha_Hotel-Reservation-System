package com.mycompany.hotel_reservation_system;
import java.util.*;
import java.io.*;
        

public class Hotel {
    Scanner input=new Scanner(System.in);
    private String roomsFile;
    private String reservationFile;
    private List<Rooms> rooms=new ArrayList<>();
    private List<Reservation> reservations=new ArrayList<>();
    private int reservation_Counter=1;
    
    Hotel(String roomsFile,String reservationFile)
    {
        this.roomsFile=roomsFile;
        this.reservationFile=reservationFile;
    }
    public void loadRooms()
    {
        rooms.clear();
        File file=new File("rooms.csv");
        System.out.println(file.getAbsolutePath());
        if(!file.exists())
        {
            for (int i = 0; i <= 50; i++) 
            {
                if(i<=20)
                {
                    rooms.add(new Rooms(100+i,100,Rooms.Roomcategory.STANDARD));
                }
                else if(i<=40)
                {
                    rooms.add(new Rooms(100+i,300,Rooms.Roomcategory.DELUXE));
                }
                else
                {
                    rooms.add(new Rooms(100+i,500,Rooms.Roomcategory.SUITE));
                }
            }
            saveRooms();
            return;
        }
        try(BufferedReader br=new BufferedReader(new FileReader(file)))
        {
            String line;
            while((line=br.readLine())!=null)
            {
                String[] parts=line.split(",");
                int id=Integer.parseInt(parts[0]);
                Rooms.Roomcategory category = Rooms.Roomcategory.valueOf(parts[1]);
                double price = Double.parseDouble(parts[2]);
                boolean available = Boolean.parseBoolean(parts[3]);
                rooms.add(new Rooms(id,price,category,available));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void saveRooms()
    {
        try(PrintWriter pw=new PrintWriter(new FileWriter("rooms.csv")))
        {
           for(Rooms r:rooms)
           {
               pw.println(r.getid() + "," + r.getcategory() + "," + r.getprice() + "," + r.getavailablity());
           }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void loadReservation()
    {
        reservations.clear();
        File file=new File("reservations.csv");
        System.out.println(file.getAbsolutePath());
        if(!file.exists())
        {
            return;
        }
        try(BufferedReader br=new BufferedReader(new FileReader(file)))
        {
            String line;
            while((line=br.readLine())!=null)
            {
              String[] parts=line.split(",");
              int id=Integer.parseInt(parts[0]);
              int roomid=Integer.parseInt(parts[1]);
              String name=parts[2];
              int days=Integer.parseInt(parts[3]);
              double total=Double.parseDouble(parts[4]);
              reservations.add(new Reservation(id,roomid,name,days,total));
              reservation_Counter=Math.max(reservation_Counter, id+1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void saveReservation()
    {
        try(PrintWriter pw=new PrintWriter(new FileWriter("reservations.csv")))
        {
            for(Reservation r:reservations)
            {
                pw.println(r.getreservationid() + "," + r.getroomid() + "," + r.getname() + "," + r.getdays() + "," + r.gettotalamount());
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();    
        }
    }
            
    public void searchRooms()
    {
        for(Rooms r:rooms)
        {
            System.out.println("\n");
            System.out.println(r);
        }
    }
    
    public void bookRooms(int roomId,String name)
    {
        for(Rooms r:rooms)
        {
            if(r.getid()==roomId&&r.getavailablity())
            {
                System.out.println("Enter Days : ");
                int days=input.nextInt();
                double total=r.getprice()*days;
                if(Payment.processpayment(total))
                {
                    Reservation res=new Reservation(reservation_Counter++, roomId, name, days, total);
                    reservations.add(res);
                    r.setavailabilty(false);
                    System.out.println("Booking Successful : "+res);
                    saveRooms();
                    saveReservation();
                }
                else
                {
                    System.out.println("Payment Failed \nBooking Not Completed");
                }
                return;
            }
        }
        System.out.println("Room Not Available");
    }
    
    public void cancelReservation(int reservationId)
    {
        Iterator<Reservation> it=reservations.iterator();
        while(it.hasNext())
        {
            Reservation res=it.next();
            if (res.getreservationid() ==reservationId)
            {
                for(Rooms r:rooms)
                {
                    if(r.getid()==res.getroomid())
                    {
                        r.setavailabilty(true);
                    }
                }
                it.remove();
                System.out.println("Reservation : "+reservationId+" Cancelled");
                saveRooms();
                saveReservation();
                return;
            }
        }
        System.out.println("Reservation Not Found");
    }
    
    public void viewReservation()
    {
        for(Reservation r:reservations)
        {
            System.out.println(r);
        }
    } 
}
