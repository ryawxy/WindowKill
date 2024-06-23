package Model;

import Controller.Constants;
import Controller.Game;
import Model.omenoct.Omenoct;
import Model.omenoct.Side;
import View.GamePanel;
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


        Epsilon epsilon = Game.getEpsilon();

            //enemy shots epsilon
            for(GameObjects enemy : Game.getEnemies()){
                for(ShotGun shotGun : enemy.getShots()){
                    Rectangle epsilon1 = new Rectangle(epsilon.getX(),epsilon.getY(),epsilon.getRadius(),epsilon.getRadius());
                    Rectangle shot= new Rectangle(shotGun.getX(),shotGun.getY(),Constants.getShotGunWidth(),Constants.getShotGunHeight());
                    if(shotGun.isVisible()){
                        if(epsilon1.intersects(shot)){
                            if(enemy instanceof Omenoct) Game.getEpsilon().decreaseHP(4);
                            else if(enemy instanceof  Necropick) Game.getEpsilon().decreaseHP(5);
                            shotGun.setVisible(false);
                            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(epsilon.getX(), epsilon.getY()), 10, false, false, epsilon, enemy);
                            intersectionPoints.add(point);
                        }
                    }
                }
            }


        //epsilon shots enemy
        for(GameObjects enemy : Game.getEnemies()){
        for(ShotGun shotGun : Game.getShots()){

            if(enemy.isVisible()){
                Rectangle enemy1 = new Rectangle(enemy.getX(),enemy.getY(),enemy.getWidth(),enemy.getHeight());
                Rectangle shot= new Rectangle(shotGun.getX(),shotGun.getY(),Constants.getShotGunWidth(),Constants.getShotGunHeight());
                if(shotGun.isVisible()) {
                    if (enemy1.intersects(shot)) {

                        enemy.decreaseHP(5);
                        shotGun.setVisible(false);
                        IntersectionPoint point = new IntersectionPoint(new Point2D.Double(shotGun.getX(), shotGun.getY()), 10, false, false, epsilon, enemy);
                        intersectionPoints.add(point);
                    }
                }
                }
            }
        }

        for(Omenoct omenoct : Game.getOmenocts()) {
            for(ShotGun shotGun : Game.getShots()){

            if(shotIntersectsFrame(shotGun)!=null) {
                if (shotGun.getX()==0 && omenoct.getSide().equals(Side.LEFT))
                    omenoct.decreaseHP(5);
                if (shotGun.getX()==GamePanel.getFRAME_HEIGHT() && omenoct.getSide().equals(Side.RIGHT))
                    omenoct.decreaseHP(5);
                if (shotGun.getY() == 0 && omenoct.getSide().equals(Side.UP))
                    omenoct.decreaseHP(5);
                if (shotGun.getY() == GamePanel.getFRAME_HEIGHT() && omenoct.getSide().equals(Side.DOWN))
                    omenoct.decreaseHP(5);




            }
        }


        }
        //enemy shots enemy
        // TODO:which enemy shots can intersect other enemies??







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
                        }else if(enemy instanceof Omenoct){
                            MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP()+4);
                        }
                        else {
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
    public void vertexIntersectsOmenoct(){
        for(Vertex vertex : Game.getEpsilon().getVertex()){
            for(Omenoct omenoct : Game.getOmenocts()){
                Rectangle vertex1 = new Rectangle(vertex.getX(),vertex.getY(),vertex.getRadius(),vertex.getRadius());
                Rectangle omenoct1 = new Rectangle(omenoct.getX(),omenoct.getY(),Constants.omenoctWidth(),Constants.omenoctWidth());
                if(vertex1.intersects(omenoct1)){

                    omenoct.decreaseHP(10);
                    IntersectionPoint point = new IntersectionPoint(new Point2D.Double(omenoct.getX(),omenoct.getY()),10,true,false,Game.getEpsilon(),omenoct);
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
