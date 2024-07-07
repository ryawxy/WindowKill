package view.entityViews.smiley;

import Controller.MouseListener;
import view.entityViews.ShotGunView;
import view.entityViews.EpsilonView;

import javax.swing.*;
import java.awt.*;

public class SmileyPanel extends JPanel {

    private Rectangle bound;
    private JFrame itsFrame;
    private SmileyView smileyView;
   private EpsilonView epsilonView;
   private ShotGunView shotGunView;

    public SmileyPanel(int x, int y){

        this.setSize(x,y);
        this.setBackground(Color.BLACK);
        MouseListener mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        smileyView = new SmileyView(((SmileyFrame)itsFrame).getSmiley());
        smileyView.paint(g2D);

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

    public JFrame getItsFrame() {
        return itsFrame;
    }

    public void setItsFrame(JFrame itsFrame) {
        this.itsFrame = itsFrame;
    }
}
