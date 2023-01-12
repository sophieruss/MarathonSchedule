import java.io.*;
import java.util.*;

public class dailySequence
{
    private static ArrayList <Integer> totalMileageArray = new ArrayList<Integer>();
    private static int userWeek, mileageTotal, day;
    private static int numberOfWeeks = fillTotalMileageArray();
    private static boolean found = false;
    private static int [] weekMileageArray = new int[7];														 //number for each day of the week
    private static int [] proposeweekMileageArray = new int[7]; 												//an array used if the user wants to change the mileage for the week
    private static int [][] mileage = new int [numberOfWeeks][7];

    static Scanner scan = new Scanner(System.in);

    static void main()
    {
        getWeek();
        getMileageTotal();
        getWeekMileageFile();
        printWeek();
        promptChangeMileage();
        getHelp();
    }
    public static void getWeek()
    {
        userWeek = -1;
        while(userWeek<1 || userWeek>20)
        {
            System.out.println("What week are you on?");
            userWeek = Integer.parseInt(scan.nextLine());

            if (userWeek<1 || userWeek>20)
            {
                System.out.print("Please enter a valid week. ");
            }
        }
    }
    public static int fillTotalMileageArray()  //sets maxMileage to the total mileage for that week
    {
        try 															{
            File mileagetotal = new File ("mileagetotal.txt");
            Scanner scan = new Scanner (mileagetotal);

            String totalPlaceHolder;

            do
            {
                totalPlaceHolder = scan.nextLine();
                totalMileageArray.add(Integer.parseInt(totalPlaceHolder));
            }
            while( scan.hasNext());

        }
        catch (FileNotFoundException filenotfound)
        {
            System.out.println("File not Found");
        }

        return totalMileageArray.size();
    }
    public static void getMileageTotal() //sets maxMileage to the total mileage for that week
    {
        try
        {
            File mileagetotal = new File ("mileagetotal.txt");
            Scanner scan = new Scanner (mileagetotal);

            int weekMarker=1;
            String totalPlaceHolder;

            do
            {
                totalPlaceHolder = scan.nextLine();
                if(weekMarker == userWeek)
                {
                    mileageTotal = (Integer.parseInt(totalPlaceHolder));
                    found = true;
                    break;
                }
                weekMarker++;
            }
            while( scan.hasNext());

        }
        catch (FileNotFoundException filenotfound)
        {
            System.out.println("File not Found");
        }

    }
    public static void getWeekMileageFile() 	//fills array with the mileage for that week
    {
        if(found != true) 																						//need to run getTotalMileageFile() before this in order for the boolean found to be able to change to positive
        {
            System.out.println("This week is not in your schedule.");
        }

        if(found == true)
        {
            try
            {
                File weekMileageFile = new File ("usermileageweek" + userWeek + ".txt");
                Scanner scan = new Scanner (weekMileageFile);
                for (int i = 0; i<7; i++)
                {
                    weekMileageArray[i] = scan.nextInt();
                }
            }
            catch (FileNotFoundException filenotfound)
            {
                System.out.println("File not Found");
            }
        }
    }
    public static void printWeek()
    {
        System.out.println("Week     M    T    W    TH   F    SA   S     "
                + "Total \n----------------------------------------------------");
        if((userWeek)<10)
        {
            System.out.print(" " + (userWeek) + "   |   ");
        }
        else
        {
            System.out.print(" " + (userWeek) + "  |   ");
        }
        for(int day = 0; day<7; day++) //for loop to go through each day of the week
        {
            if(weekMileageArray[day] == 0)
            {
                System.out.print("X");
            }
            else
            {
                System.out.print(weekMileageArray[day]);
            }

            if(weekMileageArray[day] < 10)
            {
                System.out.print("    ");
            }
            else
            {
                System.out.print("   ");
            }
        }
        System.out.println("| " + mileageTotal);
    }
    public static void printDay()
    {
        System.out.println("What day are you on? (Please enter the corresponding number) \n\n 1 = Monday \n 2 = "
                + "Tuesday \n 3 = Wednesday \n 4 = Thursday \n 5 = Friday \n 6 = Saturday \n 7 = Sunday");
        day = (scan.nextInt())-1;
        String dayDescription = null;
        String dayName = null;
        switch(day)
        {
            case 0 : dayName = "Monday";
                dayDescription = "Today is typically a light and easy run."; break;
            case 1 : dayName = "Tuesday";
                dayDescription = "Today is typically an interval day with lots of speed and hill work."; break;
            case 2 : dayName = "Wednesday";
                dayDescription = "Today is typically a rest or crosstraining day."; break;
            case 3 : dayName = "Thursday";
                dayDescription = "Today is typically an easy or fartlek run."; break;
            case 4 : dayName = "Friday";
                dayDescription = "Today is typically an easy run."; break;
            case 5 : dayName = "Saturday";
                dayDescription = "Today is typically a long run."; break;
            case 6 : dayName = "Sunday";
                dayDescription = "Today is typically a rest day."; break;

        }
        System.out.println("Current Mileage for " +
                dayName + ": " + weekMileageArray[day] + "\n" + dayDescription);
    }
    public static void promptChangeMileage()
    {
        String answer = null;
        System.out.println("\nWould you like to adjust your mileage today (YES or NO)? \n\n "
                + "Adjusting your mileage will add/subtract miles to future days \n this week "
                + "to ensure you reach the total mileage for the week.");
        answer = (scan.nextLine()).toUpperCase();
        if(answer.contentEquals("YES"))
        {
            printDay();
            changeMileage();
        }
        else if(answer.contentEquals("NO"))
        {
            System.out.println("Okay! Have a nice run");
        }
        else
        {
            System.out.println("Not a valid response. Please try again.");
            promptChangeMileage();
        }
    }
    public static void getHelp()
    {
        boolean status = false;

        while(status != true)
        {
            System.out.println("\nIs there anything else I can do for you? (Please enter the \ncorresponding number) \n\n 1 = Print Week \n 2 = Print Day \n 3 = Print Entire Schedule "
                    + "\n 4 = Start Over \n 5 = No, I am done!" );
            int response = scan.nextInt();
            scan.nextLine();
            switch(response)
            {
                case 1 : printWeek(); break;
                case 2 : printDay(); break;
                case 3 : printAllWeeks(); break;
                case 4 : main(); break;
                case 5 : System.out.println("Ok! See you next run!"); status = true; scan.nextLine(); break;
            }
        }
    }

