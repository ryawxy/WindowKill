package View;

import Controller.Constants;
import Controller.MouseListener;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;


public class GamePanel extends JPanel {

    private static Epsilon epsilon;
    private static ShotGun shotGun;
    private Trigorath trigorath;
    private Squarantine squarantine;
    private static final ArrayList<Squarantine> squarantines = new ArrayList<>();
    private static final ArrayList<Trigorath> trigoraths = new ArrayList<>();
    private static   int FRAME_WIDTH = Constants.getFrameWidth();
    private static   int FRAME_HEIGHT = Constants.getFrameHeight();

    private Dimension SCREEN_SIZE = Constants.getFrameDimension();
    private MouseListener mouseListener;
    int [] xPoints = {50,40,60};
    int [] yPoints = {20,40,40};

    int [] xPoints2 = {150,140,160};
    int [] yPoints2 = {120,140,140};

    int [] xPoints3 = {250,240,260};
    int [] yPoints3 = {220,240,240};

    int [] xPoints4 = {75,95,95,75};
    int [] yPoints4 = {75,75,95,95};
    Trigorath trigorath2;
    Trigorath trigorath3;




    public GamePanel() throws IOException {






        epsilon = new Epsilon(200,200);
        epsilon.setRadius(Constants.getEpsilonRadius());

        shotGun = new ShotGun((int) epsilon.getxCenter(), (int) epsilon.getyCenter());
        shotGun.setWidth(Constants.getShotGunWidth());
        shotGun.setHeight(Constants.getShotGunHeight());
        ShotGun.getShots().add(shotGun);

        squarantine = new Squarantine(85,85);
        squarantine.setxPoints(xPoints4);
        squarantine.setyPoints(yPoints4);
        squarantines.add(squarantine);

        trigorath = new Trigorath(50,33);
        trigorath.setxPoints(xPoints);
        trigorath.setyPoints(yPoints);
        trigoraths.add(trigorath);

        trigorath2 = new Trigorath(150,133);
        trigorath2.setxPoints(xPoints2);
        trigorath2.setyPoints(yPoints2);
        trigoraths.add(trigorath2);

        trigorath3 = new Trigorath(250,233);
        trigorath3.setxPoints(xPoints3);
        trigorath3.setyPoints(yPoints3);
        trigoraths.add(trigorath3);


        mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);

        setBorder(BorderFactory.createLineBorder(Color.black,5));
        setBackground(Color.BLACK);
        setSize(SCREEN_SIZE);
        setLocationToCenter(GlassFrame.getINSTANCE());
        GlassFrame.getINSTANCE().setContentPane(this);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if(epsilon.getX()+epsilon.getRadius() > FRAME_WIDTH){
                    epsilon.setX(FRAME_WIDTH - epsilon.getRadius());
                } if(epsilon.getY() + epsilon.getRadius() > FRAME_HEIGHT){
                    epsilon.setY(FRAME_HEIGHT - epsilon.getRadius());
                }

                repaint();
            }
        });

        this.setLayout(null);
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        //paint epsilon
        g2D.setColor(Color.RED);
        g2D.drawOval(epsilon.getX(), epsilon.getY(), epsilon.getRadius(), epsilon.getRadius());


        //paint epsilon's shotGun
        for (ShotGun shotGun1 : ShotGun.getShots()) {
            if (shotGun1.isOnFire()) {
                if (shotGun1.isVisible()){
                    g2D.setColor(Color.WHITE);

                g2D.fillRect(shotGun1.getX(), shotGun1.getY(),
                        shotGun1.getWidth(), shotGun1.getHeight());
            }
        }
    }
        //paint trigorath

        // if trigorath is alive paint it
        // if not show its collectible
        for(int i=0;i<trigoraths.size();i++) {
            Trigorath trigorath1 = trigoraths.get(i);
            if(!trigorath1.isDead()) {
                g2D.setColor(Color.YELLOW);
                g2D.drawPolygon(trigorath1.getxPoints(), trigorath1.getyPoints(), 3);
            }else{
                if(trigorath1.isShowCollectibles()){

                    g2D.setColor(Color.orange);
                    for(int j=0;j<trigorath1.getCollectibles().size();j++){


                        Collectible collectible = trigorath1.getCollectibles().get(j);
                        g2D.fillOval(collectible.getX(),collectible.getY(),collectible.getRadius(),collectible.getRadius());

                    }
                }
            }
        }

        //  paint squarantine

        // if squarantine is alive paint it
        // if not show its collectible
        for(int i=0;i<squarantines.size();i++) {
            Squarantine squarantine1 = squarantines.get(i);
            if(!squarantine1.isDead()) {
                g2D.setColor(Color.GREEN);
                g2D.drawPolygon(squarantine1.getxPoints(), squarantine1.getyPoints(), 4);
            }else{
                if(squarantine1.isShowCollectibles()){

                    g2D.setColor(Color.orange);
                    for(int j=0;j<squarantine1.getCollectibles().size();j++){


                        Collectible collectible = squarantine1.getCollectibles().get(j);
                        g2D.fillOval(collectible.getX(),collectible.getY(),collectible.getRadius(),collectible.getRadius());

                    }
                }
            }
        }

    }

    public static Epsilon getEpsilon() {
        return epsilon;
    }
    public static ShotGun getShotGun(){
        return shotGun;
    }

    public static int getFRAME_WIDTH() {
        return FRAME_WIDTH;
    }

    public  void setFRAME_WIDTH(int FRAME_WIDTH) {
        this.FRAME_WIDTH = FRAME_WIDTH;
    }

    public static int getFRAME_HEIGHT() {
        return FRAME_HEIGHT;
    }

    public void setFRAME_HEIGHT(int FRAME_HEIGHT) {
        this.FRAME_HEIGHT = FRAME_HEIGHT;
    }
    public Trigorath gettrigorath(){
        return trigorath;
    }

    public static ArrayList<Squarantine> getSquarantine() {
        return squarantines;
    }
    public static ArrayList<Trigorath> getTrigoraths(){
        return trigoraths;
    }

    public void setLocationToCenter(GlassFrame glassFrame){
        setLocation(glassFrame.getWidth()/2-getWidth()/2,glassFrame.getHeight()/2-getHeight()/2);
    }
}
