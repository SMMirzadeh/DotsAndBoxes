package DotsAndBoxes;

import Console.Play;
import User.User;
import User.UserColorManager;

import java.io.*;
import java.util.ArrayList;

public class BoxesManager {

    private int n1;
    private final int n2;
    private static final String path = "GamesHistories.txt";
    private ArrayList<Box> boxes;

    public BoxesManager(int n1) {

        this.n1 = n1;
        this.n2 = n1 - 1;
        boxes = new ArrayList<>(n2*n2);

        for (int i = 0; i < n2 * n2; i++) {

            Box box = new Box();
            int point;
            if ((i + 1) % n2 == 0) {

                point = (i) / n2 + (i + 1);

            } else {
                point = (i + 1) / n2 + (i + 1);
            }
            box.setPoints(new int[]{point, point + 1, point + n1, point + n1 + 1});
            box.setBoxId(i);
            boxes.add(i,box);
        }

        try {
            File file = new File(BoxesManager.getPath());
            if(!file.exists()){

                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Box findBox(int point1, int point2) {

        for (Box box : boxes) {

            for (int i = 0; i < 4; i++) {
                if (box.getPoints()[i] == point1) {
                    for (int j = 0; j < 4; j++) {
                        if (box.getPoints()[j] == point2) {
                            return box;
                        }
                    }
                }

            }

        }

        return null;

    }
    public PointsRel findPointsRel(int point1, int point2) {

        Box box = findBox(point1,point2);
        if (isPointsRelated(point1,point2,box)) {

            for (PointsRel item : box.getPointsRels()) {

                if ((item.getPoint1() == point1 || item.getPoint1() == point2) && (item.getPoint2() == point2 || item.getPoint2() == point1)) {

                    return item;

                }

            }

        }
            return null;
    }

    public boolean addPointsRelationship(int point1, int point2 ,User user){

        Box box = findBox(point1,point2);
        if (box == null){
            return false;
        }

        PointsRel pointsRel = new PointsRel();
        pointsRel.setPoint1(point1);
        pointsRel.setPoint2(point2);
        pointsRel.setUser(user);
        box.setPointsRel(pointsRel);

        boxes.set(box.getBoxId(),box);

        for (Box item : boxes){

            if (isBoxOwned(item)){
                if (item.getOwnersBox() == null){
                    OwnersBox ownersBox = new OwnersBox();
                    ownersBox.setUser(user);
                    ownersBox.setOwned(true);
                    item.setOwnersBox(ownersBox);
                    boxes.set(item.getBoxId(),item);
                }

            }
        }



        return true;
    }

    public boolean isPointsRelated(int point1, int point2 , Box originalBox){


        Box box = findBox(point1,point2);

        if (box == null || !(box.getBoxId() == originalBox.getBoxId())){

            return false;
        }
        for (PointsRel item : box.getPointsRels()){

            if ((item.getPoint1() == point1 || item.getPoint1() == point2)&&(item.getPoint2() == point2 || item.getPoint2() == point1)){

                return true;

            }

        }
        return false;

    }
    public boolean isPointsRelated(int point1, int point2){


        Box box = findBox(point1,point2);

        if (box == null){

            return false;
        }
        for (PointsRel item : box.getPointsRels()){

            if ((item.getPoint1() == point1 || item.getPoint1() == point2)&&(item.getPoint2() == point2 || item.getPoint2() == point1)){

                return true;

            }

        }
        return false;

    }

    public boolean isBoxOwned(Box box){

        if (!isPointsRelated(box.getPoints()[0],box.getPoints()[1])){

            return false;
        }else if (!isPointsRelated(box.getPoints()[0],box.getPoints()[2])){

            return false;
        }else if (!isPointsRelated(box.getPoints()[1],box.getPoints()[3])){

            return false;
        }else if (!isPointsRelated(box.getPoints()[2],box.getPoints()[3])){

            return false;
        }else {
            return true;
        }

    }

    public int getPlayerScore(User user){

        int score = 0;

        for (Box box : boxes){

            if (box.getOwnersBox() != null){
                if (box.getOwnersBox().getUser().getId() == user.getId()){
                    score++;
                }

            }

        }
        return score;

    }
    public ArrayList<User> winChecker(User user1 , User user2){

        int scorePlayer1 = getPlayerScore(user1);
        int scorePlayer2 = getPlayerScore(user2);
        ArrayList<User> users = new ArrayList<>();

        // َThe game equalised
        if ((n2%2) == 0 && scorePlayer1 == ((n2*n2)/2)&&scorePlayer2 == ((n2*n2)/2)){

            users.add(user1);
            users.add(user2);
            addGamesHistory("<< "+UserColorManager.textColor(user1.getUserColor())+" "+user1.getUserName() + " : " +scorePlayer1+UserColorManager.getResetColor()+"     "+UserColorManager.textColor(user2.getUserColor())+" "+user2.getUserName() + " : " +scorePlayer2+UserColorManager.getResetColor()+"     "+" And the game equalised"+" >>");
            return users;

        }
        //User 1 win
        else if(scorePlayer1 >= ((n2*n2)/2)+1){

            users.add(user1);
            addGamesHistory("<< "+UserColorManager.textColor(user1.getUserColor())+" "+user1.getUserName() + " : " +scorePlayer1+UserColorManager.getResetColor()+"     "+UserColorManager.textColor(user2.getUserColor())+" "+user2.getUserName() + " : " +scorePlayer2+UserColorManager.getResetColor()+"     "+"And "+user1.getUserName()+" wins"+" >>");
            return users;
        }
        //User 2 win
        else if(scorePlayer2 >= ((n2*n2)/2)+1){

            users.add(user2);
            addGamesHistory("<< "+UserColorManager.textColor(user1.getUserColor())+" "+user1.getUserName() + " : " +scorePlayer1+UserColorManager.getResetColor()+"     "+UserColorManager.textColor(user2.getUserColor())+" "+user2.getUserName() + " : " +scorePlayer2+UserColorManager.getResetColor()+"     "+"And "+user2.getUserName()+" wins"+" >>");
            return users;
        }
        //Game still running (no winner yet)
        else {

            return null;
        }


    }

    public int getN2() {
        return n2;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void addGamesHistory(String history){

        String histories = getGamesHistories();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write((histories+"\n"+history));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static String getGamesHistories(){

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
    public static String getPath() {
        return path;
    }

    public int getN1() {
        return n1;
    }
}