    public static void changeMileage()
    {
        System.out.println("What would you like to change your mileage to?");
        int userMileageRequest = scan.nextInt();

        int totalMilesLeft = 0; 																		//calculates how many miles are left for the week
        for(int i = 0; i <proposeweekMileageArray.length; i++) 														//duplicate array		creates an array identical to weekMileageArray. Adds the mileage up that are left for the week (inclusive of the current day)
        {
            proposeweekMileageArray[i] = weekMileageArray[i];
            if (i >= day)
            {
                totalMilesLeft += weekMileageArray[i];
            }
        }
        proposeweekMileageArray[day] = userMileageRequest;
        int mileageDifference = weekMileageArray[day] - userMileageRequest; 										//(subtracts the requested mileage for the day from original mileage for the day



        if(day == 5)
        {
            String userRequestConfirmation;

            System.out.println("This is your last running day. Your weekly mileage will \nbe "
                    + (mileageTotal - mileageDifference) + " instead of " + mileageTotal);
            System.out.println("Are you sure you want to do this? (YES or NO)");
            userRequestConfirmation = (scan.next()).toUpperCase();

            if(userRequestConfirmation.contentEquals("YES"))
            {
                System.out.println("Ok. I will make the following changes.\n\n");
                mileageTotal = (mileageTotal - mileageDifference);
                System.out.println("oeirgjorwij" + mileageTotal);
                saveChanges();
                System.out.println("jhuihuygkuyg" + mileageTotal);

            }																											//mileageDifference = 0; 					//to ensure program does not go through other if statements
        }
        else if(day == 2 || day == 6)
        {
            System.out.println("Today is a rest day! Rest!!");
            proposeweekMileageArray[day] = 0;																					//mileageDifference = 0; 		//to ensure program does not go through other if statements
            day = 5; 																											//to ensure program does not print "here is your new proposed week:" etc.
        }


        else if ( (userMileageRequest>totalMilesLeft) || userMileageRequest<0 ) 														//if the requested mileage is greater than the total mileage remaining for the week, that we end up with negative mileage which is not what we want
        {
            System.out.println("Your proposed mileage is too extreme. Try a "
                    + "number closer to your current mileage"); 												//if the difference is greater than the miles left for the week, than the rest of the week will become negative miles which we don't want
            day = 5; 																													//to ensure program does not print "here is your new proposed week:" etc.
        }

        else if(mileageDifference > 0)																	 //use entered less miles for the day - will need to add miles to remaining days
        {
            do
            {
                for(int k=day+1; k<proposeweekMileageArray.length; k++)									 //loop through the proposeMileage Array but skips the current day and the days before (because why would you change the past??)
                {

                    if(proposeweekMileageArray[k] != 0) 													// if it's not a rest day, change the mileage
                    {
                        proposeweekMileageArray[k] ++;
                        mileageDifference --;
                        if (mileageDifference == 0)
                        {
                            break;
                        }
                    }


                }

            }
            while(mileageDifference>0);
        }

        else if(mileageDifference < 0) 																		//use entered more miles for the day - will need to subtract miles to remaining days
        {
            do
            {
                for(int k=day+1; k<proposeweekMileageArray.length; k++) 									//loop through the proposeMileage Array but skips the current day and the days before (because why would you change the past??)
                {
                    if(proposeweekMileageArray[k] != 0) 													// if it's not a rest day, change the mileage
                    {
                        proposeweekMileageArray[k] --;
                        mileageDifference ++;
                        if (mileageDifference == 0)
                        {
                            break;
                        }
                    }
                }
            }
            while(mileageDifference < 0);
        }

        if (day != 5)
        {
            System.out.println("Here is your new proposed week:");
            printProposedWeek();
            System.out.println("\nWould you like to change your schedule to "
                    + "this? Please confirm \nwith YES or NO");
            scan.nextLine();
            String userRequestConfirmation = (scan.nextLine()).toUpperCase();
            if(userRequestConfirmation.contentEquals("YES"))
            {
                System.out.println("Ok. I will make the following changes.");
                saveChanges();
            }
        }
        System.out.println("Have a fun run!");
    }

