package Model;

import Model.enums.EnemyType;

public class Collectible extends GameObjects  {

    private int width;
    private int height;
    private int radius;
    private EnemyType enemyType;
    private int timer;
    private boolean show;
    public Collectible(int x, int y) {
        super(x, y);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(EnemyType enemyType) {
        this.enemyType = enemyType;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public int getXCenter(){
        return getX()+radius;
    }
    public int getYCenter(){
        return getY()+radius;
    }
}
