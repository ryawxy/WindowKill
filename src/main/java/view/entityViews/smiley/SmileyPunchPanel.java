package view.entityViews.smiley;

import Controller.MouseListener;
import javax.swing.*;
import java.awt.*;

public class SmileyPunchPanel extends JPanel {

    private Rectangle bound;
    private JFrame itsFrame;
    private SmileyPunchView smileyPunchView;

    public SmileyPunchPanel(int x, int y){

        this.setSize(x,y);
        this.setOpaque(false);
        this.setBackground(new Color(0,0,0,0));
        MouseListener mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        g.setColor(Color.BLACK);
//        g.fillRect(0,0,getWidth(),getHeight());

        Graphics2D g2D = (Graphics2D) g;
        smileyPunchView.paint(g2D);
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

    public void setSmileyPunchView(SmileyPunchView smileyPunchView) {
        this.smileyPunchView = smileyPunchView;
    }
}
