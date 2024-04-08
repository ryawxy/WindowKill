package View;

import Controller.Constants;
import Controller.MouseListener;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class GameFrame extends JFrame {

    private static final JPanel panel = new JPanel();
    private static Epsilon epsilon;
    private static ShotGun shotGun;
    private  int FRAME_WIDTH = Constants.getFrameWidth();
    private  int FRAME_HEIGHT = Constants.getFrameHeight();

    private Dimension SCREEN_SIZE = Constants.getFrameDimension();
    private MouseListener mouseListener;


    public GameFrame() throws IOException {
        epsilon = new Epsilon(200,200);
        epsilon.setRadius(Constants.getEpsilonRadius());
        epsilon.setHP(Constants.HP());
        epsilon.setXP(Constants.XP());

        shotGun = new ShotGun(epsilon.getX(),epsilon.getY());
        shotGun.setWidth(Constants.getShotGunWidth());
        shotGun.setHeight(Constants.getShotGunHeight());
        ShotGun.getShots().add(shotGun);

        mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);


        this.setSize(SCREEN_SIZE);
        this.setUndecorated(true);
        this.setTitle("Window Kill");
        Container contentPane = this.getContentPane();
        contentPane.setBackground(Color.BLACK);


//      panel.setLayout(null);
//      panel.setSize(SCREEN_SIZE);
//      panel.setBackground(Color.BLACK);
//      panel.setLocation(100,100);
//
//
//      this.add(panel);

        this.setVisible(true);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // super.componentResized(e);
                if(epsilon.getX()+epsilon.getRadius() > FRAME_WIDTH){
                    epsilon.setX(FRAME_WIDTH - epsilon.getRadius());
                } if(epsilon.getY() + epsilon.getRadius() > FRAME_HEIGHT){
                    epsilon.setY(FRAME_HEIGHT - epsilon.getRadius());
                    repaint();
                }
            }
        });

        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.setResizable(false);

    }
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g2D);

        //paint epsilon
        g2D.setColor(Color.RED);
        g2D.drawOval(epsilon.getX(),epsilon.getY(),epsilon.getRadius(),epsilon.getRadius());


        //paint epsilon's shotGun

        for (ShotGun shotGun1 : ShotGun.getShots()) {
            if(shotGun1.isOnFire()) {
                g2D.setColor(Color.WHITE);


                g2D.fillRect(shotGun1.getX(), shotGun1.getY(),
                        shotGun1.getWidth(), shotGun1.getHeight());
            }
        }
    }

    public static Epsilon getEpsilon() {
        return epsilon;
    }
    public static ShotGun getShotGun(){
        return shotGun;
    }

    public int getFRAME_WIDTH() {
        return FRAME_WIDTH;
    }

    public void setFRAME_WIDTH(int FRAME_WIDTH) {
        this.FRAME_WIDTH = FRAME_WIDTH;
    }

    public int getFRAME_HEIGHT() {
        return FRAME_HEIGHT;
    }

    public void setFRAME_HEIGHT(int FRAME_HEIGHT) {
        this.FRAME_HEIGHT = FRAME_HEIGHT;
    }
//    public static JPanel getPanel(){
//        return panel;
//    }
//
}
