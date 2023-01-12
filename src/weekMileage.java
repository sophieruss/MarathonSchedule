import java.util.*;
import java.io.*;

public class weekMileage
{
    private static int weeksTotal = user.getWeeksTotal();
    private static int [] mileageTotal = new int[weeksTotal];
    private static int [] longRuns = new int[weeksTotal];
    private static int [] minMileage =   {4,5,0,5,4,0,0};
    private static int [] rangeMileage = {3,4,0,3,1,0,0};
    private static int [][] mileage = new int [weeksTotal][7];								/*1 = mon, 2 = tues, 3 = weds, 4 = thurs, 5 = fri, 6 = sat, 7 = sun */

    public static void mileageMain()
    {
        getFirstFour();
        getLastSix();
        getSaturday();
        getRemainMileage();
        getMileageTotal();
        checkMileage();
        saveMileage();
        saveMileageTotal();
    }

    public static void getMileageTotal()
    {

        switch(weeksTotal)
        {
            case 12 : mileageTotal = new int [] {30,33,36,32, 39,42, 45,41,38,40,28,15}; break;
            case 13 : mileageTotal = new int [] {30,33,36,32, 38,40,42, 45,41,38,40,28,15}; break;
            case 14 : mileageTotal = new int [] {30,33,36,32, 38,40,37,42, 45,41,38,40,28,15}; break;
            case 15 : mileageTotal = new int [] {30,33,36,32, 38,39,40,37,42, 45,41,38,40,28,15}; break;
            case 16 : mileageTotal = new int [] {30,33,36,32, 38,39,41,37,37,42, 45,41,38,40,28,15}; break;
            case 17 : mileageTotal = new int [] {30,33,36,32, 38,39,41,35,37,37,42, 45,41,38,40,28,15}; break;
            case 18 : mileageTotal = new int [] {30,33,36,32, 38,39,41,35,35,37,37,42, 45,41,38,40,28,15}; break;
            case 19 : mileageTotal = new int [] {30,33,36,32, 38,39,41,35,32,35,37,37,42, 45,41,38,40,28,15}; break;
            case 20 : mileageTotal = new int [] {30,33,36,32, 38,39,41,35,35,30,32,37,37,42, 45,41,38,40,28,15}; break;
            default : break;
        }
    }
    public static void getFirstFour()
    {

        for (int week = 0; week <4; week++)
        {
            for(int dayOfWeek = 0; dayOfWeek<7; dayOfWeek++) 							//for loop for to go through each day of the week
            {
                int [] week1 = {5,6,0,5,4,10,0}; 										//set mileage for the week
                int [] week2 = {5,7,0,5,4,12,0};
                int [] week3 = {5,8,0,6,3,14,0};
                int [] week4 = {6,5,0,6,5,10,0};

                switch (week)
                {
                    case 0 : mileage[week][dayOfWeek] = week1[dayOfWeek]; break; 		//set the 2darray to the correct mileage listed above for week 0,1,etc;
                    case 1 : mileage[week][dayOfWeek] = week2[dayOfWeek]; break;
                    case 2 : mileage[week][dayOfWeek] = week3[dayOfWeek]; break;
                    case 3 : mileage[week][dayOfWeek] = week4[dayOfWeek]; break;
                    default : break;
                }

            }
        }
    }
    public static void getLastSix()
    {
        for (int week = weeksTotal-6; week < weeksTotal; week++)
        {
            for(int dayOfWeek = 0; dayOfWeek<7; dayOfWeek++) //for loop for to go through each day of the week
            {
                int [] weekLast =  {3,5,0,4,3,0,0}; //set mileage for the week
                int [] week2Last = {4,5,0,5,4,10,0};
                int [] week3Last = {5,9,0,6,5,15,0};
                int [] week4Last = {5,5,0,6,4,18,0};
                int [] week5Last = {7,8,0,6,5,15,0};
                int [] week6Last = {6,5,0,10,4,20,0}; //max
                if (week == weeksTotal-6)
                {
                    mileage[week][dayOfWeek] = week6Last[dayOfWeek]; //set the 2darray to the correct mileage listed above
                }
                if (week == weeksTotal-5)
                {
                    mileage[week][dayOfWeek] = week5Last[dayOfWeek]; //set the 2darray to the correct mileage listed above
                }
                if (week == weeksTotal-4)
                {
                    mileage[week][dayOfWeek] = week4Last[dayOfWeek]; //set the 2darray to the correct mileage listed above
                }
                if (week == weeksTotal-3)
                {
                    mileage[week][dayOfWeek] = week3Last[dayOfWeek]; //set the 2darray to the correct mileage listed above
                }

                if (week == weeksTotal-2)
                {
                    mileage[week][dayOfWeek] = week2Last[dayOfWeek]; //set the 2darray to the correct mileage listed above
                }
                if (week == weeksTotal-1)
                {
                    mileage[week][dayOfWeek] = weekLast[dayOfWeek]; //set the 2darray to the correct mileage listed above
                }
            }
        }
    }
    public static void getRemainMileage()  																				         		//aggregate through the dif rows, collumn will stay at monday (0)
    {



        for (int j = 0; j<7; j++) //for loop aggregates through all columns (i.e. mon, tues, weds, etc)
        {

            for(int i = 0; i <=weeksTotal-1; i++) //for loop gets mileage for every row in column (i.e. every Monday mileage)
            {
                int dayMileage =  minMileage[j] + (int)(Math.random() *(rangeMileage[j]));

                if(mileage[i][j] == 0)
                {
                    mileage[i][j] = dayMileage;
                }
            }

        }
    }
    public static void getSaturday()
    {
        switch(weeksTotal)
        {
            case 12 : longRuns = new int [] {10,12,14,10, 14,17, 20,15,18,15,10,0}; break;
            case 13 : longRuns = new int [] {10,12,14,10, 15,12,18, 20,15,18,15,10,0}; break;
            case 14 : longRuns = new int [] {10,12,14,10, 14,18,14,18, 20,15,18,15,10,0}; break;
            case 15 : longRuns = new int [] {10,12,14,10, 14,16,12,13,18, 20,15,18,15,10,0}; break;
            case 16 : longRuns = new int [] {10,12,14,10, 14,16,18,12,13,18, 20,15,18,15,10,0}; break;
            case 17 : longRuns = new int [] {10,12,14,10, 14,16,18,10,12,13,18, 20,15,18,15,10,0}; break;
            case 18 : longRuns = new int [] {10,12,14,10, 12,14,16,18,12,14,13,18, 20,15,18,15,10,0}; break;
            case 19 : longRuns = new int [] {10,12,14,10, 12,14,16,14,18,12,13,18,16, 20,15,18,15,10,0}; break;
            case 20 : longRuns = new int [] {10,12,14,10, 12,12,14,16,14,18,12,13,18,16, 20,15,18,15,10,0}; break;

            default : break;
        }

        for (int week=0; week<weeksTotal; week++ )
        {
            int longRunforWeek = longRuns[week]; //retrieves long run noted in longRuns array
            mileage[week][5] = longRunforWeek;
        }

    }
    public static void checkMileage() //method to make mileage == the expected mileage Total
    {
        for (int week = 0; week < weeksTotal; week++)
        {
            int i = 0; //sets total week mileage back to zero

            for(int dayOfWeek = 0; dayOfWeek<7; dayOfWeek++) //for loop to add up all mileage
            {
                i = i + mileage[week][dayOfWeek];
            }

            int mileageDifference = mileageTotal[week]-i; //(subtracts the expected mileage from total mileage of the week)

            if(mileageDifference > 0) //not enough miles - expected miles > acutal miles
            {
                do
                {
                    int numberOfMax = 0; //marker to count how many mileages are at their max already
                    for(int k=0;k<7;k++)
                    {
                        if(mileage[week][k] < (minMileage[k] + rangeMileage[k]))
                        {
                            mileage[week][k] ++;
                            mileageDifference --;

                            if (mileageDifference == 0)
                            {
                                break;
                            }
                        }
                        else
                        {
                            numberOfMax ++;
                        }

                    }

                    if (numberOfMax == 7) //basically if all mileages (mon-fri) are at their max, change the long run mileage
                    {
                        int oldLongRun = mileage[week][5];
                        mileage[week][5] = mileage[week][5] + mileageDifference;
                        System.out.println("Not enough miles. Had to change the default long run for week " + (week+1) + " from " + oldLongRun + " to " + mileage[week][5]);
                        mileageDifference = 0;
                    }

                }
                while(mileageDifference>0);
            }

            if(mileageDifference < 0) //too many miles - actual miles > expected miles
            {
                do
                {
                    int numberOfMax = 0; //maarker to count how many mileages are at their min already
                    for(int k=0;k<7;k++)
                    {
                        if(mileage[week][k] > minMileage[k])
                        {
                            mileage[week][k] --;
                            mileageDifference ++; // adds one bc code on line above just subtracted one mile

                            if (mileageDifference == 0)
                            {
                                break;
                            }
                        }

                        else
                        {
                            numberOfMax ++;
                        }

                    }

                    if (numberOfMax == 7) //basically if all mileages (mon-fri) are at their min, change the long run mileage
                    {
                        int oldLongRun = mileage[week][5];
                        mileage[week][5] = mileage[week][5] - mileageDifference;
                        System.out.println("Too many miles. Had to change the default long run for week " + (week+1) + " from " + oldLongRun + " to " + mileage[week][5]);
                        mileageDifference = 0;
                    }
                }
                while(mileageDifference<0);
            }

        }
    }
    public static void saveMileage() 																							//save 2d array into a file
    {
        for(int row = 0; row < mileage.length; row++) //number of weeks
        {
            try
            {
                File mileageFile = new File ("usermileageweek" + (row+1) + ".txt"); //create a file
                PrintStream writer = new PrintStream (mileageFile); //create the writer

                for(int col = 0; col < 7; col++) //days 																//loop through array and save the elements
                {
                    writer.println(mileage[row][col]);
                }
                writer.close(); //close file connection
            }
            catch(FileNotFoundException filenotfound) //if file is not found
            {
                System.err.println("The file was not found.");
            }
        }
    }
    public static void saveMileageTotal() 																						//save array with the total mileage for the week into a file
    {

        try
        {
            File mileagetotalFile = new File ("mileagetotal.txt"); //create a file
            PrintStream writer = new PrintStream (mileagetotalFile); //create the writer

            for(int row = 0; row < mileageTotal.length; row++) //number of weeks																		//loop through array and save the elements
            {
                writer.println(mileageTotal[row]);
            }
            writer.close(); //close file connection
        }

        catch(FileNotFoundException filenotfound) //if file is not found
        {
            System.err.println("The file was not found.");
        }
    }
    public static void printMileage()
    {
        System.out.println("Week     M    T    W    TH   F    SA   S     "
                + "Total \n----------------------------------------------------");


        for(int row = 0; row < mileage.length; row++)
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

                if(mileage[row][col] == 0)	//if rest/crosstraining day, display X
                {
                    System.out.print("X");
                }

                else
                {
                    System.out.print(mileage[row][col]);//display mileage
                }


                if(mileage[row][col]<10)
                {
                    System.out.print("    ");	//if fri mileage is double digits, have one less space
                }

                else
                {
                    System.out.print("   ");
                }
            }
            System.out.print("| " + mileageTotal[row]);
            System.out.println();
        }
    }
}
