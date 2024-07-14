package Model.entity;

import Controller.Constants;
import Controller.Game;
import Controller.KeyListener;
import Model.*;
import Model.enums.Ability;
import Model.enums.EnemyType;
import view.GlassFrame;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Epsilon extends GameObjects implements Movable {

    private double xVelocity;
    private double yVelocity;
    private int radius;
    private int HP = 100;
    private double xVelocity2;
    private double yVelocity2;
    private final ArrayList<Model.entity.Vertex> vertex = new ArrayList<>();
    private int vertexNumber;
    private final ArrayList<JFrame> localFrames = new ArrayList<>();

    public Epsilon(int x, int y) throws IOException {
        super(x, y);
        setX(x);
        setY(y);
        setLocalX(x);
        setLocalY(y);
        setHP(100);
        setLocalFrame(GlassFrame.getINSTANCE());
        setPreviousLocalFrame(GlassFrame.getINSTANCE());
        getLocalFrames().add(GlassFrame.getINSTANCE());


    }

    @Override
    public void move() {

        xVelocity2 = 0;
        yVelocity2 = 0;

        double xPoint2 = getLocalX();
        double yPoint2 = getLocalY();
        Point2D point2D = new Point2D.Double(xPoint2,yPoint2);


        for(IntersectionPoint point : ObjectsIntersection.getIntersectionPoints()){

                        double xPoint = point.getPoint().getX();
            double yPoint = point.getPoint().getY();
            double angle3 =  Math.atan2(getLocalY() - yPoint, getLocalX() - xPoint);
            double distance = point2D.distance(point.getPoint());

                            xVelocity2 += Math.cos(angle3) * Constants.getImpactSpeed((int) distance);
                yVelocity2 += Math.sin(angle3) * Constants.getImpactSpeed((int) distance);
        }
        if(getLocalFrame().getY()-radius<=0){


            yVelocity2 = 0;
        }
        if(getLocalFrame().getY() + radius>= getLocalFrame().getHeight()) {
            yVelocity2 = 0;

        }
        if(getLocalFrame().getX() - radius<=0) {

            xVelocity2 = 0;

        }
        if(getLocalFrame().getX() + radius>= getLocalFrame().getWidth()) {

            xVelocity2 = 0;

        }


        this.setX((int) (getX()+xVelocity2+ getXVelocity()));
        this.setY((int) (getY()+yVelocity2+ getYVelocity()));
        this.setxCenter( (getxCenter()+xVelocity2+ getXVelocity()));
        this.setyCenter( (getyCenter()+yVelocity2+ getYVelocity()));
        this.setLocalX((int) (getLocalX()+xVelocity2+ getXVelocity()));
        this.setLocalY((int) (getLocalY()+yVelocity2+ getYVelocity()));


    }


    public double getXVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getYVelocity() {
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
        return getLocalX()+radius;
    }

    public void setxCenter(double xCenter) {
    }

    public int getyCenter() {
        return getLocalY()+radius;
    }

    public void setyCenter(double yCenter) {
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
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
            int dotX = (int) (getLocalX() + (radius-10) / 2 + radius / 2 * Math.cos(i * angle));
            int dotY = (int) (getLocalY() + (radius-10) / 2 + radius / 2 * Math.sin(i *angle));
            Vertex vertex1 = new Vertex(dotX,dotY);
            vertex.add(vertex1);

        }
    }
    @Override
    public int getHeight() {
        return getRadius();
    }

    @Override
    public void setHeight(int height) {
    }

    @Override
    public int getWidth() {
        return getRadius();
    }

    @Override
    public void setWidth(int width) {
    }
    public ArrayList<JFrame> getLocalFrames() {
        return localFrames;
    }

}
