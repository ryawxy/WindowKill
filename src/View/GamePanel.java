package View;

import Controller.Constants;
import Controller.GameInfo;
import Controller.GameLoop;
import Controller.MouseListener;
import Model.*;
import myproject.MyProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;


public class GamePanel extends JPanel {

    static GamePanel INSTANCE;
    private static Epsilon epsilon;
    private static ShotGun shotGun;

    private static ArrayList<Squarantine> squarantines = new ArrayList<>();
    private static ArrayList<Trigorath> trigoraths = new ArrayList<>();
    private static int FRAME_WIDTH = 700;
    private static int FRAME_HEIGHT = 700;


    private Dimension SCREEN_SIZE = new Dimension(700, 700);
    private MouseListener mouseListener;
    private static double angle;


    public GamePanel() throws IOException {



//        Robot robot = null;
//        try {
//            robot = new Robot();
//        } catch (AWTException ex) {
//            throw new RuntimeException(ex);
//        }
//        robot.keyPress(KeyEvent.VK_WINDOWS);
//        robot.keyPress(KeyEvent.VK_D);
//        robot.keyRelease(KeyEvent.VK_WINDOWS);
//        robot.keyRelease(KeyEvent.VK_D);
        epsilon = new Epsilon(200, 200);
        epsilon.setRadius(Constants.getEpsilonRadius());
        epsilon.setXP(SkillTreeFrame.getCurrentXP());

        shotGun = new ShotGun((int) epsilon.getxCenter(), (int) epsilon.getyCenter());
        shotGun.setWidth(Constants.getShotGunWidth());
        shotGun.setHeight(Constants.getShotGunHeight());
        //    ShotGun.getShots().add(shotGun);


        mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);

        setBorder(BorderFactory.createLineBorder(Color.black, 5));
        setBackground(Color.BLACK);
        setSize(SCREEN_SIZE);
        setLocationToCenter(GlassFrame.getINSTANCE());
        GlassFrame.getINSTANCE().setContentPane(this);

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                double dx = e.getX() - epsilon.getxCenter();
                double dy = e.getY() - epsilon.getyCenter();
                angle = Math.atan2(dy, dx);

            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if(epsilon.getX()+epsilon.getRadius() > FRAME_WIDTH){
                    epsilon.setX(FRAME_WIDTH - epsilon.getRadius());
                    for(Vertex vertex : epsilon.getVertex()){
                        vertex.setX(FRAME_WIDTH-epsilon.getRadius());
                    }
                } if(epsilon.getY() + epsilon.getRadius() > FRAME_HEIGHT){
                    epsilon.setY(FRAME_HEIGHT - epsilon.getRadius());
                    for(Vertex vertex : epsilon.getVertex()){
                        vertex.setX(FRAME_HEIGHT-epsilon.getRadius());
                    }
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
                if (shotGun1.isVisible()) {
                    g2D.setColor(Color.WHITE);

                    g2D.fillRect(shotGun1.getX(), shotGun1.getY(),
                            shotGun1.getWidth(), shotGun1.getHeight());
                }
            }
        }
        //paint trigorath

        // if trigorath is alive paint it
        // if not show its collectible
        for (int i = 0; i < trigoraths.size(); i++) {
            Trigorath trigorath1 = trigoraths.get(i);
            if (!trigorath1.isDead()) {
                g2D.setColor(Color.YELLOW);
                g2D.drawPolygon(trigorath1.getxPoints(), trigorath1.getyPoints(), 3);
            } else {
                if (trigorath1.isShowCollectibles()) {

                    g2D.setColor(Color.orange);
                    for (int j = 0; j < trigorath1.getCollectibles().size(); j++) {


                        Collectible collectible = trigorath1.getCollectibles().get(j);
                        g2D.fillOval(collectible.getX(), collectible.getY(), collectible.getRadius(), collectible.getRadius());

                    }
                }
            }
        }

        //  paint squarantine

        // if squarantine is alive paint it
        // if not show its collectible
        for (int i = 0; i < squarantines.size(); i++) {
            Squarantine squarantine1 = squarantines.get(i);
            if (!squarantine1.isDead()) {
                g2D.setColor(Color.GREEN);
                g2D.drawPolygon(squarantine1.getxPoints(), squarantine1.getyPoints(), 4);
            } else {
                if (squarantine1.isShowCollectibles()) {

                    g2D.setColor(Color.orange);
                    for (int j = 0; j < squarantine1.getCollectibles().size(); j++) {


                        Collectible collectible = squarantine1.getCollectibles().get(j);
                        g2D.fillOval(collectible.getX(), collectible.getY(), collectible.getRadius(), collectible.getRadius());

                    }
                }
            }
        }
        //paint epsilons vertex

        for (Vertex vertex : getEpsilon().getVertex()) {

            double vertexAngle = angle + epsilon.getVertex().indexOf(vertex) * 2 * Math.PI / epsilon.getVertexNumber();
            vertex.setX((int) (epsilon.getxCenter() + epsilon.getRadius()/2 * Math.cos(vertexAngle))-18);
            vertex.setY((int) (epsilon.getyCenter() + epsilon.getRadius()/2 * Math.sin(vertexAngle))-18);
            g2D.setColor(Color.WHITE);
            g2D.fillOval((int) (vertex.getX()), (int) (vertex.getY()), vertex.getRadius(), vertex.getRadius());
        }


        //paint Game info
        g2D.setColor(Color.WHITE);
        g2D.drawString("✦"+MyProject.getGameInfo().getXP(),5,20);
        if(epsilon.getHP()>=0) {
            g2D.drawString("♥" + epsilon.getHP(), 100, 20);
        }else{
            g2D.drawString("♥" + 0, 100, 20);
        }
        g2D.drawString(GameLoop.getMinutes()+":"+GameLoop.getSeconds(),195,20);
        g2D.drawString(GameInfo.getCurrentWave()+"/3",290,20);
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

    public static void setFRAME_WIDTH(int FRAME_WIDTH) {
        GamePanel.FRAME_WIDTH = FRAME_WIDTH;
    }

    public static int getFRAME_HEIGHT() {
        return FRAME_HEIGHT;
    }

    public static void setFRAME_HEIGHT(int FRAME_HEIGHT) {
        GamePanel.FRAME_HEIGHT = FRAME_HEIGHT;
    }


    public static ArrayList<Squarantine> getSquarantine() {
        return squarantines;
    }
    public static ArrayList<Trigorath> getTrigoraths(){
        return trigoraths;
    }

    public static void setSquarantines(ArrayList<Squarantine> squarantines) {
        GamePanel.squarantines = squarantines;
    }

    public static void setTrigoraths(ArrayList<Trigorath> trigoraths) {
        GamePanel.trigoraths = trigoraths;
    }

    public void setLocationToCenter(GlassFrame glassFrame){
        setLocation(glassFrame.getWidth()/2-getWidth()/2,glassFrame.getHeight()/2-getHeight()/2);
    }
    public static GamePanel getINSTANCE() throws IOException {
        if(INSTANCE == null){
            INSTANCE = new GamePanel();
        }
        return INSTANCE;
    }

    public static double getAngle() {
        return angle;
    }
}
