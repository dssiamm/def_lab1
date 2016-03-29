package java_package;

import java.util.Scanner;
import java.io.*;
/**
 * Created by fraps on 29.03.2016.
 */
public class StartForm {

    public static boolean SignIn (String[] loginString, String[] passString, Scanner sc) {
        boolean SigIn = false;
        System.out.println("Enter your login:");
        String login = sc.next();
        System.out.println("Enter your pass:");
        String pass = sc.next();
        for (int i = 0; i < loginString.length; i++) {
            if (login == loginString[i] && pass == passString[i] && login != "" && pass != "") {
                SigIn = true;
                System.out.println("Hello " + login + "=)");
                break;
            }
            if (i == loginString.length - 1)
                System.out.println("Wrong login or pass!");
        }
        return SigIn;
    }

    public static void ShowStartForm () {
        System.out.println("Enter '1' to sign in");
        System.out.println("Enter '2' to sign up");
        System.out.println("Enter '3' to look special information");
        System.out.println("Enter '4' to exit");
    }

    public static void SignUp (String[] loginString, String[] passString, Scanner sc) {
        String login = "", pass = "", passcheck = "";
        boolean k = true;
        System.out.println("Enter your login:");
        do {
            login = sc.next();
            if(login.length() >=12 || login.length() <= 6)
                System.out.println("login length need to be between 5 and 13");
        } while (login.length() >=12 || login.length() <= 6);

        System.out.println("Enter your pass:");
        do {
            pass = sc.next();
            for(int i = 0; i < pass.length() - 1; i++)
                for(int j = 0; j < pass.length() - 1; j++) {
                    if (i == j)
                        continue;
                    if (pass.charAt(i) == pass.charAt(j)) {
                        k = false;
                        System.out.println("Dont use one symbol twice!");
                    }
                }
            if (pass.length() >= 12 || pass.length() <= 6) {
                k = false;
                System.out.println("login length need to be between 5 and 13");
            }
        } while ( (pass.length() >= 12 || pass.length() <= 6) && !k);

        System.out.println("Enter your pass once more to continue:");
        do {
            passcheck = sc.next();
            if(pass != passcheck)
                System.out.println("Not the same pass");
        } while (pass != passcheck);

        for(int i = 0; i < loginString.length; i++) {
            if (loginString[i] != "")
                continue;
            else {
                loginString[i] = login;
                passString[i] = pass;
            }
        }

        try(FileWriter writer = new FileWriter("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\login.txt"))
        {
            for ( int i = 0; i < loginString)
            writer.write(loginString[i]);
            // запись по символам
            writer.append('\n');
            writer.append('E');

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean admin;
        String login, pass, passcheck;
        int chose;
        String[] passString = new String[100];
        String[] loginString = new String[100];

        try (FileReader fr = new FileReader("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\login.txt")) {
            int c;
            String temp = "";
            int i = 0;
            while ((c = fr.read()) != -1)
                if ( (char) c == ' ') {
                    loginString[i] = temp;
                    temp = "";
                    i++;
                }
                else
                    temp = temp + (char) c;
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
        try (FileReader fr = new FileReader("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\pass.txt")) {
            int c;
            String temp = "";
            int i = 0;
            while ((c = fr.read()) != -1)
                if ( (char) c == ' ') {
                    passString[i] = temp;
                    temp = "";
                    i++;
                }
                else
                    temp = temp + (char) c;
        } catch (IOException d) {
            System.out.println("I/O Error: " + d);
        }

        ShowStartForm();
        chose = sc.nextInt();

        switch (chose) {
            case 1:
                SignIn(loginString, passString, sc);
                break;
            case  2:
                System.out.println("Enter your login:");
                login = sc.next();
                System.out.println("Enter your pass:");
                pass = sc.next();
                System.out.println("Enter your pass once more to continue:");
                passcheck = sc.next();
                break;
            case 4:
                break;
        }
    }
}
