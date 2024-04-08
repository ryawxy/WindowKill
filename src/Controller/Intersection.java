package Controller;

import Model.*;
import View.GamePanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;

public class Intersection {
    GamePanel gamePanel;

    private Polygon squarantine2;
    private Polygon trigorath2;
    public Intersection(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
    }
    public Intersection(){}

    public Direction shotIntersectsFrame(ShotGun shotGun){

        if(shotGun.getX()>= GamePanel.getFRAME_WIDTH()){
            return Direction.RIGHT;
        } if(shotGun.getY() >= GamePanel.getFRAME_HEIGHT()){
            return Direction.DOWN;
        } if(shotGun.getX() <= 0){
            return Direction.LEFT;
        } if(shotGun.getY() <= 0){
            return Direction.UP;
        }
        return null;
    }
    public void shotIntersectsSquarantine(){
        //check if a shot intersects with squarantine
        // if so decrease its HP
        // if its HP reaches 0 , it dies
        for(int j = 0; j< GamePanel.getSquarantine().size(); j++) {
            Squarantine squarantine1 = GamePanel.getSquarantine().get(j);
            squarantine2 = new Polygon(squarantine1.getxPoints(),squarantine1.getyPoints(),4);
            for (int i = 0; i < ShotGun.getShots().size(); i++) {
                ShotGun shot = ShotGun.getShots().get(i);
                if (squarantine2.intersects(shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight())) {
                    ShotGun.getShots().remove(shot);
                    squarantine1.setHP(squarantine1.getHP() - 5);
                    if (squarantine1.getHP() <= 0) {
                        GamePanel.getSquarantine().remove(squarantine1);
                    }
                }
            }
        }
    }
    public void shotIntersectsTrigorath(){
        //check if a shot intersects with trigorath
        // if so decrease its HP
        // if its HP reaches 0 , it dies

        for(int j = 0; j< GamePanel.getTrigoraths().size(); j++) {
            Trigorath trigorath = GamePanel.getTrigoraths().get(j);
            trigorath2 = new Polygon(trigorath.getxPoints(),trigorath.getyPoints(),3);
            for (int i = 0; i < ShotGun.getShots().size(); i++) {
                ShotGun shot = ShotGun.getShots().get(i);
                if(trigorath2.intersects(shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight())){
                    ShotGun.getShots().remove(shot);
                    trigorath.decreaseHP();

                }
            }
        }

    }
    public void shotIntersectsSquatantine(){

        //check if a shot intersects with squarantine
        // if so decrease its HP
        // if its HP reaches 0 , it dies

        for(int j = 0; j< GamePanel.getSquarantine().size(); j++) {
            Squarantine squarantine = GamePanel.getSquarantine().get(j);
            squarantine2 = new Polygon(squarantine.getxPoints(),squarantine.getyPoints(),4);
            for (int i = 0; i < ShotGun.getShots().size(); i++) {
                ShotGun shot = ShotGun.getShots().get(i);
                if(squarantine2.intersects(shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight())){
                    ShotGun.getShots().remove(shot);
                    squarantine.decreaseHP();

                }
            }
        }


    }
    public void epsilonIntersectsCollectible(){
        // check if epsilon intersects with collectibles
        // if so add to epsilons XP

        Epsilon epsilon = GamePanel.getEpsilon();

        for(Trigorath trigorath : GamePanel.getTrigoraths()){
            if(trigorath.isShowCollectibles()){

                for(int i=0;i<trigorath.getCollectibles().size();i++){
                    Collectible collectible = trigorath.getCollectibles().get(i);
                    double xDistance = Math.abs(epsilon.getxCenter()-collectible.getXCenter());
                    double yDistance = Math.abs(epsilon.getyCenter()-collectible.getYCenter());
                    double distance = Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance,2));
                    if(distance<=collectible.getRadius()+epsilon.getRadius()){

                        epsilon.increaseXP();
                        trigorath.getCollectibles().remove(collectible);
                        System.out.println(epsilon.getXP());
                    }

                }
            }
        }

        for(Squarantine squarantine : GamePanel.getSquarantine()){
            if(squarantine.isShowCollectibles()){

                for(int i=0;i<squarantine.getCollectibles().size();i++){
                    Collectible collectible = squarantine.getCollectibles().get(i);
                    double xDistance = Math.abs(epsilon.getxCenter()-collectible.getXCenter());
                    double yDistance = Math.abs(epsilon.getyCenter()-collectible.getYCenter());
                    double distance = Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance,2));
                    if(distance<=collectible.getRadius()+epsilon.getRadius()){

                        epsilon.increaseXP();
                        squarantine.getCollectibles().remove(collectible);

                    }

                }
            }
        }
    }
    public void epsilonIntersectsEnemy(){

        Epsilon epsilon = GamePanel.getEpsilon();

        for(Trigorath trigorath : GamePanel.getTrigoraths()){
            for (int i = 0; i < trigorath.getxPoints().length; i++) {

                double distance = Math.sqrt(Math.pow(epsilon.getxCenter() - trigorath.getxPoints()[i], 2) + Math.pow(epsilon.getyCenter() - trigorath.getyPoints()[i], 2));

                if (distance <= epsilon.getRadius()) {
                    // Intersection with trigorath's vertex detected
                    epsilon.decreaseHP();

                }
            }

            for (int i = 0; i < trigorath.getxPoints().length; i++) {

                int x1 = trigorath.getxPoints()[i];
                int y1 = trigorath.getyPoints()[i];
                int x2 = trigorath.getxPoints()[(i + 1) % trigorath.getxPoints().length];
                int y2 = trigorath.getyPoints()[(i + 1) % trigorath.getyPoints().length];



                double dx = x2 - x1;
                double dy = y2 - y1;
                double dr = Math.sqrt(dx * dx + dy * dy);
                double D = x1 * y2 - x2 * y1;
                double discriminant = epsilon.getRadius()  * dr * dr - D * D;

                if (discriminant >= 0) {


                    // Intersection with trigorath's edge detected

                }

            }
        }
        for(Squarantine squarantine : GamePanel.getSquarantine()){
            for (int i = 0; i < squarantine.getxPoints().length; i++) {

                double distance = Math.sqrt(Math.pow(epsilon.getxCenter() - squarantine.getxPoints()[i], 2) + Math.pow(epsilon.getyCenter() - squarantine.getyPoints()[i], 2));

                if (distance <= epsilon.getRadius()) {
                    // Intersection with squarantine's vertex detected
                    epsilon.decreaseHP();

                }

            }

            for (int i = 0; i < squarantine.getxPoints().length; i++) {

                int x1 = squarantine.getxPoints()[i];
                int y1 = squarantine.getyPoints()[i];
                int x2 = squarantine.getxPoints()[(i + 1) % squarantine.getxPoints().length];
                int y2 = squarantine.getyPoints()[(i + 1) % squarantine.getyPoints().length];



                double dx = x2 - x1;
                double dy = y2 - y1;
                double dr = Math.sqrt(dx * dx + dy * dy);
                double D = x1 * y2 - x2 * y1;
                double discriminant = epsilon.getRadius()  * dr * dr - D * D;

                if (discriminant >= 0) {


                    // Intersection with squarantine's edge detected

                }

            }
        }
    }



}
