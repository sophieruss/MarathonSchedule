import java.util.*;
public class main {

    public static void main(String[] args)
    {
        welcome();
    }
    public static void welcome()
    {
        Scanner scan = new Scanner(System.in);
        String response = " ";

        System.out.println("Are you a new user? ('YES' or 'NO')");
        response = (scan.nextLine()).toUpperCase();

        if(response.contentEquals("YES"))
        {
            firstTime();
        }

        else if(response.contentEquals("NO"))
        {
            notFirstTime();
        }
        else
        {
            System.out.println("Not a valid response. Please try again.");
            welcome();
        }
        scan.close();

    }
    public static void firstTime()
    {
        user.newUser();
        System.out.println("\n----------------------------------------------------\n");
        weekMileage.mileageMain();
        System.out.println("This is your training outline:\n");
        weekMileage.printMileage();
    }
    public static void notFirstTime()
    {
        if(login.main() == true)
        {
            System.out.println("Your information has been located. \n");
            dailySequence.main();
        }
        else
        {
            System.out.println("You are not a user in this sytem. Please try again");
        }
    }
}

