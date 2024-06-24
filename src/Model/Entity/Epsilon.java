package Model.Entity;

import Controller.Constants;
import Controller.Game;
import Controller.KeyListener;
import Model.*;
import Model.enums.Ability;
import Model.enums.EnemyType;
import View.GamePanel;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Epsilon extends GameObjects implements Movable {

    private double xVelocity;
    private double yVelocity;
    private int radius;
    private double xCenter = getX()+radius;
    private double yCenter = getY()+radius;
    private int HP = 100;
    private int XP = 2000;
    private double xVelocity2;
    private double yVelocity2;

    private ArrayList<Vertex> vertex = new ArrayList<>();
    private int vertexNumber;

    public Epsilon(int x, int y) throws IOException {
        super(x, y);
        setX(x);
        setY(y);

    }

    @Override
    public void move() {

        xVelocity2 = 0;
        yVelocity2 = 0;

        double xPoint2 = getX();
        double yPoint2 = getY();
        Point2D point2D = new Point2D.Double(xPoint2,yPoint2);


        for(IntersectionPoint point : Intersection.getIntersectionPoints()){

                        double xPoint = point.getPoint().getX();
            double yPoint = point.getPoint().getY();
            double angle3 =  Math.atan2(getY() - yPoint, getX() - xPoint);
            double distance = point2D.distance(point.getPoint());

                            xVelocity2 += Math.cos(angle3) * Constants.getImpactSpeed((int) distance);
                yVelocity2 += Math.sin(angle3) * Constants.getImpactSpeed((int) distance);
        }
        if(getY()-radius<=0){


            yVelocity2 = 0;
        }
        if(getY() + radius>= GamePanel.getFRAME_HEIGHT()) {
            yVelocity2 = 0;

        }
        if(getX() - radius<=0) {

            xVelocity2 = 0;

        }
        if(getX() + radius>= GamePanel.getFRAME_WIDTH()) {

            xVelocity2 = 0;

        }


        this.setX((int) (getX()+xVelocity2+getxVelocity()));
        this.setY((int) (getY()+yVelocity2+getyVelocity()));
        this.setxCenter( (getxCenter()+xVelocity2+getxVelocity()));
        this.setyCenter( (getyCenter()+yVelocity2+getyVelocity()));

    }


    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getxCenter() {
        return getX()+radius;
    }

    public void setxCenter(double xCenter) {
        this.xCenter = xCenter;
    }

    public int getyCenter() {
        return getY()+radius;
    }

    public void setyCenter(double yCenter) {
        this.yCenter = yCenter;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }
    public void increaseXP(int increment){
        setXP(getXP()+increment);
    }

    public void increaseHP(int increment){setHP(getHP()+increment);}
    public void decreaseHP(EnemyType enemyType, boolean meleeAttack){

        if(GameInfo.getCurrentAbility().containsKey(Ability.Melampus) && KeyListener.getKeyPressedNumber()>0) {
            Random random = new Random();
            int x = random.nextInt(100);
            if(x<5*KeyListener.getKeyPressedNumber()) {
                if (enemyType.equals(EnemyType.Squarantine) && meleeAttack) setHP(getHP() - 6);
                if (enemyType.equals(EnemyType.Trigorath) && meleeAttack) setHP(getHP() - 10);
                if (enemyType.equals(EnemyType.Omenoct) && meleeAttack) setHP(getHP() - 8);


            }
        }else{
            if (enemyType.equals(EnemyType.Squarantine) && meleeAttack) setHP(getHP() - 6);
            if (enemyType.equals(EnemyType.Trigorath) && meleeAttack) setHP(getHP() - 10);
            if (enemyType.equals(EnemyType.Omenoct) && meleeAttack) setHP(getHP() - 8);
        }
        Game.getSoundPlayer().playSoundEffect("src/Sound/epsilon.wav");
    }
    public void decreaseHP(int decrement){
        setHP(getHP()-decrement);
    }

    public double getxVelocity2() {
        return xVelocity2;
    }

    public double getyVelocity2() {
        return yVelocity2;
    }

    public ArrayList<Vertex> getVertex() {
        return vertex;
    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    public void setVertexNumber(int vertexNumber) {
        this.vertexNumber = vertexNumber;
    }
    public void addVertex() {
        vertex.clear();
        double angle = 2 * Math.PI / vertexNumber;
        for (int i = 0; i < vertexNumber; i++) {
            int dotX = (int) (getX() + (radius-10) / 2 + radius / 2 * Math.cos(i * angle));
            int dotY = (int) (getY() + (radius-10) / 2 + radius / 2 * Math.sin(i *angle));
            Vertex vertex1 = new Vertex(dotX,dotY);
            vertex.add(vertex1);

        }
    }

}
