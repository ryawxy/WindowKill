package Model;

import java.awt.geom.Point2D;

public class IntersectionPoint {

    private Point2D point;
    private int elapsedTime = 0;
    private final int time;
    private boolean meleeAttack;
    //epsilon got attacked
    private boolean enemyAttack;
    //enemy attack from epsilon
    private GameObjects entity1;
    private GameObjects entity2;

    public IntersectionPoint(Point2D point, int time,boolean meleeAttack,boolean enemyAttack,GameObjects entity1,GameObjects entity2) {
        this.point = point;
        this.time = time;
        this.enemyAttack = enemyAttack;
        this.meleeAttack = meleeAttack;
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

    public Point2D getPoint() {
        return point;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getTime() {
        return time;
    }

    public boolean isMeleeAttack() {
        return meleeAttack;
    }

    public void setMeleeAttack(boolean meleeAttack) {
        this.meleeAttack = meleeAttack;
    }

    public boolean isEnemyAttack() {
        return enemyAttack;
    }

    public void setEnemyAttack(boolean enemyAttack) {
        this.enemyAttack = enemyAttack;
    }

    public GameObjects getEntity1() {
        return entity1;
    }

    public GameObjects getEntity2() {
        return entity2;
    }

    public void setEntity1(GameObjects entity1) {
        this.entity1 = entity1;
    }

    public void setEntity2(GameObjects entity2) {
        this.entity2 = entity2;
    }
}
