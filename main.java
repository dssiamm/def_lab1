package java_package;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            sw.write("\n" + login + " " + pass + " " + "1");
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
    static void ChangePass (String pass, String newpass) {
        String fileName = "user_info.txt";
        String search = pass;
        String replace = newpass;
        Path path = Paths.get(fileName);
        Charset charset = StandardCharsets.UTF_8;
        Files.write("C:\\Users\\fraps\\IdeaProjects\\defPOlab1\\src\\java_package\\user_info.txt",
                new String(Files.readAllBytes(path), charset).replace(search, replace)
                        .getBytes(charset));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String login, pass;
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
