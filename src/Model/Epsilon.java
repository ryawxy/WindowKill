package Model;

import Controller.ImpactSpeed;
import Controller.Intersection;
import View.Game;
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
    private int XP = 2000;
    private double xVelocity2;
    private double yVelocity2;
    private double xVelocity3;
    private double yVelocity3;
    private double xVelocity4;
    private double yVelocity4;
    private double xVelocity5;
    private double yVelocity5;
    private double xVelocity8;
    private double yVelocity8;
    private double xVelocity9;
    private double yVelocity9;
    private ArrayList<Vertex> vertex = new ArrayList<>();
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

        if(Intersection.getIntersectionPoint3()!=null){

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
        if(Intersection.getIntersectionPoint4()!=null){

            double xPoint = Intersection.getIntersectionPoint4().getX();
            double yPoint = Intersection.getIntersectionPoint4().getY();
            double angle3 =  Math.atan2(getY() - yPoint, getX() - xPoint);
            double impactSpeed = 4;
            xVelocity4 = Math.cos(angle3) * 2;
            yVelocity4 = Math.sin(angle3) * 2;
        }else{
            xVelocity4 = 0;
            yVelocity4 = 0;
        }
        if(Intersection.getIntersectionPoint5()!=null){

            double xPoint = Intersection.getIntersectionPoint5().getX();
            double yPoint = Intersection.getIntersectionPoint5().getY();
            double angle3 =  Math.atan2(getY() - yPoint, getX() - xPoint);
            double impactSpeed = 4;
            xVelocity5 = Math.cos(angle3) * 2;
            yVelocity5 = Math.sin(angle3) * 2;
        }else{
            xVelocity5 = 0;
            yVelocity5 = 0;
        }
        if(Intersection.getIntersectionPoint8()!=null){

            double xPoint = Intersection.getIntersectionPoint8().getX();
            double yPoint = Intersection.getIntersectionPoint8().getY();
            double angle3 =  Math.atan2(getY() - yPoint, getX() - xPoint);
            double impactSpeed = 4;
            xVelocity8 = Math.cos(angle3) * 2;
            yVelocity8 = Math.sin(angle3) * 2;
        }else{
            xVelocity8 = 0;
            yVelocity8 = 0;
        }
        if(Intersection.getIntersectionPoint9()!=null){

            double xPoint = Intersection.getIntersectionPoint9().getX();
            double yPoint = Intersection.getIntersectionPoint9().getY();
            double angle3 =  Math.atan2(getY() - yPoint, getX() - xPoint);
            double impactSpeed = 4;
            xVelocity9 = Math.cos(angle3) * 2;
            yVelocity9 = Math.sin(angle3) * 2;
        }else{
            xVelocity9 = 0;
            yVelocity9 = 0;
        }
        if(getY()-radius<=0){
            yVelocity2=0;
          //  xVelocity2=0;
         //   xVelocity3=0;
            yVelocity3=0;
            yVelocity4=0;
            yVelocity5=0;
            yVelocity8 = 0;
            yVelocity9 = 0;
        }
        if(getY() + radius>= GamePanel.getFRAME_HEIGHT()) {
            yVelocity2=0;
         //   xVelocity2=0;
        //    xVelocity3=0;
            yVelocity3=0;
            yVelocity4=0;
            yVelocity5=0;
            yVelocity8 = 0;
            yVelocity9 = 0;
        }
        if(getX() - radius<=0) {
         //   yVelocity2=0;
            xVelocity2=0;
            xVelocity3=0;
         //   yVelocity3=0;
            xVelocity4=0;
            xVelocity5=0;
            xVelocity8 = 0;
            xVelocity9 = 0;
        }
        if(getX() + radius>= GamePanel.getFRAME_WIDTH()) {
         //   yVelocity2=0;
            xVelocity2=0;
            xVelocity3=0;
            xVelocity4=0;
            xVelocity5=0;
            xVelocity8 = 0;
            xVelocity9 = 0;
         //   yVelocity3=0;
        }

        this.setX((int) (getX()+xVelocity+xVelocity2+xVelocity3+xVelocity4+xVelocity5+xVelocity8+xVelocity9));
        this.setY((int) (getY()+yVelocity+yVelocity2+yVelocity3+yVelocity4+yVelocity5+yVelocity8+yVelocity9));
        this.setxCenter( (getxCenter()+xVelocity+xVelocity2+xVelocity4+xVelocity5+xVelocity8+xVelocity9));
        this.setyCenter( (getyCenter()+yVelocity+yVelocity2+yVelocity4+yVelocity5+yVelocity8+yVelocity9));

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

            Game.getSoundPlayer().playSoundEffect("src/Sound/epsilon.wav");



    }

    public ArrayList<Vertex> getVertex() {
        return vertex;
    }

    public void setVertex(ArrayList<Vertex> vertex) {
        this.vertex = vertex;
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


    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getxVelocity2() {
        return xVelocity2;
    }

    public void setxVelocity2(double xVelocity2) {
        this.xVelocity2 = xVelocity2;
    }

    public double getyVelocity2() {
        return yVelocity2;
    }

    public void setyVelocity2(double yVelocity2) {
        this.yVelocity2 = yVelocity2;
    }

    public double getxVelocity3() {
        return xVelocity3;
    }

    public void setxVelocity3(double xVelocity3) {
        this.xVelocity3 = xVelocity3;
    }

    public double getyVelocity3() {
        return yVelocity3;
    }

    public void setyVelocity3(double yVelocity3) {
        this.yVelocity3 = yVelocity3;
    }

    public double getxVelocity4() {
        return xVelocity4;
    }

    public void setxVelocity4(double xVelocity4) {
        this.xVelocity4 = xVelocity4;
    }

    public double getyVelocity4() {
        return yVelocity4;
    }

    public void setyVelocity4(double yVelocity4) {
        this.yVelocity4 = yVelocity4;
    }

    public double getxVelocity5() {
        return xVelocity5;
    }

    public void setxVelocity5(double xVelocity5) {
        this.xVelocity5 = xVelocity5;
    }

    public double getyVelocity5() {
        return yVelocity5;
    }

    public void setyVelocity5(double yVelocity5) {
        this.yVelocity5 = yVelocity5;
    }

    public double getxVelocity8() {
        return xVelocity8;
    }

    public void setxVelocity8(double xVelocity8) {
        this.xVelocity8 = xVelocity8;
    }

    public double getyVelocity8() {
        return yVelocity8;
    }

    public void setyVelocity8(double yVelocity8) {
        this.yVelocity8 = yVelocity8;
    }

    public double getxVelocity9() {
        return xVelocity9;
    }

    public void setxVelocity9(double xVelocity9) {
        this.xVelocity9 = xVelocity9;
    }

    public double getyVelocity9() {
        return yVelocity9;
    }

    public void setyVelocity9(double yVelocity9) {
        this.yVelocity9 = yVelocity9;
    }
}
