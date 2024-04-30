package Controller;

import Model.*;
import View.GamePanel;
import myproject.MyProject;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Intersection {
    GamePanel gamePanel;

    private Polygon squarantine2;
    private Polygon trigorath2;
    private boolean intersect;
    private static Point2D intersectionPoint;
    private static Point2D intersectionPoint2;
    private static Point2D intersectionPoint3;
    private static Point2D intersectionPoint4;
    private static Point2D intersectionPoint5;
    private static Point2D intersectionPoint6;
    private static Point2D intersectionPoint7;
    private static Point2D intersectionPoint8;
    private static Point2D intersectionPoint9;

    public Intersection(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
    }

    public Direction shotIntersectsFrame(ShotGun shotGun) {

      //    if(shotGun.isVisible()) {
        if (shotGun.getX() >= GamePanel.getFRAME_WIDTH()) {
            return Direction.RIGHT;
        }
        if (shotGun.getY() >= GamePanel.getFRAME_HEIGHT()) {
         //   if(shotGun.isVisible()) {
            //    shotGun.setVisible(false);
                return Direction.DOWN;

        }
        if (shotGun.getX() <= 0) {
         //   if(shotGun.isVisible()) {
            //    shotGun.setVisible(false);
                return Direction.LEFT;
           // }
        }
        if (shotGun.getY() <= 0) {
         //   if(shotGun.isVisible()) {
           //     shotGun.setVisible(false);
                return Direction.UP;
        //    }
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
            if(!squarantine.isDead()){
            for (int i = 0; i < ShotGun.getShots().size(); i++) {
                ShotGun shot = ShotGun.getShots().get(i);
                if(shot.isVisible()) {
                    if (squarantine2.intersects(shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight())) {
                        intersectionPoint4 = new Point2D.Double(shot.getX(),shot.getY());
                        shot.setVisible(false);
                        squarantine.decreaseHP(false);
                    }
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
            if(!trigorath.isDead()){
            for (int i = 0; i < ShotGun.getShots().size(); i++) {
                ShotGun shot = ShotGun.getShots().get(i);
                if(shot.isVisible()) {
                    if (trigorath2.intersects(shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight())) {
                        intersectionPoint4 = new Point2D.Double(shot.getX(),shot.getY());

                        shot.setVisible(false);
                        trigorath.decreaseHP(false);
                    }
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
                    Ellipse2D epsilonShape = new Ellipse2D.Double(epsilon.getX()-epsilon.getRadius(),
                            epsilon.getY()-epsilon.getRadius(),
                            2*epsilon.getRadius(),2*epsilon.getRadius());
                    Ellipse2D collectibleShape = new Ellipse2D.Double(collectible.getX()-collectible.getRadius(),
                            collectible.getY()-collectible.getRadius(),
                            2*collectible.getRadius(),2*collectible.getRadius());
                    Area epsilonArea = new Area(epsilonShape);
                    Area collectibleArea = new Area(collectibleShape);
                    epsilonArea.intersect(collectibleArea);

                    if(!epsilonArea.isEmpty()){
                        MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP()+5);
                        trigorath.getCollectibles().remove(collectible);
                    }


                }
            }
        }

        for(Squarantine squarantine : GamePanel.getSquarantine()){
            if(squarantine.isShowCollectibles()){

                for(int i=0;i<squarantine.getCollectibles().size();i++){
                    Collectible collectible = squarantine.getCollectibles().get(i);

                    Ellipse2D epsilonShape = new Ellipse2D.Double(epsilon.getX()-epsilon.getRadius(),
                            epsilon.getY()-epsilon.getRadius(),
                            2*epsilon.getRadius(),2*epsilon.getRadius());
                    Ellipse2D collectibleShape = new Ellipse2D.Double(collectible.getX()-collectible.getRadius(),
                            collectible.getY()-collectible.getRadius(),
                            2*collectible.getRadius(),2*collectible.getRadius());
                    Area epsilonArea = new Area(epsilonShape);
                    Area collectibleArea = new Area(collectibleShape);
                    epsilonArea.intersect(collectibleArea);

                    if(!epsilonArea.isEmpty()){
                        MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP()+5);
                        squarantine.getCollectibles().remove(collectible);
                    }
                }
            }
        }
    }
    public void enemyIntersection(){
        ArrayList<Point2D> points = new ArrayList<>();
        ArrayList<Point2D> points2 = new ArrayList<>();
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
//            for(Squarantine squarantine : GamePanel.getSquarantine()){
//                if(!squarantine.isDead()){
//                    for(int j=0;j<4;j++){
//                        points2.add(new Point2D.Double(squarantine.getxPoints()[j],squarantine.getyPoints()[j]));
//                    }
//                }
//            }
            }
            for (Point2D point2D : points) {
                if (trigorath2.contains(point2D)) {
                    intersectionPoint = point2D;
                }
            }
//            for (Point2D point2D : points2) {
//                if (trigorath2.contains(point2D)) {
//                    intersectionPoint9 = point2D;
//                }
//            }
        }

        for(Squarantine squarantine : GamePanel.getSquarantine()){
            for(Squarantine squarantine1 : GamePanel.getSquarantine()){
                if(!squarantine.equals(squarantine1)){
                    Rectangle squarantine2 = new Rectangle(squarantine.getxPoints()[0],squarantine.getyPoints()[0],20,20);
                    Rectangle squarantine3 = new Rectangle(squarantine1.getxPoints()[0],squarantine1.getyPoints()[0],20,20);
                    if(squarantine2.intersects(squarantine3)){
                        Rectangle intersection = squarantine2.intersection(squarantine3);
                        intersectionPoint8 = new Point2D.Double(intersection.x,intersection.y);
                    }
                }
            }
        }


    }
    public void  getIntersectionPoint(Polygon shape1, Polygon shape2) {

        for (int i = 0; i < shape1.npoints; i++) {
            for (int j = 0; j < shape2.npoints; j++) {
                if (shape1.contains(shape2.xpoints[j], shape2.ypoints[j])) {
                    intersectionPoint9 = new Point2D.Double(shape2.xpoints[j], shape2.ypoints[j]);

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


public boolean checkCollision(int epsilonX,int epsilonY,int radius,Polygon polygon){
    Ellipse2D epsilon = new Ellipse2D.Double(epsilonX-radius,epsilonY-radius,radius,radius);
    Area epsilonArea = new Area(epsilon);
    Area polygonArea = new Area(polygon);
    epsilonArea.intersect(polygonArea);
    return !epsilonArea.isEmpty();
}
//public void checkMeleeAttack(){
//        Point2D epsilonCenter = new Point2D.Double(GamePanel.getEpsilon().getxCenter(),GamePanel.getEpsilon().getyCenter());
//
//      for(Trigorath trigorath : GamePanel.getTrigoraths()) {
//          for (int i = 0; i < 3; i++) {
//              if (epsilonCenter.distance(trigorath.getxPoints()[i], trigorath.getyPoints()[i]) <= GamePanel.getEpsilon().getRadius()) {
//
//                  if(!GamePanel.getEpsilon().getVertex().isEmpty()) {
//                      for (Vertex vertex : GamePanel.getEpsilon().getVertex()) {
//                          Point2D vertexCenter = new Point2D.Double(vertex.getX(), vertex.getY());
//                          if (vertexCenter.distance(trigorath.getxPoints()[i], trigorath.getyPoints()[i]) > vertex.getRadius()) {
//                              GamePanel.getEpsilon().setHP(GamePanel.getEpsilon().getHP() - 10);
//                              break;
//                          }
//                      }
//                  }else{
//                      GamePanel.getEpsilon().setHP(GamePanel.getEpsilon().getHP() - 10);
//                      break;
//                  }
//              }
//          }
//      }
//          for(Squarantine squarantine : GamePanel.getSquarantine()) {
//              for (int i = 0; i < 4; i++) {
//                  if (epsilonCenter.distance(squarantine.getxPoints()[i], squarantine.getyPoints()[i]) <= GamePanel.getEpsilon().getRadius()) {
//                      if (!GamePanel.getEpsilon().getVertex().isEmpty()) {
//                          for (Vertex vertex : GamePanel.getEpsilon().getVertex()) {
//                              Point2D vertexCenter = new Point2D.Double(vertex.getX(), vertex.getY());
//                              if (vertexCenter.distance(squarantine.getxPoints()[i], squarantine.getyPoints()[i]) > GamePanel.getEpsilon().getRadius()) {
//                                  GamePanel.getEpsilon().setHP(GamePanel.getEpsilon().getHP() - 10);
//                                  break;
//                              }
//                          }
//                      }
//                  }else{
//                      GamePanel.getEpsilon().setHP(GamePanel.getEpsilon().getHP() - 10);
//                      break;
//                  }
//              }
//          }
//
//
//
//}


    public static Point2D getIntersectionPoint2() {
        return intersectionPoint2;
    }

    public static void setIntersectionPoint2(Point2D intersectionPoint2) {
        Intersection.intersectionPoint2 = intersectionPoint2;
    }

    public static Point2D getIntersectionPoint3() {
        return intersectionPoint3;
    }

    public static void setIntersectionPoint3(Point2D intersectionPoint3) {
        Intersection.intersectionPoint3 = intersectionPoint3;
    }

    public static Point2D getIntersectionPoint4() {
        return intersectionPoint4;
    }

    public static void setIntersectionPoint4(Point2D intersectionPoint4) {
        Intersection.intersectionPoint4 = intersectionPoint4;
    }

    public static Point2D getIntersectionPoint5() {
        return intersectionPoint5;
    }

    public static void setIntersectionPoint5(Point2D intersectionPoint5) {
        Intersection.intersectionPoint5 = intersectionPoint5;
    }

    public static Point2D getIntersectionPoint6() {
        return intersectionPoint6;
    }

    public static void setIntersectionPoint6(Point2D intersectionPoint6) {
        Intersection.intersectionPoint6 = intersectionPoint6;
    }

    public static Point2D getIntersectionPoint7() {
        return intersectionPoint7;
    }

    public static void setIntersectionPoint7(Point2D intersectionPoint7) {
        Intersection.intersectionPoint7 = intersectionPoint7;
    }

    public static Point2D getIntersectionPoint8() {
        return intersectionPoint8;
    }

    public static void setIntersectionPoint8(Point2D intersectionPoint8) {
        Intersection.intersectionPoint8 = intersectionPoint8;
    }

    public static Point2D getIntersectionPoint9() {
        return intersectionPoint9;
    }

    public static void setIntersectionPoint9(Point2D intersectionPoint9) {
        Intersection.intersectionPoint9 = intersectionPoint9;
    }
}
