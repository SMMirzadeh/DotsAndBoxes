package User;

import Console.Play;
import DotsAndBoxes.Box;
import DotsAndBoxes.BoxesManager;
import DotsAndBoxes.PointsRel;

import java.util.Random;

public class UserColorManager {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";


    public static void setUserTextColor(User user) {

        switch (user.getUserColor()) {

            case RED:
                System.out.print(ANSI_RED);
                break;
            case GREEN:
                System.out.print(ANSI_GREEN);
                break;
            case BLUE:
                System.out.print(ANSI_BLUE);
                break;
            case YELLOW:
                System.out.print(ANSI_YELLOW);
                break;
            case PURPLE:
                System.out.print(ANSI_PURPLE);
                break;
            case CYAN:
                System.out.print(ANSI_CYAN);
                break;
            default:
                System.out.print(ANSI_RESET);
                break;

        }

    }
    public static String textColor(UserColor userColor) {

        String userTextColor = "";
        switch (userColor) {

            case RED:
                userTextColor = ANSI_RED;
                break;
            case GREEN:
                userTextColor = ANSI_GREEN;
                break;
            case BLUE:
                userTextColor = ANSI_BLUE;
                break;
            case YELLOW:
                userTextColor = ANSI_YELLOW;
                break;
            case PURPLE:
                userTextColor = ANSI_PURPLE;
                break;
            case CYAN:
                userTextColor = ANSI_CYAN;
                break;
            default:
                userTextColor = ANSI_RESET;
                break;

        }
        return userTextColor;

    }
    public static String backgroundColor(UserColor userColor) {

        String userBackgroundColor = "";
        switch (userColor) {

            case RED:
                userBackgroundColor = ANSI_RED_BACKGROUND;
                break;
            case GREEN:
                userBackgroundColor = ANSI_GREEN_BACKGROUND;
                break;
            case BLUE:
                userBackgroundColor = ANSI_BLUE_BACKGROUND;
                break;
            case YELLOW:
                userBackgroundColor = ANSI_YELLOW_BACKGROUND;
                break;
            case PURPLE:
                userBackgroundColor = ANSI_PURPLE_BACKGROUND;
                break;
            case CYAN:
                userBackgroundColor = ANSI_CYAN_BACKGROUND;
                break;
            default:
                userBackgroundColor = ANSI_RESET;
                break;

        }
        return userBackgroundColor;

    }
    public static String textColor(int point1 , int point2) {

        PointsRel pointsRel = Play.getBoxesManager().findPointsRel(point1,point2);
        if (pointsRel != null){

            return textColor(pointsRel.getUser().getUserColor());
        }else {
            return getResetColor();
        }


    }

    public static UserColor getUserColor(String color){

        UserColor userColor;
        switch (color) {

            case "red":
                return  UserColor.RED;

            case "green":
                return  UserColor.GREEN;

            case "blue":
                return  UserColor.BLUE;

            case "yellow":
                return  UserColor.YELLOW;

            case "purple":
                return  UserColor.PURPLE;

            case "cyan":
                return  UserColor.CYAN;
            default:
                return UserColor.RED;


        }


    }
    public static void setUserBackgroundColor(User user) {

        switch (user.getUserColor()) {

            case RED:
                System.out.print(ANSI_RED_BACKGROUND);
                break;
            case GREEN:
                System.out.print(ANSI_GREEN_BACKGROUND);
                break;
            case BLUE:
                System.out.print(ANSI_BLUE_BACKGROUND);
                break;
            case YELLOW:
                System.out.print(ANSI_YELLOW_BACKGROUND);
                break;
            case PURPLE:
                System.out.print(ANSI_PURPLE_BACKGROUND);
                break;
            case CYAN:
                System.out.print(ANSI_CYAN_BACKGROUND);
                break;
            default:
                System.out.print(ANSI_RESET);
                break;
        }
    }
    public static void resetColor () {

        System.out.print(ANSI_RESET);

    }

    public static String getResetColor () {

        return ANSI_RESET;

    }
}
