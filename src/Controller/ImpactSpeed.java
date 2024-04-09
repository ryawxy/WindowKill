package Controller;

import Model.Squarantine;
import Model.Trigorath;
import View.GamePanel;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ImpactSpeed {

    static ArrayList<Trigorath> trigoraths = GamePanel.getTrigoraths();
    ArrayList<Squarantine> squarantines = GamePanel.getSquarantine();
    static double distance;
    static int impactspeed;
    public ImpactSpeed(){}

    public static int impactspeed() {
        if (Intersection.getIntersectionPoint() != null) {
            Point2D point = Intersection.getIntersectionPoint();
            for (Trigorath trigorath : trigoraths) {
                distance = Math.sqrt(Math.pow(trigorath.getTrigorathXPos() - point.getX(),2)+
                        Math.pow(trigorath.getTrigorathYPos() - point.getY(),2));
                if(distance<=10){
                    return 5;

                }
                if(distance>10 && distance<=20){
                    return 4;

                }
                if(distance>20 && distance<=30){
                    return 3;

                }
                if(distance>30){
                    return 0;

                }
            }
        }
        return 0;
    }

}
