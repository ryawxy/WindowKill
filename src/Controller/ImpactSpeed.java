package Controller;

import Model.GameObjects;
import Model.Entity.Squarantine;
import Model.Entity.Trigorath;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ImpactSpeed {

    static ArrayList<Trigorath> trigoraths = Game.getTrigoraths();
    static ArrayList<Squarantine> squarantines = Game.getSquarantine();
    static double distance;
    static int impactspeed;
    static Point2D point;



    public static double getImpactspeed(GameObjects gameObjects) {

//        if(Intersection.getIntersectionPoint()!=null) {
//             point = Intersection.getIntersectionPoint();
//        }else if(Intersection.getIntersectionPoint2()!=null){
//            point = Intersection.getIntersectionPoint2();
//        }
//        else if(Intersection.getIntersectionPoint4()!=null){
//            point = Intersection.getIntersectionPoint4();
//        }

            distance = Math.sqrt(Math.pow(gameObjects.getX() - point.getX(), 2) +
                    Math.pow(gameObjects.getY() - point.getY(), 2));
            if (distance <= 60) {
                return 6;

            }
            if (distance > 70 && distance <= 80) {
                return 5;

            }
            if (distance > 90 && distance <= 100) {
                return 3;

            }
            if (distance > 100) {
                return 0;




        }
        return 0;
    }
}
