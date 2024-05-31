package Model;

import Controller.Game;
import View.GamePanel;

public class Vertex extends GameObjects implements movable{

    private double xVelocity;
    private double yVelocity;
    private int radius = 10;
    private double xCenter = getX()+radius;
    private double yCenter = getY()+radius;
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



    public Vertex(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
        Epsilon epsilon = Game.getEpsilon();
//        if(Intersection.getIntersectionPoint()!=null){
//
//            double xPoint = Intersection.getIntersectionPoint().getX();
//            double yPoint = Intersection.getIntersectionPoint().getY();
//            double angle2 =  Math.atan2(getY() - yPoint, getX() - xPoint);
//            double impactSpeed = ImpactSpeed.getImpactspeed(this);
//            xVelocity2 = Math.cos(angle2) * 3;
//            yVelocity2 = Math.sin(angle2) * 3;
//        }else{
//            xVelocity2 = 0;
//            yVelocity2 = 0;
//        }

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
        if(epsilon.getY()-epsilon.getRadius()<=0){
            yVelocity2=0;
            //  xVelocity2=0;
            //   xVelocity3=0;
            yVelocity3=0;
            yVelocity4=0;
        }
        if(epsilon.getY() + epsilon.getRadius()>= GamePanel.getFRAME_HEIGHT()) {
            yVelocity2=0;
            //   xVelocity2=0;
            //    xVelocity3=0;
            yVelocity3=0;
            yVelocity4=0;
        }
        if(epsilon.getX() - epsilon.getRadius()<=0) {
            //   yVelocity2=0;
            xVelocity2=0;
            xVelocity3=0;
            //   yVelocity3=0;
            xVelocity4=0;
        }
        if(epsilon.getX() +epsilon.getRadius()>= GamePanel.getFRAME_WIDTH()) {
            //   yVelocity2=0;
            xVelocity2=0;
            xVelocity3=0;
            xVelocity4=0;
            //   yVelocity3=0;
        }
        xVelocity = epsilon.getxVelocity();
        yVelocity = epsilon.getyVelocity();
        xVelocity2 = epsilon.getxVelocity2();
        yVelocity2 = epsilon.getyVelocity2();



        this.setX((int) (getX()+xVelocity+xVelocity2));
        this.setY((int) (getY()+yVelocity+yVelocity2));
        this.setxCenter( (getxCenter()+xVelocity+xVelocity2));
        this.setyCenter( (getyCenter()+yVelocity+yVelocity2));


    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getxCenter() {
        return xCenter;
    }

    public void setxCenter(double xCenter) {
        this.xCenter = xCenter;
    }

    public double getyCenter() {
        return yCenter;
    }

    public void setyCenter(double yCenter) {
        this.yCenter = yCenter;
    }


    @Override
    public void setX(int x) {
        super.setX(x);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
    }
}
