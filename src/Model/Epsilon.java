package Model;

import Controller.ImpactSpeed;
import Controller.Intersection;
import View.GameInfo;
import View.GamePanel;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class Epsilon extends GameObjects implements movable {

    private double xVelocity;
    private double yVelocity;
    private int radius;
    private double xCenter = getX()+radius;
    private double yCenter = getY()+radius;
    private int HP = 100;
    private int XP = GameInfo.getXP();
    private double xVelocity2;
    private double yVelocity2;
    private double xVelocity3;
    private double yVelocity3;
    private ArrayList<Point2D> vertex = new ArrayList<>();
    private int vertexNumber;



    public Epsilon(int x, int y) throws IOException {
        super(x, y);
        setX(x);
        setY(y);

    }

    @Override
    public void move() {
        if(Intersection.getIntersectionPoint()!=null){

            double xPoint = Intersection.getIntersectionPoint().getX();
            double yPoint = Intersection.getIntersectionPoint().getY();
            double angle2 =  Math.atan2(getY() - yPoint, getX() - xPoint);
            double impactSpeed = ImpactSpeed.getImpactspeed(this);
            xVelocity2 = Math.cos(angle2) * 3;
            yVelocity2 = Math.sin(angle2) * 3;
        }else{
            xVelocity2 = 0;
            yVelocity2 = 0;
        }

        if(Intersection.getIntersectionPoint2()!=null){

            double xPoint = Intersection.getIntersectionPoint3().getX();
            double yPoint = Intersection.getIntersectionPoint3().getY();
            double angle3 =  Math.atan2(getY() - yPoint, getX() - xPoint);
            double impactSpeed = 4;
            xVelocity3 = Math.cos(angle3) * 3;
            yVelocity3 = Math.sin(angle3) * 3;
        }else{
            xVelocity3 = 0;
            yVelocity3 = 0;
        }
        if(getY()-radius<=0){
            yVelocity2=0;
          //  xVelocity2=0;
         //   xVelocity3=0;
            yVelocity3=0;
        }
        if(getY() + radius>= GamePanel.getFRAME_HEIGHT()) {
            yVelocity2=0;
         //   xVelocity2=0;
        //    xVelocity3=0;
            yVelocity3=0;
        }
        if(getX() - radius<=0) {
         //   yVelocity2=0;
            xVelocity2=0;
            xVelocity3=0;
         //   yVelocity3=0;
        }
        if(getX() + radius>= GamePanel.getFRAME_WIDTH()) {
         //   yVelocity2=0;
            xVelocity2=0;
            xVelocity3=0;
         //   yVelocity3=0;
        }

        this.setX((int) (getX()+xVelocity+xVelocity2+xVelocity3));
        this.setY((int) (getY()+yVelocity+yVelocity2+yVelocity3));
        this.setxCenter( (getxCenter()+xVelocity+xVelocity2));
        this.setyCenter( (getyCenter()+yVelocity+yVelocity2));

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
    public void increaseXP(){
        setXP(getXP()+5);
    }
    public void decreaseHP(){
        setHP(getHP()-10);

    }

    public ArrayList<Point2D> getVertex() {
        return vertex;
    }

    public void setVertex(ArrayList<Point2D> vertex) {
        this.vertex = vertex;
    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    public void setVertexNumber(int vertexNumber) {
        this.vertexNumber = vertexNumber;
    }
    public void addVertex() {
        System.out.println(1111);
        vertex.clear();
        double angle = 2 * Math.PI / vertexNumber;
        for (int i = 0; i < vertexNumber; i++) {
            int dotX = (int) (getX() + radius / 2 + radius / 2 * Math.cos(i * angle));
            int dotY = (int) (getY() + radius / 2 + radius / 2 * Math.sin(i * angle));
            vertex.add(new Point2D.Double(dotX,dotY));
            System.out.println(vertex.size());
        }
    }

}
