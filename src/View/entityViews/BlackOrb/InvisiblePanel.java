package View.entityViews.BlackOrb;

import View.entityViews.EpsilonView;

import javax.swing.*;
import java.awt.*;

public class InvisiblePanel extends JPanel {

    EpsilonView epsilonView;
    JFrame itsFrame;
    Rectangle bound;
    public InvisiblePanel(int x,int y){

        this.setSize(x,y);
        this.setBackground(new Color(0,0,0,0));


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        epsilonView = new EpsilonView(itsFrame);
    }

    public JFrame getItsFrame() {
        return itsFrame;
    }

    public void setItsFrame(JFrame itsFrame) {
        this.itsFrame = itsFrame;
    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }
}
