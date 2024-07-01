package Model;

import Controller.Constants;
import Controller.Game;
import Model.Entity.*;
import Model.enums.Direction;
import Model.enums.EnemyType;
import Model.enums.Side;
import Model.enums.Size;
import View.GamePanel;
import View.GlassFrame;
import View.entityViews.Barricados.BarricadosFrame;
import myproject.MyProject;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectsIntersection {
    GamePanel gamePanel;

    private Polygon squarantine2;
    private Polygon trigorath2;
    private static boolean VSCollission;
    private static  boolean VTCollision;

    private static ArrayList<IntersectionPoint> intersectionPoints;
    private static long lastTime,lastTime2;
    //AOE
    private static long lastTime3,lastTime4;
    //Drown

    public ObjectsIntersection(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
    }

    public ObjectsIntersection() {
    }

    public Direction shotIntersectsFrame(ShotGun shotGun) {

        //    if(shotGun.isVisible()) {
        if (shotGun.getX() >= Game.getEpsilon().getLocalFrame().getWidth()) {
            return Direction.RIGHT;
        }
        if (shotGun.getY() >= Game.getEpsilon().getLocalFrame().getHeight()) {
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
                            else if(enemy instanceof Necropick) Game.getEpsilon().decreaseHP(5);
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

                        System.out.println(222222);
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
                        switch (enemy) {
                            case Necropick necropick ->
                                    MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 2);
                            case Omenoct omenoct -> MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 4);
                            case Archmire archmire -> {
                                if (archmire.getsize().equals(Size.LARGE))
                                    MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 6);
                                else MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 3);
                            }
                            default -> MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() + 5);
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

        for(GameObjects enemy1 : Game.getEnemies()){
            for(GameObjects enemy2 : Game.getEnemies() ){
                if(!(enemy1 instanceof Trigorath && enemy2 instanceof Squarantine) && !(enemy1 instanceof Squarantine && enemy2 instanceof Trigorath)
                && !(enemy1 instanceof Trigorath && enemy2 instanceof Trigorath) && !(enemy1 instanceof Squarantine && enemy2 instanceof Squarantine)){
                if(!enemy1.equals(enemy2)) {
                    if (enemy1.isVisible() && enemy2.isVisible()) {
                        Rectangle e1 = new Rectangle(enemy1.getX(), enemy1.getY(), enemy1.getWidth(), enemy1.getHeight());
                        Rectangle e2 = new Rectangle(enemy2.getX(), enemy2.getY(), enemy2.getWidth(), enemy2.getHeight());
                        if (e1.intersects(e2)) {
                            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(enemy1.getX(), enemy1.getY()), 10, false, false, enemy1, enemy2);
                            intersectionPoints.add(point);
                        }
                    }
                }
                }


            }
        }
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
    public void AOEIntersection() {
        for (Archmire archmire : Game.getArchmires()) {
            for (Footprint footprint : archmire.getFootprint()) {
                if (footprint.isVisible()) {
                    Point2D footprintCenter;
                    Point2D archmireCenter;
                    double arcRadius;
                    double arcRadius1;
                    Point2D enemyCenter;
                    Point2D epsilonCenter;
                    if (archmire.getsize().equals(Size.MINI)){
                        footprintCenter = new Point2D.Double(footprint.getPosition().getX() + (double) Constants.miniArchmireWidth() / 2,
                                footprint.getPosition().getY() + (double) Constants.miniArchmireHeight() / 2);
                    archmireCenter = new Point2D.Double(archmire.getX() + (double) Constants.miniArchmireWidth() / 2,
                            archmire.getY() + (double) Constants.miniArchmireHeight() / 2);}
                    else {
                        footprintCenter = new Point2D.Double(footprint.getPosition().getX() + (double) Constants.largeArchmireWidth() / 2,
                                footprint.getPosition().getY() + (double) Constants.largeArchmireHeight() / 2);
                        archmireCenter = new Point2D.Double(archmire.getX() + (double) Constants.largeArchmireWidth() / 2,
                                archmire.getY() + (double) Constants.largeArchmireHeight() / 2);
                    }
                     epsilonCenter = new Point2D.Double(Game.getEpsilon().getxCenter(), Game.getEpsilon().getyCenter());

                    if (archmire.getsize().equals(Size.MINI)) {
                        arcRadius = Constants.miniArchmireWidth();
                        arcRadius1 = Constants.miniArchmireHeight();
                    } else {
                        arcRadius = Constants.largeArchmireWidth();
                        arcRadius1 = Constants.largeArchmireHeight();
                    }
                        long currentTime = System.currentTimeMillis();
                        if (epsilonCenter.distance(footprintCenter) <= Math.abs(arcRadius - (double) Game.getEpsilon().getRadius() / 2) ||
                                epsilonCenter.distance(footprintCenter) <= Math.abs(arcRadius1 - (double) Game.getEpsilon().getRadius() / 2)) {
                            if ((currentTime - lastTime) / 1000 >= 1) {
                                lastTime = currentTime;
                                Game.getEpsilon().setHP(Game.getEpsilon().getHP() - 2);
                            }
                        }
                    if (epsilonCenter.distance(archmireCenter) <= Math.abs(arcRadius - (double) Game.getEpsilon().getRadius() / 2) ||
                            epsilonCenter.distance(footprintCenter) <= Math.abs(arcRadius1 - (double) Game.getEpsilon().getRadius() / 2)) {
                        if ((currentTime - lastTime3) / 1000 >= 1) {
                            lastTime3 = currentTime;
                            Game.getEpsilon().setHP(Game.getEpsilon().getHP() - 10);
                        }
                    }

                        for (GameObjects enemy : Game.getEnemies()) {
                            if (!enemy.equals(archmire)) {
                                enemyCenter = new Point2D.Double(enemy.getX() + (double) enemy.getWidth() / 2, enemy.getY() + (double) enemy.getHeight() / 2);

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
            public void epsilonIntersectsEnemy(){
                for(Trigorath trigorath: Game.getTrigoraths() ) {
                    if (!trigorath.isDead()) {
                        VTCollision = false;
                        Epsilon epsilon = Game.getEpsilon();
                        Polygon trigorath2 = new Polygon(trigorath.getxPoints(), trigorath.getyPoints(), 3);
                        for (Vertex vertex : epsilon.getVertex()) {
                            if (checkCollision((int) vertex.getxCenter(), (int) vertex.getyCenter(), vertex.getRadius(), trigorath2)) {
                                VTCollision = true;
                                trigorath.decreaseHP(5);
                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(trigorath.getX(), trigorath.getY()),10,false,true,vertex,trigorath);
                                ObjectsIntersection.getIntersectionPoints().add(point);
                            }
                        }
                    }
                }
                for(Trigorath trigorath3: Game.getTrigoraths() ) {
                    if (!trigorath3.isDead()) {

                        Epsilon epsilon = Game.getEpsilon();
                        if(trigorath3.getLocalFrame().equals(epsilon.getLocalFrame())){

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
                for(Omenoct omenoct: Game.getOmenocts() ) {
                    if (!omenoct.isDead()) {

                        Epsilon epsilon = Game.getEpsilon();
                        Polygon trigorath2 = new Polygon(omenoct.getxPoints(), omenoct.getyPoints(), 6);
                        //   if (!VTCollision) {
                        if (checkCollision(epsilon.getxCenter(), epsilon.getyCenter(), epsilon.getRadius(), trigorath2)) {
                            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(omenoct.getX(), omenoct.getY()),30,true,false,omenoct,epsilon);
                            ObjectsIntersection.getIntersectionPoints().add(point);
                            boolean melee = point.isMeleeAttack();
                            epsilon.decreaseHP(EnemyType.Omenoct,melee);

                        }
                    }
                }


                for(Squarantine squarantine: Game.getSquarantine() ) {
                    if (!squarantine.isDead()) {
                        VSCollission = false;
                        Epsilon epsilon = Game.getEpsilon();
                        Polygon squarantine2 = new Polygon(squarantine.getxPoints(), squarantine.getyPoints(), 4);
                        for (Vertex vertex : epsilon.getVertex()) {
                            if (checkCollision((int) vertex.getxCenter(), (int) vertex.getyCenter(), vertex.getRadius(), squarantine2)) {

                                VSCollission = true;
                                squarantine.decreaseHP(10);
                                IntersectionPoint point = new IntersectionPoint(new Point2D.Double(squarantine.getX(), squarantine.getY()),10,false,true,vertex,squarantine);
                                ObjectsIntersection.getIntersectionPoints().add(point);

                            }
                        }
                    }
                }
                for(Squarantine squarantine3: Game.getSquarantine() ){
                    if(!squarantine3.isDead()) {
                        VSCollission = false;
                        Epsilon epsilon = Game.getEpsilon();
                        Polygon squarantine2 = new Polygon(squarantine3.getxPoints(), squarantine3.getyPoints(), 4);
                        //       if (!VSCollission) {
                        if (checkCollision(epsilon.getxCenter(), epsilon.getyCenter(), epsilon.getRadius(), squarantine2)) {
                            IntersectionPoint point = new IntersectionPoint(new Point2D.Double(squarantine3.getX(), squarantine3.getY()),10,true,false,epsilon,squarantine3);
                            ObjectsIntersection.getIntersectionPoints().add(point);
                            boolean melee = point.isMeleeAttack();
                            epsilon.decreaseHP(EnemyType.Squarantine,melee);
                        }
                    }

                }
                for(BarricadosFrame barricadosFrame: Game.getBarricadosFrames()){
                    Epsilon epsilon = Game.getEpsilon();
                    Barricados barricados = barricadosFrame.getBarricados();

                    if(epsilon.getLocalFrame().equals(barricadosFrame)) {
                        Rectangle barricados1 = new Rectangle(barricados.getX(), barricados.getY(), barricados.getWidth(), barricados.getHeight());
                        Rectangle epsilon1 = new Rectangle(epsilon.getLocalX(), epsilon.getLocalY()
                                , epsilon.getWidth(), epsilon.getHeight());



                        if (epsilon1.intersects(barricados1)) {


                            IntersectionPoint intersectionPoint = new IntersectionPoint(new Point2D.Double(barricados.getX()+
                                    (double) Constants.barricadosWidth() /2,
                                    barricados.getY()+ (double) Constants.barricadosWidth() /2),
                                    10, false, false, barricados, epsilon);
                            intersectionPoints.add(intersectionPoint);
                        }
                    }

                }
            }





    public static ArrayList<IntersectionPoint> getIntersectionPoints(){
        if(intersectionPoints==null) intersectionPoints = new ArrayList<>();
        return intersectionPoints;
    }
}
