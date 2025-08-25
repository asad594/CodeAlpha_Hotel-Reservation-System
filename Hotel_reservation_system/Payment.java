
package com.mycompany.hotel_reservation_system;
import java.util.Scanner;

public class Payment {
    public static boolean processpayment(double amount)
    {
        Scanner input=new Scanner(System.in);
        try
        {
            System.out.println("Total To Pay : $"+amount);
            System.out.print("Enter payment amount : $");
            double paidamount=input.nextDouble();
            if(paidamount>=amount)
            {
                System.out.println("\n");
                System.out.println("Payment Successfull ");
                System.out.println("Change : $"+(paidamount-amount));
                return true;
            }
            else
            {
                System.out.println("Insufficient amount ");
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println("Payment Error : "+e.getMessage());
            return false;
        }
    }
    
}
