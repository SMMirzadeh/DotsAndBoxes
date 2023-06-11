package DotsAndBoxes;

import java.util.ArrayList;

public class Box {

    private int boxId;
    private int[] points = new int[4];
    private ArrayList<PointsRel> pointsRels = new ArrayList<>();
    private OwnersBox ownersBox = null;



    public int[] getPoints() {
        return points;
    }

    public void setPoints(int[] points) {
        this.points = points;
    }

    public void setPointsRel(PointsRel pointsRel) {
        pointsRels.add(pointsRel);
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public int getBoxId() {
        return boxId;
    }

    public ArrayList<PointsRel> getPointsRels() {
        return pointsRels;
    }

    public void setOwnersBox(OwnersBox ownersBox) {
        this.ownersBox = ownersBox;
    }

    public OwnersBox getOwnersBox() {
        return ownersBox;
    }
}
