import java.io.*;
import java.util.*;

public class user {

    static Scanner scan = new Scanner(System.in);

    public static void userStatus()
    {
        System.out.println("Are you a new user? ('YES' or 'NO')");
        String response = (scan.nextLine()).toUpperCase();

        if(response.contentEquals("YES"))
        {
            newUser();
        }

        else
        {
            System.out.println("Locating your information...");
        }
    }
    public static String getName()
    {
        String name1;
        System.out.println("Welcome! Please enter your name: "); //Name input
        name1 = scan.nextLine();
        return name1;
    }
    public static double getPace()
    {
        double pace1 = 0.0;

        System.out.println("Would you like to enter a recent 5k time or mile time? \n(Enter 'A' for 5k time or 'B' for mile time)");
        String temp = (scan.nextLine()).toUpperCase();

        if(temp.equals("A"))
        {
            System.out.println("Please enter a recent 5k time: (MM.SS or MMM.SS)");
            pace1 = (scan.nextDouble());
            pace1 = (60*((int)(pace1*100)/100)) + ((pace1*100)%100); //get 5k time in seconds
            pace1 = pace1/(5/1.609); //gets mile time in seconds
        }
        else if(temp.equals("B"))
        {
            System.out.println("Please enter a recent mile time: (MM.SS)");
            pace1 = scan.nextDouble();
            pace1 = ((pace1/1)*60) + (((pace1*100)%100)); //get mile time in seconds

        }
        else
        {
            System.out.println("Not a valid input. Please try again.");
            getPace();
        }

        return pace1;
    }
    public static int getAge()
    {
        int age1;
        System.out.println("Age: "); //Age input
        age1 = scan.nextInt();
        return age1;
    }
    public static int getWeeks()
    {
        int weeks1;
        System.out.println("Weeks to train (12-20): ");
        weeks1 = scan.nextInt();
        if(weeks1<12)
        {
            System.out.println("You need to have atleast twelve weeks");
            return 0;
        }
        if(weeks1>20)
        {
            System.out.println("This program is designed for up to a 20 week training schedule. \nPlease come back in "+ (weeks1-20) + " weeks. ");
            return 0;
        }
        else
        {
            return weeks1;

        }
    }
    public static void newUser()
    {
        String name;
        double pace;
        int age, weeks;

        name = getName();
        pace = getPace();
        age = getAge();
        weeks = getWeeks();
        int paceMin = (int)((pace)/60);
        int paceSec = (int)(pace%60);

        try
        {
            File userFile = new File ("userinfo.txt"); 											//create a file
            PrintStream writer = new PrintStream (userFile);									//create the writer

            writer.println(((name).trim()).toUpperCase());
            writer.println(weeks);
            writer.println(age);
            writer.println(paceMin);
            writer.println(paceSec);
            writer.close(); 																	//close file connection

            System.out.println("Thank you. The following information has recorded:");
            System.out.println("Name: " + name);
            System.out.println("Weeks to train: " + weeks);
            System.out.println("Age: " + age);
            System.out.println("Time: " + paceMin + ":" + paceSec);

        }
        catch(FileNotFoundException filenotfound)
        {
            System.err.println("The file was not found.");
        }
    }
    public static int getWeeksTotal()
    {
        int returnTemp = 0;

        try
        {
            File weekstotal = new File ("userinfo.txt");
            Scanner scan = new Scanner (weekstotal);

            int lineMarker=1;
            String weeksTotalTemp; //bc its in a String not an int

            do
            {
                weeksTotalTemp = scan.nextLine();
                if(lineMarker == 2) //bc line1 is the user's name
                {
                    returnTemp = Integer.parseInt(weeksTotalTemp);
                    break;
                }
                lineMarker++;
            }
            while( scan.hasNext());

            scan.close(); 					//closes scanner :)

        }
        catch (FileNotFoundException filenotfound)
        {
            System.out.println("File not Found");
        }

        return returnTemp;
    }
}
