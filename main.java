package java_package;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;



public class main {
    static void MainMenu () {
        System.out.println("'1' to sign in");
        System.out.println("'2' to sign up");
        System.out.println("'3' to look special information");
        System.out.println("'4' to exit");
    }
    static void SpecialInfo () {
        System.out.println("author: Dmitriy Snegovoy\nMy task: Відсутність символів, що повторюються.");
    }
    static boolean SignIn (String login, String pass) {
        boolean result = false;
        String temp1 = "";
        String temp2 = "-";
        try (BufferedReader fr = new BufferedReader(new FileReader("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\user_info.txt"))) {
            String c;
            while ((c = fr.readLine()) != null)
                    for(int i = 0; i < c.length(); i++) {
                        if (c.charAt(i) == ' ')
                            break;
                        if (c.charAt(i) != login.charAt(i))
                            break;
                        if (i == login.length() - 1)
                            temp1 = c;
                    }
            } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
        try (BufferedReader fr = new BufferedReader(new FileReader("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\user_info.txt"))) {
            String c;
            while ((c = fr.readLine()) != null)
                for(int i = login.length() + 1; i < c.length(); i++) {
                    if (c.charAt(i) == ' ')
                        break;
                    if(pass.length() != c.length() - login.length() - 3)
                        break;
                    if (c.charAt(i) != pass.charAt(i - login.length() - 1))
                        break;
                    if (i == login.length() + pass.length())
                        temp2 = c;
                }
        } catch (IOException f) {
            System.out.println("I/O Error: " + f);
        }
        if (temp1.equals(temp2))
            result = true;
        return result;
        }
    static void SignUp (String login, String pass) {
        try{
            FileWriter sw = new FileWriter("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\user_info.txt",true);
            sw.write("\n");
            sw.write(login + " " + pass + " " + "1");
            sw.close();
        }catch(Exception d){
            System.out.print(d.getMessage());
        }
    }
    static boolean ReturnPermission (String login) {
        boolean result = false;
        try (BufferedReader fr = new BufferedReader(new FileReader("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\user_info.txt"))) {
            String c;
            while ((c = fr.readLine()) != null)
                for(int i = 0; i < c.length(); i++) {
                    if (c.charAt(i) == ' ')
                        break;
                    if (c.charAt(i) != login.charAt(i))
                        break;
                    if (i == login.length() - 1 && c.charAt(c.length() - 1) == '1')
                            result = true;
                }
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
        return result;
    }
    static void ChangePass (String pass, String newpass, String login) {
        int j = 0;
        String[] mass = new String[30];
        String temp2 = "-";
        try (BufferedReader fr = new BufferedReader(new FileReader("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\user_info.txt"))) {
            String c;
            while ((c = fr.readLine()) != null) {
                for (int i = login.length() + 1; i < c.length(); i++) {
                    if (c.charAt(i) == ' ')
                        break;
                    if (c.charAt(i) != pass.charAt(i - login.length() - 1))
                        break;
                    if (i == login.length() + pass.length())
                        temp2 = c;
                }
                if (temp2 == "-")
                    mass[j] = c;
                if(temp2 == c) {
                    c = login + " " + newpass + " " + "1";
                    mass[j] = c;
                    temp2 = "-";
                }
                j++;
            }
        } catch (IOException f) {
            System.out.println("I/O Error: " + f);
        }
        try{
            FileWriter sw = new FileWriter("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\user_info.txt");
            BufferedWriter out1 = new BufferedWriter(sw);
            out1.write("");
            out1.close();
        }catch(Exception d){
            System.out.print(d.getMessage());
        }
        try{
            FileWriter sw = new FileWriter("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\user_info.txt",true);
            for (int i = 0; i < j; i++)
                sw.write(mass[i] + "\n");
                sw.close();
        }catch(Exception d){
            System.out.print(d.getMessage());
        }
    }
    static void showlist () {
        try (BufferedReader fr = new BufferedReader(new FileReader("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\user_info.txt"))) {
            String c;
            while ((c = fr.readLine()) != null)
                for (int i = 0; i < c.length(); i++) {
                    System.out.print(c.charAt(i));
                    if (c.charAt(i) == ' ')
                        break;
                }
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String login, pass, newpass;
        int sww;
        int k=0;
        do {
            MainMenu();
            sww = sc.nextInt();
            switch (sww) {
                case 1:
                    do {
                        System.out.println("Enter yoor login: ");
                        login = sc.next();
                        System.out.println("Enter your password: ");
                        pass = sc.next();
                        if (SignIn(login, pass)) {
                            System.out.println("Hello " + login);
                            System.out.println("'1' to change your pass");
                            System.out.println("'2' to show list of users(admin only)");
                            int dw;
                            dw = sc.nextInt();
                            switch (dw) {
                                case 1:
                                    String passcheck;
                                    System.out.println("Enter your pass:");
                                    passcheck = sc.next();
                                    if(pass.equals(passcheck)) {
                                        System.out.println("Enter new pass:");
                                        newpass = sc.next();
                                        ChangePass(pass, newpass, login);
                                    }
                                    else
                                        System.out.println("Wrong pass");
                                case 2:
                                    if (SignIn(login, pass) && login.equals("admin")) {
                                        showlist();
                                    }
                            }
                        }
                        k++;
                    } while (k != 3);
                    if (k == 3)
                        sww = 4;
                    break;
                case 2:
                    System.out.println("Enter yoor login: ");
                    login = sc.next();
                    System.out.println("Enter your password: ");
                    pass = sc.next();
                    SignUp(login, pass);
                    break;
                case 3:
                    SpecialInfo();
                    break;
            }
        } while (sww != 4);
    }
}
