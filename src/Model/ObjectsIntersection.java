package Model;

import Controller.Constants;
import Controller.Game;
import Model.entity.*;
import Model.entity.blackOrb.BlackOrb;
import Model.entity.blackOrb.Laser;
import Model.enums.ArchmireType;
import Model.enums.Direction;
import Model.enums.EnemyType;
import Model.enums.Side;
import view.entityViews.blackOrb.BlackOrbFrame;
import view.GamePanel;
import Model.entity.Epsilon;
import view.entityViews.barricados.BarricadosFrame;
import myproject.MyProject;
import view.entityViews.wyrm.WyrmFrame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectsIntersection {
    GamePanel gamePanel;

    private static ArrayList<IntersectionPoint> intersectionPoints;
    private static long lastTime,lastTime2;
    //AOE
    private static long lastTime3,lastTime4;
    //Drown
    private static long lastTime5;
    //laser



    public ObjectsIntersection(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
    }

    public ObjectsIntersection() {
    }

    public Direction shotIntersectsFrame(ShotGun shotGun) {



        if (shotGun.getLocalX() >=shotGun.getLocalFrame().getWidth()) {
            return Direction.RIGHT;
        }
        if (shotGun.getLocalY() >= shotGun.getLocalFrame().getHeight()) {

            return Direction.DOWN;

        }
        if (shotGun.getLocalX() < 0) {

            return Direction.LEFT;

        }
        if (shotGun.getLocalY() < 0) {

            return Direction.UP;

        }

        return null;
    }

    public void shotIntersectsEntity(){


        Epsilon epsilon = Game.getEpsilon();

            //enemy shots epsilon
            for(GameObjects enemy : Game.getEnemies()){
               
                for(ShotGun shotGun : enemy.getShots()){

                    Rectangle epsilon1 = new Rectangle(epsilon.getLocalX()+epsilon.getLocalFrame().getX(),epsilon.getLocalY()+epsilon.getLocalFrame().getY(),epsilon.getRadius(),epsilon.getRadius());
                    Rectangle shot= new Rectangle(shotGun.getLocalX()+shotGun.getLocalFrame().getX(),shotGun.getLocalY()+shotGun.getLocalFrame().getY(),Constants.getShotGunWidth(),Constants.getShotGunHeight());
                    if(shotGun.isVisible()){

                        if(epsilon1.intersects(shot)){
                            if(enemy instanceof Omenoct) Game.getEpsilon().decreaseHP(4);
                            if(enemy instanceof Wyrm) Game.getEpsilon().decreaseHP(8);
                            else if(enemy instanceof Necropick) Game.getEpsilon().decreaseHP(5);
                            shotGun.setVisible(false);
                            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(shotGun.getLocalX()+shotGun.getLocalFrame().getX(), shotGun.getLocalY()+shotGun.getLocalFrame().getY()), 10, false, false, epsilon, enemy);
                            intersectionPoints.add(point);
                        }
                    }
                }
            }


        //epsilon shots enemy
        for(GameObjects enemy : Game.getEnemies()){
        for(ShotGun shotGun : Game.getEpsilonShots()) {

            if (!(enemy instanceof BlackOrb) && !(enemy instanceof Wyrm)) {
                if (enemy.isVisible()) {
                    Rectangle enemy1 = new Rectangle(enemy.getLocalX()+enemy.getLocalFrame().getX(), enemy.getLocalY()+enemy.getLocalFrame().getY(), enemy.getWidth(), enemy.getHeight());
                    Rectangle shot = new Rectangle(shotGun.getLocalX()+shotGun.getLocalFrame().getX(), shotGun.getLocalY()+shotGun.getLocalFrame().getY(), Constants.getShotGunWidth(), Constants.getShotGunHeight());
                    if (shotGun.isVisible()) {


                        if (enemy1.intersects(shot)) {

                            shotGun.setVisible(false);

                            enemy.decreaseHP(5);

                            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(shotGun.getLocalX(), shotGun.getLocalY()), 10, false, false, epsilon, enemy);
                            intersectionPoints.add(point);
                        }
                    }
                }
            }
        }
        }
        for(BlackOrbFrame blackOrbFrame : Game.getBlackOrbFrames()){
            for(ShotGun shotGun : Game.getEpsilonShots()) {
   //             if (shotGun.isVisible()) {

                //    if (shotGun.getLocalFrame().equals(blackOrbFrame.getBlackOrb().getLocalFrame())) {
                        Rectangle shot = new Rectangle(shotGun.getLocalX()+shotGun.getLocalFrame().getX(), shotGun.getLocalY()+shotGun.getLocalFrame().getY(), Constants.getShotGunWidth(), Constants.getShotGunHeight());
                        Rectangle blackOrb1 = new Rectangle(blackOrbFrame.getBlackOrb().getLocalX()+blackOrbFrame.getX(),
                                blackOrbFrame.getBlackOrb().getLocalY()+blackOrbFrame.getY(), blackOrbFrame.getBlackOrb().getWidth(),blackOrbFrame.getBlackOrb().getHeight());

                        if(shot.intersects(blackOrb1)){
                            System.out.println(1111);
                            blackOrbFrame.getBlackOrb().decreaseHP(5);
                            shotGun.setVisible(false);

                            if(blackOrbFrame.getBlackOrb().isDead()) {
                                for (Laser laser : blackOrbFrame.getBlackOrb().getLasers()) {

                                    laser.setVisible(false);
                                }
                                for (BlackOrbFrame blackOrbFrame1 : Game.getBlackOrbFrames()) {
                                    if (!blackOrbFrame1.equals(blackOrbFrame)) {
                                        for (Laser laser : blackOrbFrame1.getBlackOrb().getLasers()) {
                                            if (laser.getBlackOrb2().equals(blackOrbFrame.getBlackOrb()))
                                                laser.setVisible(false);
                                        }
                                    }
                                }
                            }

                        }

                    }
    //            }
     //       }
        }
        for(WyrmFrame wyrmFrame : Game.getWyrmFrames()){
            for(ShotGun shotGun : Game.getEpsilonShots()){
                if(shotGun.isVisible()){
                    Rectangle shot = new Rectangle(shotGun.getLocalX()+shotGun.getLocalFrame().getX(), shotGun.getLocalY()+shotGun.getLocalFrame().getY(),
                            Constants.getShotGunWidth(), Constants.getShotGunHeight());

                    Rectangle wyrm = new Rectangle(wyrmFrame.getWyrm().getLocalX()+wyrmFrame.getX(),wyrmFrame.getWyrm().getLocalY()+wyrmFrame.getY(),100,100);
                    if(wyrm.intersects(shot)){

                        shotGun.setVisible(false);
                        wyrmFrame.getWyrm().decreaseHP(4);
                    }
                    }
            }

        }

        for(Omenoct omenoct : Game.getOmenocts()) {
            for(ShotGun shotGun : Game.getEpsilonShots()){

            if(shotIntersectsFrame(shotGun)!=null) {
                if (shotGun.isVisible()) {
                    if (shotIntersectsFrame(shotGun).equals(Direction.LEFT) && omenoct.getSide().equals(Side.LEFT)){
                        omenoct.decreaseHP(5);
                   }
                    if (shotIntersectsFrame(shotGun).equals(Direction.RIGHT) && omenoct.getSide().equals(Side.RIGHT)){
                        omenoct.decreaseHP(5);
                    }
                    if (shotIntersectsFrame(shotGun).equals(Direction.UP) && omenoct.getSide().equals(Side.UP)){
                        omenoct.decreaseHP(5);
                   }
                    if (shotIntersectsFrame(shotGun).equals(Direction.DOWN) && omenoct.getSide().equals(Side.DOWN)){
                        omenoct.decreaseHP(5);}

                }
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


        for(GameObjects enemy : Game.getEnemies()) {
            if (enemy.isShowCollectibles()) {
                for (int i = 0; i < enemy.getCollectibles().size(); i++) {
                    Collectible collectible = enemy.getCollectibles().get(i);

                    if (epsilon.getLocalFrame().equals(collectible.getLocalFrame())) {
                        Ellipse2D epsilonShape = new Ellipse2D.Double(epsilon.getLocalX() - epsilon.getRadius(),
                                epsilon.getLocalY() - epsilon.getRadius(),
                                2 * epsilon.getRadius(), 2 * epsilon.getRadius());
                        Ellipse2D collectibleShape = new Ellipse2D.Double(collectible.getX() - (double) collectible.getWidth() /2,
                                collectible.getY() - (double) collectible.getHeight() /2,
                                 collectible.getWidth(), collectible.getHeight());
                        Area epsilonArea = new Area(epsilonShape);
                        Area collectibleArea = new Area(collectibleShape);
                        epsilonArea.intersect(collectibleArea);


                        if (!epsilonArea.isEmpty()) {

                            switch (enemy) {
                                case Necropick necropick -> MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 2);
                                case Omenoct omenoct -> MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 4);
                                case Archmire archmire -> {
                                    if (archmire.getsize().equals(ArchmireType.LARGE))
                                        MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 6);
                                    else MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 3);
                                }
                                case BlackOrb blackOrb -> MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 30);
                                case Wyrm wyrm -> MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 8);

                                default -> MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 5);
                            }
                            enemy.getCollectibles().remove(collectible);
                        }
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

