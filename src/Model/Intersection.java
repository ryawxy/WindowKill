package Model;

import Controller.Constants;
import Controller.Game;
import Controller.ShopController;
import View.GamePanel;
import View.NecropickView;
import myproject.MyProject;

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

    private static ArrayList<IntersectionPoint> intersectionPoints;

    public Intersection(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
    }

    public Intersection() {
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

    public void shotIntersectsEntity(){
        //check if a shot intersects with squarantine
        // if so decrease its HP
        // if its HP reaches 0 , it dies
        for (int j = 0; j < Game.getSquarantine().size(); j++) {
            Squarantine squarantine = Game.getSquarantine().get(j);
            squarantine2 = new Polygon(squarantine.getxPoints(), squarantine.getyPoints(), 3);
            if (!squarantine.isDead()) {
                for (int i = 0; i < Game.getShots().size(); i++) {
                    ShotGun shot = Game.getShots().get(i);
                    if (shot.isVisible()) {
                        if (squarantine2.intersects(shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight())) {
                            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(shot.getX(), shot.getY()), 10, false, true, shot, squarantine);
                            intersectionPoints.add(point);
                            shot.setVisible(false);
                            squarantine.decreaseHP(5);
                        }
                    }

                }
            }
        }
        for(int j = 0; j< Game.getTrigoraths().size(); j++) {
            Trigorath trigorath = Game.getTrigoraths().get(j);
            trigorath2 = new Polygon(trigorath.getxPoints(),trigorath.getyPoints(),3);
            if(!trigorath.isDead()){
                for (int i = 0; i < Game.getShots().size(); i++) {
                    ShotGun shot = Game.getShots().get(i);
                    if(shot.isVisible()) {
                        if (trigorath2.intersects(shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight())) {
                            IntersectionPoint point = new IntersectionPoint( new Point2D.Double(shot.getX(),shot.getY()),10,false,true,shot,trigorath);
                            intersectionPoints.add(point);

                            shot.setVisible(false);
                            trigorath.decreaseHP(5);
                        }
                    }

                }
            }
        }
        Epsilon epsilon = Game.getEpsilon();
        for (Necropick necropick : Game.getNecropicks()) {
            for (ShotGun shotGun : necropick.getShots()) {
                Rectangle epsilon1 = new Rectangle(epsilon.getX(), epsilon.getY(), epsilon.getRadius(), epsilon.getRadius());
                Rectangle shot = new Rectangle(shotGun.getX(), shotGun.getY(), Constants.getShotGunWidth(), Constants.getShotGunHeight());
                if (shotGun.isVisible()) {
                    if (epsilon1.intersects(shot)) {
                        Game.getEpsilon().decreaseHP(5);
                        shotGun.setVisible(false);
                        IntersectionPoint point = new IntersectionPoint(new Point2D.Double(epsilon.getX(), epsilon.getY()), 10, false, false, epsilon, necropick);
                        intersectionPoints.add(point);
                    }
                    for(GameObjects enemy : Game.getEnemies()){
                        if(!enemy.equals(necropick)) {
                            Rectangle enemy1 = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

                            if (enemy1.intersects(shot)) {

                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(shotGun.getX(), shotGun.getY()), 10, false, false, necropick, enemy);
                                intersectionPoints.add(point);
                                shotGun.setVisible(false);
                            }
                        }
                    }
                }
            }
        }
        for(Necropick necropick : Game.getNecropicks()){
            for(ShotGun shotGun : Game.getShots()){
                Rectangle neropick1 = new Rectangle(necropick.getX(),necropick.getY(), Constants.necropickWidth(),Constants.necropickWidth());
                Rectangle shot = new Rectangle(shotGun.getX(),shotGun.getY(),shotGun.getWidth(),shotGun.getHeight());
                if(shotGun.isVisible()){
                    if(neropick1.intersects(shot)) {

                        shotGun.setVisible(false);
                        necropick.decreaseHP(2);


                    }

                }
            }
        }
    }


    public void epsilonIntersectsCollectible(){
        // check if epsilon intersects with collectibles
        // if so add to epsilons XP

        Epsilon epsilon = Game.getEpsilon();


        for(GameObjects enemy : Game.getEnemies()){
            if(enemy.isShowCollectibles()){
                for(int i=0; i<enemy.getCollectibles().size(); i++){
                    Collectible collectible = enemy.getCollectibles().get(i);

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
                        if(enemy instanceof Necropick){
                            MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP()+2);
                        }else {
                            MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 5);
                        }
                        enemy.getCollectibles().remove(collectible);
                    }
                }
            }
        }

    }
    public void enemyIntersection(){
        ArrayList<Point2D> points = new ArrayList<>();
        ArrayList<Point2D> points2 = new ArrayList<>();
        Trigorath t = new Trigorath(10,100);
        for(Trigorath trigorath : Game.getTrigoraths()){
            Polygon trigorath2 = new Polygon(trigorath.getxPoints(), trigorath.getyPoints(), 3);
            if(!trigorath.isDead()){
            for(Trigorath trigorath1 : Game.getTrigoraths()) {
                if(!trigorath1.isDead()) {
                    if (!trigorath.equals(trigorath1)) {

                        for (int i = 0; i < 3; i++) {
                            points.add(new Point2D.Double(trigorath1.getxPoints()[i], trigorath1.getyPoints()[i]));
                        }
                    }
                }
                t = trigorath1;
            }
            }
            for (Point2D point2D : points) {
                if (trigorath2.contains(point2D)) {
                    IntersectionPoint point = new IntersectionPoint(point2D,10,false,false,trigorath,t);
                    intersectionPoints.add(point);

                }
            }

        }

        for(Squarantine squarantine : Game.getSquarantine()){
            for(Squarantine squarantine1 : Game.getSquarantine()){
                if(!squarantine.equals(squarantine1)){
                    Rectangle squarantine2 = new Rectangle(squarantine.getxPoints()[0],squarantine.getyPoints()[0],20,20);
                    Rectangle squarantine3 = new Rectangle(squarantine1.getxPoints()[0],squarantine1.getyPoints()[0],20,20);
                    if(squarantine2.intersects(squarantine3)){
                        Rectangle intersection = squarantine2.intersection(squarantine3);
                        IntersectionPoint point = new IntersectionPoint( new Point2D.Double(intersection.x,intersection.y),10,false,false,squarantine1,squarantine);
                        intersectionPoints.add(point);
                    }
                }
            }
        }



    }
    public void  getIntersectionPoint() {
        for(Trigorath trigorath : Game.getTrigoraths()){
            for(Squarantine squarantine : Game.getSquarantine()){
                Polygon trigorath1 = new Polygon(trigorath.getxPoints(), trigorath.getyPoints(), 3);
                Polygon squarantine1 = new Polygon(squarantine.getxPoints(), squarantine.getyPoints(), 4);

                for (int i = 0; i < trigorath1.npoints; i++) {
                    for (int j = 0; j < squarantine1.npoints; j++) {
                        if (trigorath1.contains(squarantine1.xpoints[j], squarantine1.ypoints[j])) {
                            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(squarantine1.xpoints[j], squarantine1.ypoints[j]),30,false,false,trigorath,squarantine);
                            intersectionPoints.add(point);

                        }
                    }
                }
            }
        }

    }

