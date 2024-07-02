package View.entityViews.BlackOrb;

import Model.Entity.Epsilon;
import View.entityViews.EpsilonView;

import javax.swing.*;
import java.awt.*;

public class BlackOrbPanel extends JPanel {

    private Rectangle bound;
    private JFrame itsFrame;
    private BlackOrbView blackOrbView = new BlackOrbView();
    private LaserView laserView ;
    private EpsilonView epsilonView;
    public BlackOrbPanel(int x, int y){

        this.setSize(x,y);
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        blackOrbView.paint(g2D);

//        laserView = new LaserView(((BlackOrbFrame)itsFrame).getBlackOrb());
//        laserView.paint(g2D);

        epsilonView = new EpsilonView(itsFrame);
        epsilonView.paint(g2D);


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

