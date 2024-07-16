package view.entityViews.smiley;

import Controller.MouseListener;

import view.entityViews.EpsilonView;
import view.entityViews.ShotGunView;

import javax.swing.*;
import java.awt.*;

public class SmileyPanel extends JPanel {

    private Rectangle bound;
    private JFrame itsFrame;
    private SmileyView smileyView;
    private SmileyPointerView smileyPointerView;

    public SmileyPanel(int x, int y){

        this.setSize(x,y);
        this.setOpaque(false);
        this.setBackground(new Color(0,0,0,0));
        MouseListener mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        smileyPointerView.paint(g2D);

        smileyView.paint(g2D);

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
    public void setItsFrame(JFrame itsFrame) {
        this.itsFrame = itsFrame;
    }

    public void setSmileyView(SmileyView smileyView) {
        this.smileyView = smileyView;
    }

    public void setSmileyPointerView(SmileyPointerView smileyPointerView) {
        this.smileyPointerView = smileyPointerView;
    }
}
