package Controller;

import Model.*;
import View.GamePanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class Intersection {
    GamePanel gamePanel;

    private Polygon squarantine2;
    private Polygon trigorath2;
    private boolean intersect;
    private static Point2D intersectionPoint;
    public Intersection(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
    }

    public Direction shotIntersectsFrame(ShotGun shotGun){

        if(shotGun.getX()>= GamePanel.getFRAME_WIDTH()){
            shotGun.setVisible(false);
            return Direction.RIGHT;
        } if(shotGun.getY() >= GamePanel.getFRAME_HEIGHT()){
            shotGun.setVisible(false);
            return Direction.DOWN;
        } if(shotGun.getX() <= 0){
            shotGun.setVisible(false);
            return Direction.LEFT;
        } if(shotGun.getY() <= 0){
            shotGun.setVisible(false);
            return Direction.UP;
        }
        return null;
    }
    public void shotIntersectsSquarantine(){
        //check if a shot intersects with squarantine
        // if so decrease its HP
        // if its HP reaches 0 , it dies
        for(int j = 0; j< GamePanel.getSquarantine().size(); j++) {
            Squarantine squarantine = GamePanel.getSquarantine().get(j);
            squarantine2 = new Polygon(squarantine.getxPoints(),squarantine.getyPoints(),3);
            for (int i = 0; i < ShotGun.getShots().size(); i++) {
                ShotGun shot = ShotGun.getShots().get(i);
                if(shot.isVisible()){
                    if(squarantine2.intersects(shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight())) {

                        shot.setVisible(false);
                        squarantine.decreaseHP();
                    }

                }
            }
        }
    }
    public void shotIntersectsTrigorath(){
        //check if a shot intersects with trigorath
        // if so decrease its HP
        // if its HP reaches 0 , it dies

        for(int j = 0; j< GamePanel.getTrigoraths().size(); j++) {
            Trigorath trigorath = GamePanel.getTrigoraths().get(j);
            trigorath2 = new Polygon(trigorath.getxPoints(),trigorath.getyPoints(),3);
            for (int i = 0; i < ShotGun.getShots().size(); i++) {
                ShotGun shot = ShotGun.getShots().get(i);
                if(shot.isVisible()){
                if(trigorath2.intersects(shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight())) {

                    shot.setVisible(false);
                    trigorath.decreaseHP();
                }

                }
            }
        }

    }
    public void epsilonIntersectsCollectible(){
        // check if epsilon intersects with collectibles
        // if so add to epsilons XP

        Epsilon epsilon = GamePanel.getEpsilon();

        for(Trigorath trigorath : GamePanel.getTrigoraths()){
            if(trigorath.isShowCollectibles()){

                for(int i=0;i<trigorath.getCollectibles().size();i++){
                    Collectible collectible = trigorath.getCollectibles().get(i);
                    double xDistance = Math.abs(epsilon.getxCenter()-collectible.getXCenter());
                    double yDistance = Math.abs(epsilon.getyCenter()-collectible.getYCenter());
                    double distance = Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance,2));
                    if(distance<=collectible.getRadius()+epsilon.getRadius()){

                        epsilon.increaseXP();
                        trigorath.getCollectibles().remove(collectible);

                    }

                }
            }
        }

        for(Squarantine squarantine : GamePanel.getSquarantine()){
            if(squarantine.isShowCollectibles()){

                for(int i=0;i<squarantine.getCollectibles().size();i++){
                    Collectible collectible = squarantine.getCollectibles().get(i);
                    double xDistance = Math.abs(epsilon.getxCenter()-collectible.getXCenter());
                    double yDistance = Math.abs(epsilon.getyCenter()-collectible.getYCenter());
                    double distance = Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance,2));
                    if(distance<=collectible.getRadius()+epsilon.getRadius()){

                        epsilon.increaseXP();
                        squarantine.getCollectibles().remove(collectible);

                    }

                }
            }
        }
    }
    public void enemyIntersection(){
        ArrayList<Point2D> points = new ArrayList<>();
        for(Trigorath trigorath : GamePanel.getTrigoraths()){
            Polygon trigorath2 = new Polygon(trigorath.getxPoints(), trigorath.getyPoints(), 3);
            if(!trigorath.isDead()){
            for(Trigorath trigorath1 : GamePanel.getTrigoraths()) {
                if(!trigorath1.isDead()) {
                    if (!trigorath.equals(trigorath1)) {

                        for (int i = 0; i < 3; i++) {
                            points.add(new Point2D.Double(trigorath1.getxPoints()[i], trigorath1.getyPoints()[i]));
                        }
                    }
                }
            }
            for(Squarantine squarantine : GamePanel.getSquarantine()){
                if(!squarantine.isDead()){
                    for(int j=0;j<4;j++){
                        points.add(new Point2D.Double(squarantine.getxPoints()[j],squarantine.getyPoints()[j]));
                    }
                }
            }
            }
            for (Point2D point2D : points) {
                if (trigorath2.contains(point2D)) {
                    intersectionPoint = point2D;
                }
            }
        }
    }

    public static Point2D getIntersectionPoint() {
        return intersectionPoint;
    }

    public static void setIntersectionPoint(Point2D intersectionPoint) {
        Intersection.intersectionPoint = intersectionPoint;
    }
    //    public void epsilonIntersectsEnemy(){