//        for(GameObjects enemy1 : Game.getEnemies()){
//            for(GameObjects enemy2 : Game.getEnemies() ){
//                if(!(enemy1 instanceof Trigorath && enemy2 instanceof Squarantine) && !(enemy1 instanceof Squarantine && enemy2 instanceof Trigorath)
//                && !(enemy1 instanceof Trigorath && enemy2 instanceof Trigorath) && !(enemy1 instanceof Squarantine && enemy2 instanceof Squarantine)){
//                if(!enemy1.equals(enemy2) && !(enemy1 instanceof Archmire) && !(enemy2 instanceof Archmire)) {
//                    if (enemy1.isVisible() && enemy2.isVisible()) {
//                        Rectangle e1 = new Rectangle(enemy1.getLocalX()+enemy1.getLocalFrame().getX(), enemy1.getLocalY()+enemy1.getLocalFrame().getY(), enemy1.getWidth(), enemy1.getHeight());
//                        Rectangle e2 = new Rectangle(enemy2.getLocalX()+enemy2.getLocalFrame().getX(), enemy2.getLocalY()+enemy2.getLocalFrame().getY(), enemy2.getWidth(), enemy2.getHeight());
//                        if (e1.intersects(e2)) {
//                            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(enemy1.getX(), enemy1.getY()), 10, false, false, enemy1, enemy2);
//                            intersectionPoints.add(point);
//                        }
//                    }
//                }
//                }
//            }
//        }
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
                if(!necropick.isDead()) {
                    Rectangle vertex1 = new Rectangle(vertex.getLocalX() + vertex.getLocalFrame().getX(), vertex.getLocalY() + vertex.getLocalFrame().getY(), vertex.getRadius(), vertex.getRadius());
                    Rectangle necropick1 = new Rectangle(necropick.getLocalX() + necropick.getLocalFrame().getX(), necropick.getLocalY() + necropick.getLocalFrame().getY(), Constants.necropickWidth(), Constants.necropickWidth());
                    if (vertex1.intersects(necropick1)) {
                        necropick.decreaseHP(10);
                        IntersectionPoint point = new IntersectionPoint(new Point2D.Double(necropick.getLocalX() + necropick.getLocalFrame().getX(), necropick.getLocalY() + necropick.getLocalFrame().getY()), 10, true, false, Game.getEpsilon(), necropick);
                        intersectionPoints.add(point);
                    }
                }
            }
        }
}
    public void vertexIntersectsOmenoct(){
        for(Vertex vertex : Game.getEpsilon().getVertex()){
            for(Omenoct omenoct : Game.getOmenocts()){
                if(!omenoct.isDead()) {
                    Rectangle vertex1 = new Rectangle(vertex.getX(), vertex.getY(), vertex.getRadius(), vertex.getRadius());
                    Rectangle omenoct1 = new Rectangle(omenoct.getLocalX(), omenoct.getLocalY(), Constants.omenoctWidth(), Constants.omenoctWidth());
                    if (vertex1.intersects(omenoct1)) {

                        omenoct.decreaseHP(5);
                        IntersectionPoint point = new IntersectionPoint(new Point2D.Double(omenoct.getLocalX(), omenoct.getLocalY()), 10, true, false, Game.getEpsilon(), omenoct);
                        intersectionPoints.add(point);
                    }
                }
            }
        }
    }
    public void vertexIntersectsOrb(){

        for(Vertex vertex : Game.getEpsilon().getVertex()){
            for(BlackOrbFrame blackOrbFrame : Game.getBlackOrbFrames()){
                Rectangle vertex1 = new Rectangle(vertex.getLocalX()+vertex.getLocalFrame().getX(),vertex.getLocalY()+vertex.getLocalFrame().getY(),vertex.getRadius(),vertex.getRadius());
                Rectangle blackOrb = new Rectangle(blackOrbFrame.getBlackOrb().getLocalX()+blackOrbFrame.getX(),blackOrbFrame.getBlackOrb().getLocalY()+blackOrbFrame.getY(),Constants.orbSize(),Constants.orbSize());
                if(vertex1.intersects(blackOrb)){
                    blackOrbFrame.getBlackOrb().decreaseHP(10);
                    IntersectionPoint intersectionPoint = new IntersectionPoint(new Point2D.Double(blackOrbFrame.getBlackOrb().getLocalX()+blackOrbFrame.getX(),
                            blackOrbFrame.getBlackOrb().getLocalY()+blackOrbFrame.getY()), 10,true,
                            false,Game.getEpsilon(),blackOrbFrame.getBlackOrb());
                 //   intersectionPoints.add(intersectionPoint);

                    if(blackOrbFrame.getBlackOrb().isDead()) {
                        for (Laser laser : blackOrbFrame.getBlackOrb().getLasers()) {

                            laser.setVisible(false);
                        }
                        for (BlackOrbFrame blackOrbFrame1 : Game.getBlackOrbFrames()) {
                            if (!blackOrbFrame1.equals(blackOrbFrame)) {
                                for (Laser laser : blackOrbFrame1.getBlackOrb().getLasers()) {
                                    if (laser.getBlackOrb2().equals(blackOrbFrame.getBlackOrb()))
                                        laser.setVisible(false);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void AOEIntersection() {
        Epsilon epsilon = Game.getEpsilon();
        for (Archmire archmire : Game.getArchmires()) {
            for (Footprint footprint : archmire.getFootprint()) {
                if (footprint.isVisible()) {
                    Point2D footprintCenter;
                    Point2D archmireCenter;
                    double arcRadius;
                    double arcRadius1;
                    Point2D enemyCenter;
                    Point2D epsilonCenter;

                        footprintCenter = new Point2D.Double(footprint.getLocalX()+footprint.getLocalFrame().getX() + (double) archmire.getWidth() /2,
                                footprint.getLocalY()+footprint.getLocalFrame().getY() + (double) archmire.getHeight() /2);
                    archmireCenter = new Point2D.Double(archmire.getLocalX()+archmire.getLocalFrame().getX() + (double) archmire.getWidth() /2,
                            archmire.getLocalY()+archmire.getLocalFrame().getY() + (double) archmire.getHeight() /2);

                     epsilonCenter = new Point2D.Double(epsilon.getLocalX()+epsilon.getLocalFrame().getX()+ (double) epsilon.getWidth() /2,
                             epsilon.getLocalY()+epsilon.getLocalFrame().getY()+ (double) epsilon.getHeight() /2 );


                        arcRadius = archmire.getWidth();
                        arcRadius1 = archmire.getHeight();

                        long currentTime = System.currentTimeMillis();
                        if (epsilonCenter.distance(footprintCenter) <= Math.abs(arcRadius - (double) epsilon.getRadius() / 2) ||
                                epsilonCenter.distance(footprintCenter) <= Math.abs(arcRadius1 - (double)epsilon.getRadius() / 2)) {
                            if ((currentTime - lastTime) / 1000 >= 1) {
                                lastTime = currentTime;
                                epsilon.decreaseHP(2);
                            }
                        }
                    if (epsilonCenter.distance(archmireCenter) <= Math.abs(arcRadius - (double) epsilon.getRadius() / 2) ||
                            epsilonCenter.distance(footprintCenter) <= Math.abs(arcRadius1 - (double) epsilon.getRadius() / 2)) {
                        if ((currentTime - lastTime3) / 1000 >= 1) {
                            lastTime3 = currentTime;
                            epsilon.decreaseHP(10);
                        }
                    }

                        for (GameObjects enemy : Game.getEnemies()) {
                            if (!enemy.equals(archmire) ) {
                                enemyCenter = new Point2D.Double(enemy.getLocalX()+enemy.getLocalFrame().getX() + (double) enemy.getWidth() / 2,
                                        enemy.getLocalY()+enemy.getLocalFrame().getY() + (double) enemy.getHeight() / 2);

                                double distance = enemyCenter.distance(footprintCenter);
                                if(distance <= Math.abs(arcRadius1- (double) enemy.getWidth() /2) || distance <= Math.abs(arcRadius1- (double) enemy.getHeight() /2)
                                || distance <= Math.abs(arcRadius - (double) enemy.getWidth() /2) || distance <= Math.abs(enemy.getHeight()/2)){
                                    if ((currentTime - lastTime2) / 1000 >= 1) {
                                        lastTime2 = currentTime;
                                        enemy.decreaseHP(2);
                                    }
                                }
                                double distance2 = enemyCenter.distance(archmireCenter);
                                if(distance2 <= Math.abs(arcRadius1- (double) enemy.getWidth() /2) || distance2 <= Math.abs(arcRadius1- (double) enemy.getHeight() /2)
                                        || distance2 <= Math.abs(arcRadius - (double) enemy.getWidth() /2) || distance2 <= Math.abs(enemy.getHeight()/2)){
                                    if ((currentTime - lastTime4) / 1000 >= 1) {
                                        lastTime4 = currentTime;
                                        enemy.decreaseHP(10);
                                    }
                                }
                            }
                        }
                    }
                }
                }
            }
            public void epsilonIntersectsEnemy() {
                for (Trigorath trigorath : Game.getTrigoraths()) {
                    if (!trigorath.isDead()) {
                        boolean VTCollision = false;
                        Epsilon epsilon = Game.getEpsilon();
                        Polygon trigorath2 = new Polygon(trigorath.getxPoints(), trigorath.getyPoints(), 3);
                        for (Vertex vertex : epsilon.getVertex()) {
                            if (checkCollision((int) vertex.getxCenter(), (int) vertex.getyCenter(), vertex.getRadius(), trigorath2)) {
                                VTCollision = true;
                                trigorath.decreaseHP(5);
                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(trigorath.getX(), trigorath.getY()), 10, false, true, vertex, trigorath);
                                ObjectsIntersection.getIntersectionPoints().add(point);
                            }
                        }
                    }
                }
                for (Trigorath trigorath3 : Game.getTrigoraths()) {
                    if (!trigorath3.isDead()) {

                        Epsilon epsilon = Game.getEpsilon();
                        if (trigorath3.getLocalFrame().equals(epsilon.getLocalFrame())) {

                            Polygon trigorath2 = new Polygon(trigorath3.getxPoints(), trigorath3.getyPoints(), 3);
                            //   if (!VTCollision) {
                            if (checkCollision(epsilon.getxCenter(), epsilon.getyCenter(), epsilon.getRadius(), trigorath2)) {
                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(trigorath3.getX(), trigorath3.getY()), 30, true, false, trigorath3, epsilon);
                                ObjectsIntersection.getIntersectionPoints().add(point);
                                boolean melee = point.isMeleeAttack();
                                epsilon.decreaseHP(EnemyType.Trigorath, melee);
                            }

                        }
                    }
                }
                for (Omenoct omenoct : Game.getOmenocts()) {
                    if (!omenoct.isDead()) {

                        Epsilon epsilon = Game.getEpsilon();
                        Rectangle epsilon1 = new Rectangle(epsilon.getLocalX(), epsilon.getLocalY(), epsilon.getWidth(), epsilon.getHeight());
                        Rectangle omenoct2 = new Rectangle(omenoct.getLocalX(), omenoct.getLocalY(), omenoct.getWidth(), omenoct.getHeight());

                        if (epsilon.getLocalFrame().equals(omenoct.getLocalFrame())) {
                            if (epsilon1.intersects(omenoct2)) {
                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(omenoct.getLocalX() + (double) omenoct.getWidth() / 2, omenoct.getLocalY() + (double) omenoct.getHeight() / 2), 15, true, false, omenoct, epsilon);
                                ObjectsIntersection.getIntersectionPoints().add(point);
                                boolean melee = point.isMeleeAttack();
                                epsilon.decreaseHP(EnemyType.Omenoct, melee);
                            }
                        }
                    }
                }
                boolean VSCollission;
                for (Squarantine squarantine : Game.getSquarantine()) {
                    if (!squarantine.isDead()) {
                        VSCollission = false;
                        Epsilon epsilon = Game.getEpsilon();
                        Polygon squarantine2 = new Polygon(squarantine.getxPoints(), squarantine.getyPoints(), 4);
                        for (Vertex vertex : epsilon.getVertex()) {
                            if (checkCollision((int) vertex.getxCenter(), (int) vertex.getyCenter(), vertex.getRadius(), squarantine2)) {

                                VSCollission = true;
                                squarantine.decreaseHP(10);
                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(squarantine.getX(), squarantine.getY()), 10, false, true, vertex, squarantine);
                                ObjectsIntersection.getIntersectionPoints().add(point);

                            }
                        }
                    }
                }
                for (Squarantine squarantine3 : Game.getSquarantine()) {
                    if (!squarantine3.isDead()) {
                        VSCollission = false;
                        Epsilon epsilon = Game.getEpsilon();
                        Polygon squarantine2 = new Polygon(squarantine3.getxPoints(), squarantine3.getyPoints(), 4);
                        //       if (!VSCollission) {
                        if (checkCollision(epsilon.getxCenter(), epsilon.getyCenter(), epsilon.getRadius(), squarantine2)) {
                            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(squarantine3.getX(), squarantine3.getY()), 10, true, false, epsilon, squarantine3);
                            ObjectsIntersection.getIntersectionPoints().add(point);
                            boolean melee = point.isMeleeAttack();
                            epsilon.decreaseHP(EnemyType.Squarantine, melee);
                        }
                    }

                }
//                for (BarricadosFrame barricadosFrame : Game.getBarricadosFrames()) {
//                    Epsilon epsilon = Game.getEpsilon();
//                    Barricados barricados = barricadosFrame.getBarricados();
//
//
//                        Rectangle barricados1 = new Rectangle(barricados.getLocalX()+barricados.getLocalFrame().getX(),
//                                barricados.getLocalY()+barricados.getLocalFrame().getY(),
//                                barricados.getWidth(), barricados.getHeight());
//
//                        Rectangle epsilon1 = new Rectangle(epsilon.getLocalX()+epsilon.getLocalFrame().getX(),
//                                epsilon.getLocalY()+epsilon.getLocalFrame().getY()
//                                , epsilon.getWidth(), epsilon.getHeight());
//
//
//                        if (epsilon1.intersects(barricados1)) {
//
//
//                            IntersectionPoint intersectionPoint = new IntersectionPoint(new Point2D.Double(barricados.getLocalX(),
//                                    barricados.getLocalY()),
//                                    5, false, false, barricados, epsilon);
//                            intersectionPoints.add(intersectionPoint);
//                        }
//                    }


                for (BlackOrbFrame blackOrbFrame : Game.getBlackOrbFrames()) {
                    Epsilon epsilon = Game.getEpsilon();
                    Rectangle epsilon1 = new Rectangle(epsilon.getLocalX(),
                            epsilon.getLocalY(),epsilon.getWidth(),epsilon.getHeight());

                    for (Laser laser : blackOrbFrame.getBlackOrb().getLasers()) {
                        if (blackOrbFrame.equals(Game.getEpsilon().getLocalFrame())) {
                            if(laser.isVisible()){

                            Rectangle laserBounds = new Rectangle(-laser.getWidth()/2,-laser.getHeight()/2,laser.getWidth(),laser.getHeight());
                            Area laserArea = new Area(laserBounds);
                            AffineTransform transform = new AffineTransform();
                            transform.translate(laser.getLocalX(),
                                    laser.getLocalY());
                            transform.rotate(Math.toRadians(laser.getAngle()), laser.getWidth() / 2.0, laser.getHeight() / 2.0);
                            laserArea.transform(transform);

                            long currentTime = System.currentTimeMillis();
                            if(laserArea.intersects(epsilon1)) {
                                if ((currentTime - lastTime5) / 1000 >= 1) {
                                    lastTime5 = currentTime;
                                    epsilon.decreaseHP(12);
                                }
                            }
                            }
                        }
                    }
                }
            }
            public  void wyrmInterectsEntity() {
                for (Wyrm wyrm : Game.getWyrms()) {
                    for (GameObjects entity : Game.getEnemies()) {
                        if (!entity.equals(wyrm) && !wyrm.isDead() && !entity.isDead() && entity.isVisible()) {
                            Rectangle wyrm1 = new Rectangle(wyrm.getLocalX() + wyrm.getLocalFrame().getX(),
                                    wyrm.getLocalY() + wyrm.getLocalFrame().getY(),
                                    wyrm.getWidth(), wyrm.getHeight());
                            Rectangle entity1 = new Rectangle(entity.getLocalX() + entity.getLocalFrame().getX(),
                                    entity.getLocalY() + entity.getLocalFrame().getY(), entity.getWidth(), entity.getHeight());

                            if (wyrm1.intersects(entity1)) {
                                wyrm.setCollisionNumber(wyrm.getCollisionNumber()+1);

                            }
                        }
                    }
                }
            }
    public static ArrayList<IntersectionPoint> getIntersectionPoints(){
        if(intersectionPoints==null) intersectionPoints = new ArrayList<>();
        return intersectionPoints;
    }
}
