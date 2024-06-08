package Model;

import java.util.ArrayList;

public class Omenoct extends GameObjects implements Movable{
    private  int HP = 20;
    private boolean dead;
    private boolean showCollectibles;
    private final ArrayList<ShotGun> shots = new ArrayList<>();
    private boolean visible = true ;
    private final ArrayList<Collectible> collectibles = new ArrayList<>();
    private int[] xPoints = {0,0,0,0,0,0};
    private int[] yPoints = {0,0,0,0,0,0};

    public Omenoct(int x, int y) {
        super(x, y);
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    @Override
    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }

    public ArrayList<ShotGun> getShots() {
        return shots;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
    }

    @Override
    public void move() {

    }

    public int[] getxPoints() {
        return xPoints;
    }

    public void setxPoints(int[] xPoints) {
        this.xPoints = xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    public void setyPoints(int[] yPoints) {
        this.yPoints = yPoints;
    }
}
