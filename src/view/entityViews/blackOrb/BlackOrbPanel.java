package view.entityViews.blackOrb;

import Controller.MouseListener;
import view.ShotGunView;
import view.entityViews.EpsilonView;
import view.entityViews.VertexView;

import javax.swing.*;
import java.awt.*;

public class BlackOrbPanel extends JPanel {

    private Rectangle bound;
    private JFrame itsFrame;
    BlackOrbView blackOrbView;


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

        VertexView vertexView = new VertexView(itsFrame);
        vertexView.paint(g2D);


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

    public BlackOrbView getBlackOrbView() {
        return blackOrbView;
    }

    public void setBlackOrbView(BlackOrbView blackOrbView) {
        this.blackOrbView = blackOrbView;
    }
}

