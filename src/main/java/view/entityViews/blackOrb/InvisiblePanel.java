package view.entityViews.blackOrb;

import Controller.MouseListener;
import view.entityViews.EpsilonView;
import view.entityViews.VertexView;

import javax.swing.*;
import java.awt.*;

public class InvisiblePanel extends JPanel {

    EpsilonView epsilonView;
    VertexView vertexView;
    JFrame itsFrame;
    Rectangle bound;
    public InvisiblePanel(int x,int y){

        this.setSize(x,y);
        this.setOpaque(false);
        this.setBackground(new Color(0,0,0,0));
        addMouseListener(new MouseListener(this));


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        epsilonView = new EpsilonView(itsFrame);
        epsilonView.paint(g2D);

        vertexView = new VertexView(itsFrame);
        vertexView.paint(g2D);


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
