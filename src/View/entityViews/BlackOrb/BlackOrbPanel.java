package View.entityViews.BlackOrb;

import Controller.MouseListener;
import View.ShotGunView;
import View.entityViews.EpsilonView;

import javax.swing.*;
import java.awt.*;

public class BlackOrbPanel extends JPanel {

    private Rectangle bound;
    private JFrame itsFrame;
    private final BlackOrbView blackOrbView = new BlackOrbView();

    public BlackOrbPanel(int x, int y){

        this.setSize(x,y);
        this.setBackground(Color.BLACK);
        MouseListener mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        blackOrbView.paint(g2D);

        LaserView laserView = new LaserView(((BlackOrbFrame) itsFrame).getBlackOrb());
        laserView.paint(g2D);

        EpsilonView epsilonView = new EpsilonView(itsFrame);
        epsilonView.paint(g2D);

        ShotGunView shotGunView = new ShotGunView(itsFrame);
        shotGunView.paint(g2D);


    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }

    public JFrame getItsFrame() {
        return itsFrame;
    }

    public void setItsFrame(JFrame itsFrame) {
        this.itsFrame = itsFrame;
    }
}

