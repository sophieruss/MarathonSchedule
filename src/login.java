import java.io.*;
import java.util.*;
public class login {

    private static String nameInput, nameFile;
    private static boolean mainBoolean = false;
    public static boolean main()
    {
        getNameInput();
        getNameFile();
        if(nameInput.contentEquals(nameFile))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static void getNameInput()
    {
        Scanner scan = new Scanner (System.in);
        System.out.println("Welcome back! Please enter your name: ");
        nameInput = (((scan.nextLine()).trim()).toUpperCase());								 //sets nameInput to user input in caps lock and takes out any spaces at end
    }
    public static void getNameFile()
    {
        try
        {
            File userFile = new File ("userinfo.txt");
            Scanner scan = new Scanner (userFile);
            nameFile = scan.nextLine();
            scan.close();
        }
        catch (FileNotFoundException filenotfound)
        {
            System.out.println("File not Found");
        }
    }
}

