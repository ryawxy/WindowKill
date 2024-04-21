package Controller;

import Model.*;
import View.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class Intersection {
    GamePanel gamePanel;

    private Polygon squarantine2;
    private Polygon trigorath2;
    private boolean intersect;
    private static Point2D intersectionPoint;
    private static Point2D intersectionPoint2;
    private static Point2D intersectionPoint3;
    private static boolean meleeAttack;
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
            if(!squarantine.isDead()){
            for (int i = 0; i < ShotGun.getShots().size(); i++) {
                ShotGun shot = ShotGun.getShots().get(i);
                if(shot.isVisible()) {
                    if (squarantine2.intersects(shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight())) {

                        shot.setVisible(false);
                        squarantine.decreaseHP();
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

                        shot.setVisible(false);
                        trigorath.decreaseHP();
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


public boolean checkCollision(int epsilonX,int epsilonY,int radius,Polygon polygon){
    Ellipse2D epsilon = new Ellipse2D.Double(epsilonX-radius,epsilonY-radius,radius,radius);
    Area epsilonArea = new Area(epsilon);
    Area polygonArea = new Area(polygon);
    epsilonArea.intersect(polygonArea);
    return !epsilonArea.isEmpty();
}
public void checkMeleeAttack(){
        Point2D epsilonCenter = new Point2D.Double(GamePanel.getEpsilon().getxCenter(),GamePanel.getEpsilon().getyCenter());

      for(Trigorath trigorath : GamePanel.getTrigoraths()) {
          for (int i = 0; i < 3; i++) {
              if (epsilonCenter.distance(trigorath.getxPoints()[i], trigorath.getyPoints()[i]) <= GamePanel.getEpsilon().getRadius()) {
                  for (Point2D point2D : GamePanel.getEpsilon().getVertex()) {
                   //   Point2D vertexCenter = new Point2D.Double()
                      GamePanel.getEpsilon().setHP(GamePanel.getEpsilon().getHP() - 10);
                      break;
                  }
              }
          }
      }
          for(Squarantine squarantine : GamePanel.getSquarantine()) {
              for (int i = 0; i < 4; i++) {
                  if (epsilonCenter.distance(squarantine.getxPoints()[i], squarantine.getyPoints()[i]) <= GamePanel.getEpsilon().getRadius()) {
                      GamePanel.getEpsilon().setHP(GamePanel.getEpsilon().getHP() - 10);
                      break;

                  }
              }
          }




}


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
}
