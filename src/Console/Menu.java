package Console;

import DotsAndBoxes.BoxesManager;
import User.UserManager;
import User.User;
import User.UserColorManager;
import User.UserColor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Menu {
    private static User mainPlayer1;
    private static User player2;
    private static UserManager userManager = new UserManager();


    public static void start() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("+----------------------------------+");
        System.out.println("| 1_ for SignUp please press key s |");
        System.out.println("| 2_ for Login please press key l  |");
        System.out.println("+----------------------------------+");
        while(true){

            String command = scanner.next();
            if (command.equals("s")){

                signup();
                break;
            }
            else if(command.equals("l")){

                login();
                break;

            }else {

                System.out.println("Wrong command ,please try again");
            }
        }


    }
    public static void signup(){

        User user = setUserInformation();

        if (user != null){

            boolean isSignUp =  userManager.signUp(user);
            if (isSignUp){

                mainPlayer1 = user;
                afterLogin();
            }else {
                System.out.println("SingUp failed probably UserName is taken , Please try again. ");
                start();
            }
        }else {

            System.out.println("Passwords are not match , Please try again . \n\n");

            start();
        }
    }
    public static void signupPlayer2(){

        User user = setUserInformation();
        Scanner scanner = new Scanner(System.in);
        if (user != null){

            boolean isSignUp =  userManager.signUp(user);
            if (isSignUp){

                player2 = user;
                System.out.println("Please enter number of rows (number of each ⬤ in row) :");
                int n1 = scanner.nextInt();
                Play.start(mainPlayer1,player2,n1);
            }else {
                System.out.println("SingUp failed probably UserName is taken , Please try again. ");
               afterLogin();
            }
        }else {

            System.out.println("Passwords are not match , Please try again . \n\n");

            start();
        }
    }
    public static void login(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("+------------------------------------------+");
        System.out.println("| for back to main page please press key m |");
        System.out.println("+------------------------------------------+\n");

        System.out.println("Please enter your UserName");
        String userName = scanner.next();
        if (userName.equals("m")){
            mainPlayer1 = null;
            start();
        }
        System.out.println("Please enter your Password");
        String password = scanner.next();
        if (password.equals("m")){
            mainPlayer1 = null;
            start();
        }

        boolean isLoginSuccess =  userManager.logIn(userName,userManager.passwordToHash(password));
        if (isLoginSuccess ==false){

            System.out.println("Login failed ,Probably password is incorrect.");
            mainPlayer1 = null;
            start();
        }else {
            System.out.println("Login Succeed");
            mainPlayer1 = userManager.getUser(userName);
            afterLogin();
        }
    }
    public static User loginPlayer2(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("+------------------------------------------+");
        System.out.println("| for back to main page please press key m |");
        System.out.println("| for SignUp please press key s            |");
        System.out.println("+------------------------------------------+\n");

        System.out.println("+---------------------------------------------------+");
        System.out.println("| please login the second player to start the game  |");
        System.out.println("+---------------------------------------------------+\n");

        System.out.println("Please enter your UserName");
        String userName = scanner.next();
        if (userName.equals("m")){
            afterLogin();
        }else if (userName.equals("s")){

            signupPlayer2();
        }
        System.out.println("Please enter your Password");
        String password = scanner.next();
        if (userName.equals("m")){
            afterLogin();
        }else if (userName.equals("s")){

            signupPlayer2();
        }

        boolean isLoginSuccess =  userManager.logIn(userName,userManager.passwordToHash(password));
        if (isLoginSuccess ==false){

            System.out.println("Login failed ,Probably password is incorrect.");
            afterLogin();
        }else {

            System.out.println("Login Succeed");
            return userManager.getUser(userName);

        }

        return null;
    }
    public static void afterLogin(){

        System.out.println("Welcome Mr/Ms "+mainPlayer1.getUserName());

        Scanner scanner = new Scanner(System.in);

        System.out.println("+-------------------------------------------------+");
        System.out.println("| for logout from your account please press key l |");
        System.out.println("| for edit your information please press key e    |");
        System.out.println("| for delete your account please press key d      |");
        System.out.println("| for start the game please press key s           |");
        System.out.println("| for see games histories please press key h      |");
        System.out.println("+-------------------------------------------------+\n");

        while(true){

            String command = scanner.next();
            if (command.equals("e")){

                User tempUser = setUserInformation();

                if (!tempUser.getUserName().equals("")){

                    mainPlayer1.setUserName(tempUser.getUserName());
                }
                if (!tempUser.getPassword().equals("d41d8cd98f00b204e9800998ecf8427e")){

                    mainPlayer1.setPassword(tempUser.getPassword());
                }
                if (!tempUser.getLastName().equals("")){

                    mainPlayer1.setLastName(tempUser.getLastName());
                }

                    mainPlayer1.setUserColor(tempUser.getUserColor());


                boolean isEdited =  userManager.editUser(mainPlayer1);
                if (isEdited){
                    System.out.println("+-------------------------------------------------+");
                    System.out.println("| for edit your information please press key e    |");
                    System.out.println("| for delete your account please press key d      |");
                    System.out.println("| for logout from your account please press key l |");
                    System.out.println("| for start the game please press key s           |");
                    System.out.println("+-------------------------------------------------+\n");

                    System.out.println("Edit your account Succeed.");

                }else {

                    System.out.println("Edit account failed ,please try again.");

                }

            } else if(command.equals("d")){

                boolean isDeleted = userManager.deleteUser(mainPlayer1.getId());

                if (isDeleted){

                    System.out.println("Delete your account Succeed.");
                    mainPlayer1 = null;
                    start();
                    break;
                }
                else {

                    System.out.println("Delete account failed ,please try again.");
                }

            }else if(command.equals("l")){

                mainPlayer1 = null;
                start();
                break;

            }else if(command.equals("s")){


                player2 = loginPlayer2();
                if (player2 == null){
                    System.out.println("User not found");
                    afterLogin();
                }else if(player2.getId() == mainPlayer1.getId()){

                    System.out.println("You must login with another account , Please try again");
                    player2 = null;
                    afterLogin();
                }

                System.out.println("Please enter number of rows (number of each ⬤ in row) :");
                int n1 = scanner.nextInt();
                Play.start(mainPlayer1,player2,n1);

            }else if(command.equals("h")){

                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                    String histories = getGamesHistories();
                    if (!histories.equals("")){

                        String[] historiesInArray = histories.split("\n");
                        for (String history : historiesInArray){

                            System.out.println(history);
                        }
                        while (true){

                            System.out.println("For back to menu please press key m");
                            String com = scanner.next();
                            if (com.equals("m")){

                                afterLogin();
                            }else {

                                System.out.println("Wrong command ,Please try again");
                            }
                        }

                    }else {

                        System.out.println("There is no game history.");
                        afterLogin();
                    }
            }
            else {

                System.out.println("Wrong command ,please try again");
            }
        }

    }
    public static User setUserInformation(){

        User user = new User();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter UserName :");
        user.setUserName(scanner.nextLine());

        System.out.println("Please enter LastName :");
        user.setLastName(scanner.nextLine());

        System.out.println("Please enter yor profile color :");
        System.out.println(UserColorManager.textColor(UserColor.RED)+"red|"+UserColorManager.textColor(UserColor.GREEN)+"green|"+UserColorManager.textColor(UserColor.BLUE)+"blue|"+UserColorManager.textColor(UserColor.YELLOW)+"yellow|"+UserColorManager.textColor(UserColor.PURPLE)+"purple|"+UserColorManager.textColor(UserColor.CYAN)+"cyan");
        UserColorManager.resetColor();
        user.setUserColor(UserColorManager.getUserColor(scanner.nextLine()));

        String password1 = "";
        System.out.println("Please enter your new Password :");
        password1 = scanner.nextLine();

        String password2 = "";
        System.out.println("Please enter Password again :");
        password2 = scanner.nextLine();

        if (password1.equals(password2)){

            user.setPassword(userManager.passwordToHash(password1));
            return user;

        }else {

            return null;
        }

    }
    public static String getGamesHistories(){


        try {
            File file = new File(BoxesManager.getPath());
            if(!file.exists()){

                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String histories = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(BoxesManager.getPath()));
            String line = "";
            while ((line = reader.readLine()) != null){

                histories = histories + "\n" + line;
            }
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return histories.trim();
    }

}
