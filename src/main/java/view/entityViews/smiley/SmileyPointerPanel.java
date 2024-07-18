package view.entityViews.smiley;

import Controller.MouseListener;


import view.entityViews.EpsilonView;
import view.entityViews.ShotGunView;

import javax.swing.*;
import java.awt.*;

public class SmileyPointerPanel extends JPanel {

    private Rectangle bound;
    private JFrame itsFrame;
    private SmileyPointerView smileyPointerView;
    private SmileyView smileyView;
    private EpsilonView epsilonView;
    private ShotGunView shotGunView;

    public SmileyPointerPanel(int x, int y){

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

        smileyView.paint(g2D);
        smileyPointerView.paint(g2D);

        epsilonView = new EpsilonView(itsFrame);
        epsilonView.paint(g2D);

        shotGunView = new ShotGunView(itsFrame);
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

    public void setSmileyPointerView(SmileyPointerView smileyPointerView) {
        this.smileyPointerView = smileyPointerView;
    }

    public void setSmileyView(SmileyView smileyView) {
        this.smileyView = smileyView;
    }
}
