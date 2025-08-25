/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hotel_reservation_system;

import java.util.Scanner;

/**
 *
 * @author Asad
 */
public class Hotel_Reservation_System {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Hotel hotel = new Hotel("rooms.csv", "reservations.csv");
        hotel.loadRooms();
        hotel.loadReservation();
        int choice;
        do 
        {
            System.out.println("\n=======Hotel Reservation System========");
            System.out.println("1.Show All Rooms");
            System.out.println("2.Book a Room");
            System.out.println("3.Cancel Reservation");
            System.out.println("4.View Reservation");
            System.out.println("5.Exit And Save Data");
            choice=input.nextInt();
            input.nextLine();
            
            switch(choice)
            {
                case 1:
                    hotel.searchRooms();
                break;
                
                case 2:
                    System.out.println("Enter Room ID TO Book: ");
                    int id=input.nextInt();
                    System.out.println("Enter Your Name : ");
                    String name=input.next();
                    hotel.bookRooms(id, name);
                break;
                
                case 3:
                    System.out.println("Enter reservation ID to cancel : ");
                    int res=input.nextInt();
                    hotel.cancelReservation(res);
                break;
                
                case 4:
                    hotel.viewReservation();
                break;
                
                case 5:
                    System.out.println("Saving data and exiting...");
                    hotel.saveRooms();
                    hotel.saveReservation();
                    input.close();
                break;
                
                default:
                   System.out.println("Invalid choice! Try again.");
                break;

            }
        }
        while(choice!=5);
        input.close();
        
    }
}
