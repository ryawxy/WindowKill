package View.entityViews.wyrm;

import Controller.MouseListener;
import View.ShotGunView;
import View.entityViews.EpsilonView;

import javax.swing.*;
import java.awt.*;

public class WyrmPanel extends JPanel {

    private Rectangle bound;
    private JFrame itsFrame;
    WyrmView wyrmView;
    EpsilonView epsilonView;
    private double rotationAngle;

    public WyrmPanel(int x, int y){

        this.setSize(x,y);
        this.setBackground(Color.BLACK);
        MouseListener mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        setRotationAngle(getRotationAngle()+0.02);
        WyrmView wyrmView1 = new WyrmView(((WyrmFrame)itsFrame).getWyrm(),rotationAngle);

        wyrmView1.paint(g2D);

        epsilonView = new EpsilonView(itsFrame);
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

    public WyrmView getWyrmView() {
        return wyrmView;
    }

    public void setWyrmView(WyrmView wyrmView) {
        this.wyrmView = wyrmView;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
}
