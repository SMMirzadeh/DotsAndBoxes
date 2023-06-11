package Console;

import DotsAndBoxes.Box;
import DotsAndBoxes.BoxesManager;
import User.User;
import User.UserColorManager;
import User.UserManager;

import java.util.ArrayList;
import java.util.Scanner;


public class Play {

    private static BoxesManager boxesManager;
    private static UserManager userManager = new UserManager();;
    public static void start(User mainPlayer1 , User player2 , int n1){

        boxesManager = new BoxesManager(n1);
        Scanner scanner = new Scanner(System.in);

        User currentUser = userManager.chooseUserRandomly(mainPlayer1,player2);

        while (true){

            clearConsole();

            int p1Score = boxesManager.getPlayerScore(mainPlayer1);
            int p2Score = boxesManager.getPlayerScore(player2);
            ArrayList<User> users = boxesManager.winChecker(mainPlayer1,player2);
            if (users != null) {

                if (users.size() == 2){
                    printBoxes();
                    System.out.println("The game equalised");
                    break;
                }else {
                    printBoxes();
                    System.out.println(UserColorManager.textColor(users.get(0).getUserColor())+users.get(0).getUserName()+" wins");
                    UserColorManager.resetColor();
                    break;
                }
            }
            printScoresSection(mainPlayer1,player2);
            printBoxes();
            System.out.println(UserColorManager.textColor(currentUser.getUserColor())+ "("+currentUser.getUserName()+")");


            System.out.println("Please inter number of point 1");
            int point1 = scanner.nextInt();
            System.out.println("Please inter number of point 2");
            int point2 = scanner.nextInt();
            UserColorManager.resetColor();
            boolean isPointsRelated =  boxesManager.addPointsRelationship(point1,point2,currentUser);
            if (isPointsRelated){

                if (!(boxesManager.getPlayerScore(mainPlayer1) > p1Score || boxesManager.getPlayerScore(player2) > p2Score)) {
                    currentUser = userManager.changeToOtherUser(currentUser, mainPlayer1, player2);
                }
            }else {

                System.out.println("The relation between two points isn't define , Please try again");
            }
        }
        Menu.afterLogin();
    }
    public static void clearConsole(){

        System.out.print("\n\n\n\n \n\n\n\n \n\n\n\n \n\n\n\n \n\n\n\n \n\n\n\n");
    }
    public static void printScoresSection(User user1, User user2){

        String result = "| " +user1.getUserName()+" : "+boxesManager.getPlayerScore(user1)+"     "+user2.getUserName()+" : "+boxesManager.getPlayerScore(user2)+" |";
        System.out.print("+");
        for (int i = 0;i< (result.length())-2;i++){

            System.out.print("-");
        }
        System.out.print("+\n");
        System.out.println("| "+ UserColorManager.textColor(user1.getUserColor()) +user1.getUserName()+" : "+boxesManager.getPlayerScore(user1)+UserColorManager.getResetColor()+"     "+UserColorManager.textColor(user2.getUserColor())+user2.getUserName()+" : "+boxesManager.getPlayerScore(user2)+UserColorManager.getResetColor()+" |");
        System.out.print("+");
        for (int i = 0;i< (result.length())-2;i++){

            System.out.print("-");
        }
        System.out.print("+\n");

    }

    public static void printBoxes(){

        int start;
        int end  = boxesManager.getN2();
        int repetition = 3;
        boolean isFirstTime = true;
        int pointNum = 1;
        for (start = 1; start<=end; start++){

            Box box = boxesManager.getBoxes().get(start-1);

            if (repetition == 3 && start <= boxesManager.getN2()){
                System.out.print(" "+pointNum+" ");
                if (pointNum <10){

                    System.out.print(" ");
                }
                pointNum++;
                if (boxesManager.isPointsRelated(box.getPoints()[0],box.getPoints()[1],box)){

                    System.out.print(UserColorManager.textColor(box.getPoints()[0],box.getPoints()[1])+"━━━");
                    UserColorManager.resetColor();

                }else {
                    System.out.print("   ");
                }
                if (start%boxesManager.getN2() == 0){
                    System.out.print(" "+pointNum+" ");
                    if (pointNum <10){

                        System.out.print(" ");
                    }
                    pointNum++;
                    System.out.println();
                }

            }
            else if (repetition == 2){
                if (isFirstTime){
                    isFirstTime = false;
                    if (boxesManager.isPointsRelated(box.getPoints()[0],box.getPoints()[2],box)){
                        System.out.print(UserColorManager.textColor(box.getPoints()[0],box.getPoints()[2])+" ┃");
                        UserColorManager.resetColor();
                    }else {
                        System.out.print("  ");
                    }
                }

                if (box.getOwnersBox() != null && box.getOwnersBox().isOwned()){

                    System.out.print(" ");
                    System.out.print(UserColorManager.backgroundColor(box.getOwnersBox().getUser().getUserColor()) + "    ");
                    UserColorManager.resetColor();
                    System.out.print(" ");

                }else {
                    System.out.print("      ");
                }

                if (boxesManager.isPointsRelated(box.getPoints()[1],box.getPoints()[3],box)){
                    System.out.print(UserColorManager.textColor(box.getPoints()[1],box.getPoints()[3])+"┃");
                    UserColorManager.resetColor();
                }else {
                    System.out.print(" ");
                }
                if (start%boxesManager.getN2() == 0){
                    System.out.println();
                }
            }
            else if (repetition == 1) {
                System.out.print(" "+pointNum+" ");
                if (pointNum <10){

                    System.out.print(" ");
                }
                pointNum++;
                if (boxesManager.isPointsRelated(box.getPoints()[2],box.getPoints()[3],box)){

                    System.out.print(UserColorManager.textColor(box.getPoints()[2],box.getPoints()[3])+"━━━");
                    UserColorManager.resetColor();

                }else {
                    System.out.print("   ");
                }
                if (start%boxesManager.getN2() == 0){
                    System.out.print(" "+pointNum+" ");
                    if (pointNum <10){

                        System.out.print(" ");
                    }
                    pointNum++;
                    System.out.println();
                }

            }

            if (start%boxesManager.getN2() == 0 && start <= boxesManager.getN2()*boxesManager.getN2()){

                if (repetition > 0){

                    repetition--;
                    isFirstTime=true;
                    start =(start-boxesManager.getN2());
                }else if (start < boxesManager.getN2()*boxesManager.getN2()){
                    end +=boxesManager.getN2();
                    repetition = 3;
                }

            }
        }



    }

    public static BoxesManager getBoxesManager() {
        return boxesManager;
    }

}