//
//        Epsilon epsilon = GamePanel.getEpsilon();
//
//        for(Trigorath trigorath : GamePanel.getTrigoraths()){
//            for (int i = 0; i < trigorath.getxPoints().length; i++) {
//
//                double distance = Math.sqrt(Math.pow(epsilon.getxCenter() - trigorath.getxPoints()[i], 2) + Math.pow(epsilon.getyCenter() - trigorath.getyPoints()[i], 2));
//
//                if (distance <= epsilon.getRadius()) {
//                    // Intersection with trigorath's vertex detected
//                    //     epsilon.decreaseHP();
//                    intersect = true;
//
//                }
//            }
//
//            for (int i = 0; i < trigorath.getxPoints().length; i++) {
//
//                int x1 = trigorath.getxPoints()[i];
//                int y1 = trigorath.getyPoints()[i];
//                int x2 = trigorath.getxPoints()[(i + 1) % trigorath.getxPoints().length];
//                int y2 = trigorath.getyPoints()[(i + 1) % trigorath.getyPoints().length];
//
//
//
//                double dx = x2 - x1;
//                double dy = y2 - y1;
//                double dr = Math.sqrt(dx * dx + dy * dy);
//                double D = x1 * y2 - x2 * y1;
//                double discriminant = epsilon.getRadius()  * dr * dr - D * D;
//
//                if (discriminant >= 0) {
//                    intersect = true;
//                    // Intersection with trigorath's edge detected
//
//                }
//
//            }
//        }
//        for(Squarantine squarantine : GamePanel.getSquarantine()){
//            for (int i = 0; i < squarantine.getxPoints().length; i++) {
//
//                double distance = Math.sqrt(Math.pow(epsilon.getxCenter() - squarantine.getxPoints()[i], 2) + Math.pow(epsilon.getyCenter() - squarantine.getyPoints()[i], 2));
//
//                if (distance <= epsilon.getRadius()) {
//                    // Intersection with squarantine's vertex detected
//                    //    epsilon.decreaseHP();
//                    intersect = true;
//
//                }
//
//            }
//
//            for (int i = 0; i < squarantine.getxPoints().length; i++) {
//
//                int x1 = squarantine.getxPoints()[i];
//                int y1 = squarantine.getyPoints()[i];
//                int x2 = squarantine.getxPoints()[(i + 1) % squarantine.getxPoints().length];
//                int y2 = squarantine.getyPoints()[(i + 1) % squarantine.getyPoints().length];
//
//
//
//                double dx = x2 - x1;
//                double dy = y2 - y1;
//                double dr = Math.sqrt(dx * dx + dy * dy);
//                double D = x1 * y2 - x2 * y1;
//                double discriminant = epsilon.getRadius()  * dr * dr - D * D;
//
//                if (discriminant >= 0) {
//                    intersect = true;
//                    // Intersection with squarantine's edge detected
//
//                }
//
//            }
//        }
//    }




}
