package view.entityViews.smiley;

import Controller.MouseListener;
import javax.swing.*;
import java.awt.*;

public class SmileyPunchPanel extends JPanel {

    private Rectangle bound;
    private JFrame itsFrame;

    public SmileyPunchPanel(int x, int y){

        this.setSize(x,y);
        this.setBackground(Color.BLACK);
        MouseListener mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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
