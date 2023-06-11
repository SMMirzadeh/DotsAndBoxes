package User;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UserManager {

    private final String path = "UsersData.txt";

    public UserManager(){


        //Creating UsersData.txt file into the path direction
        try {
            File usersData = new File(path);
            usersData.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



    }
    public boolean signUp(User user){


        if (userExistence(user.getUserName()) == false ){

            String usersData = getAllUsers();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(path));

                writer.write(usersData+user.getId()+"//"+user.getUserName()+"//"+user.getPassword()+"//"+user.getLastName()+"//"+user.getUserColor());
                writer.close();
                return  true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;

            }
        }else {

            return false;

        }




    }
    public boolean userExistence(String userName){

        String usersData = getAllUsers();
        boolean isUserExist = false;
        if (!usersData.equals("")){

            String[] users = usersData.split("\n");
            for (int i = 0 ; i<users.length ; i++){

                String[] userInText = users[i].split("//");

                if (userName.equals(userInText[1])){

                    isUserExist = true;
                    break;
                }

            }
        }


        return isUserExist;


    }
    public boolean logIn(String userName , String hashedPassword){

        String usersData = getAllUsers();
        boolean isUserLogin = false;
        if(!usersData.equals("")){

            String[] users = usersData.split("\n");
            for (int i = 0 ; i<users.length ; i++){

                String[] userInText = users[i].split("//");
                if (userName.equals(userInText[1])&&hashedPassword.equals(userInText[2])){

                    isUserLogin = true;
                    break;
                }

            }

        }

        return isUserLogin;
    }
    public boolean editUser(User user){
        String usersData = getAllUsers();
        String newUsersData = "";
        String[] users = usersData.split("\n");
        boolean isUserEdited = false;
        for (int i = 0 ; i<users.length ; i++){

            String[] userInText = users[i].split("//");
            if (user.getId() != Integer.parseInt(userInText[0])){

                // If current user was last user exist ,don't add "/n"
                if (i==(users.length-1)){

                    newUsersData +=users[i];
                }else {
                    newUsersData +=users[i]+"\n";
                }

            }else {
                userInText[0] = Integer.toString(user.getId());
                userInText[1]=user.getUserName();
                userInText[2]=user.getPassword();
                userInText[3]=user.getLastName();
                userInText[4]= String.valueOf(user.getUserColor());

                // If current user was last user exist ,don't add "/n"
                if (i==(users.length-1)){

                    newUsersData +=String.join("//",userInText);
                }else {
                    newUsersData +=String.join("//",userInText)+"\n";
                }
                isUserEdited = true;
            }



        }
        if (!usersData.equals(newUsersData)){

            setAllUsers(newUsersData);
        }
        return isUserEdited;



    }
    public boolean deleteUser(int userId){

        String usersData = getAllUsers();
        String newUsersData = "";
        String[] users = usersData.split("\n");
        boolean isUserDeleted = false;
        for (int i = 0 ; i<users.length ; i++){

            String[] userInText = users[i].split("//");
            if (userId != Integer.parseInt(userInText[0])){

                // If current user was last user exist ,don't add "/n"
                if (i==(users.length-1)){

                    newUsersData +=users[i];
                }else {
                    newUsersData +=users[i]+"\n";
                }

            }else {

                isUserDeleted = true;
                // If deleted user was last user exist , delete "\n" at the end of string
                if (i==(users.length-1)){

                    newUsersData.trim();
                }
            }



        }
        if (!usersData.equals(newUsersData)){

            setAllUsers(newUsersData);
        }
        return isUserDeleted;


    }
    public String getAllUsers(){
        String usersInfo = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = reader.readLine()) != null){

                usersInfo +=line+"\n";
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usersInfo;
    }
    public void setAllUsers(String users){


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            writer.write(users);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public User getUser(int userId){

        String usersData = getAllUsers();
        User user = new User();
        String[] users = usersData.split("\n");
        for (int i = 0 ; i<users.length ; i++){

            String[] userInText = users[i].split("//");
            if (userId == Integer.parseInt(userInText[0])){


                user.setId(Integer.parseInt(userInText[0]));
                user.setUserName(userInText[1]);
                user.setPassword(userInText[2]);
                user.setLastName(userInText[3]);
                user.setUserColor(UserColor.valueOf(userInText[4]));
                break;
            }


        }
        return user;

    }
    public User getUser(String userName){

        String usersData = getAllUsers();
        User user = new User();
        String[] users = usersData.split("\n");
        for (int i = 0 ; i<users.length ; i++){

            String[] userInText = users[i].split("//");

            if (userName.equals(userInText[1])){

                user.setId(Integer .parseInt(userInText[0]));
                user.setUserName(userInText[1]);
                user.setPassword(userInText[2]);
                user.setLastName(userInText[3]);
                user.setUserColor(UserColor.valueOf(userInText[4]));
                break;

            }


        }
        return user;

    }
    public String passwordToHash(String password){

        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] massageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1,massageDigest);
            String hashPassword = no.toString(16);
            while (hashPassword.length() <32){
                hashPassword = "0"+hashPassword;
            }
            result = hashPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    public User chooseUserRandomly(User user1 , User user2){

        Random random = new Random();
        int turnNumber = random.nextInt(1,101);
        if (turnNumber <=50){

            return user1;

        }else {

            return user2;
        }


    }
    public User changeToOtherUser(User currentUser , User user1 , User user2){

        if (currentUser.getId() == user1.getId()){

            return user2;
        }else {

            return user1;
        }
    }

}
