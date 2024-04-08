package View;

import Controller.KeyListener;
import Controller.MouseListener;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame {


    JPanel panel;
    protected JPanel gamePanel;
    private static Epsilon epsilon;
    private static ShotGun shotGun;
    private  int FRAME_WIDTH = 700;
    private  int FRAME_HEIGHT = 700;
    private Timer timer;
    private int x;
    private int y;
    private Dimension SCREEN_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    private MouseListener mouseListener;

    public GameFrame() throws IOException {
        epsilon = new Epsilon(200,200);
        epsilon.setRadius(30);

        shotGun = new ShotGun(epsilon.getX(),epsilon.getY());
        shotGun.setWidth(8);
        shotGun.setHeight(3);

        mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);




        this.setSize(SCREEN_SIZE);
        this.setUndecorated(true);
        this.setTitle("Window Kill");

        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(700,700);
        panel.setBackground(Color.BLACK);



        this.add(panel);

        this.setVisible(true);

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
        if(shotGun.getxVelocity()!=0 || shotGun.getyVelocity()!=0){
            g2D.setColor(Color.WHITE);
            g2D.fillRect(shotGun.getX(),shotGun.getY(),shotGun.getWidth(),shotGun.getHeight());
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

}