    public static void saveChanges()
    {
        changeMileageWeekToProposed();
        saveMileage();
        saveMileageTotal();
        totalMileageArray.clear();
        fillTotalMileageArray();
    }
    public static void printProposedWeek()
    {

        System.out.println("Week     M    T    W    TH   F    SA   S     Total \n----------------------------------------------------");

        if((userWeek)<10)
        {
            System.out.print(" " + (userWeek) + "   |   ");
        }
        else
        {
            System.out.print(" " + (userWeek) + "  |   ");
        }

        for(int day = 0; day<7; day++) //for loop for to go through each day of the week
        {
            if(proposeweekMileageArray[day] == 0)
            {
                System.out.print("X");
            }
            else
            {
                System.out.print(proposeweekMileageArray[day]);
            }

            if(proposeweekMileageArray[day] < 10)
            {
                System.out.print("    ");
            }
            else
            {
                System.out.print("   ");
            }
        }

        System.out.println("| " + mileageTotal);


    }
    public static void changeMileageWeekToProposed()
    {
        for(int i = 0; i <weekMileageArray.length; i++)
        {
            weekMileageArray[i] = proposeweekMileageArray[i] ;
        }
    }
    public static void saveMileage() //updates usermileageweekXX.txt file

    {
        try
        {
            File mileageFile = new File ("usermileageweek" + (userWeek) + ".txt"); 											//create a file
            PrintStream writer = new PrintStream (mileageFile); //create the writer
            for(int col = 0; col < 7; col++) //days
            {
                writer.println(weekMileageArray[col]);
            }
            writer.close(); //close file connection
        }
        catch(FileNotFoundException filenotfound) 																		//if file is not found
        {
            System.err.println("The file was not found.");
        }
    }

    public static void saveMileageTotal() //updates mileagetotal.txt file																	//saves new total mileage for the week week into a file
    {
        try
        {
            File mileagetotalFile = new File ("mileagetotal.txt"); 														//create a file
            PrintStream writer = new PrintStream (mileagetotalFile); 													//create the writer
            for(int row = 0; row < totalMileageArray.size(); row++) //number of weeks
            {

                if(row == (userWeek-1))
                {
                    writer.println(mileageTotal);

                }
                else
                {
                    writer.println(totalMileageArray.get(row));
                }
            }
            writer.close(); 																				//close file connection
        }
        catch(FileNotFoundException filenotfound) 															//if file is not found
        {
            System.err.println("The file was not found.");
        }
    }

    public static void fillMileageArray()
    {
        try
        {
            for(int row = 0; row < numberOfWeeks; row++)
            {
                File mileagetotal = new File ("usermileageweek" + (row+1)+ ".txt");

                Scanner scan = new Scanner (mileagetotal);

                int col = 0;

                while(col < 7 && scan.hasNext() && row<numberOfWeeks)
                {
                    mileage[row][col] = Integer.parseInt(scan.nextLine());
                    col++;

                }
            }

        }



        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    public static void printAllWeeks()
    {

        fillMileageArray();

        System.out.println("Week     M    T    W    TH   F    SA   S     Total \n----------------------------------------------------");


        for(int row = 0; row < numberOfWeeks; row++)
        {
            if((row+1)<10)
            {
                System.out.print(" " + (row+1) + "   |   ");
            }
            else
            {
                System.out.print(" " + (row+1) + "  |   ");
            }


            for(int col = 0; col < 7; col++)
            {

                if(mileage[row][col] == 0)					//if off day, display X
                {
                    System.out.print("X");
                }

                else
                {
                    System.out.print(mileage[row][col]);	//display mileage
                }


                if(mileage[row][col]<10)		//spaces
                {
                    System.out.print("    ");				//if fri mileage is double digits, have one less space
                }

                else
                {
                    System.out.print("   ");
                }
            }
            System.out.print("| " + totalMileageArray.get(row));
            System.out.println();
        }


    }
}