public boolean checkCollision(int epsilonX,int epsilonY,int radius,Polygon polygon){
    Ellipse2D epsilon = new Ellipse2D.Double(epsilonX-radius,epsilonY-radius,radius,radius);
    Area epsilonArea = new Area(epsilon);
    Area polygonArea = new Area(polygon);
    epsilonArea.intersect(polygonArea);
    return !epsilonArea.isEmpty();
}
public void vertexIntersectsNecropick(){
        for(Vertex vertex : Game.getEpsilon().getVertex()){
            for(Necropick necropick : Game.getNecropicks()){
                Rectangle vertex1 = new Rectangle(vertex.getX(),vertex.getY(),vertex.getRadius(),vertex.getRadius());
                Rectangle necropick1 = new Rectangle(necropick.getX(),necropick.getY(),Constants.necropickWidth(),Constants.necropickWidth());
                if(vertex1.intersects(necropick1)){
                    necropick.decreaseHP(10);
                    IntersectionPoint point = new IntersectionPoint(new Point2D.Double(necropick.getX(),necropick.getY()),10,true,false,Game.getEpsilon(),necropick);
                    intersectionPoints.add(point);
                }
            }
        }
}

    public static ArrayList<IntersectionPoint> getIntersectionPoints(){
        if(intersectionPoints==null) intersectionPoints = new ArrayList<>();
        return intersectionPoints;
    }
}
