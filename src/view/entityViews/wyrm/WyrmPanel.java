package view.entityViews.wyrm;

import Controller.MouseListener;
import view.ShotGunView;
import view.entityViews.EpsilonView;

import javax.swing.*;
import java.awt.*;

public class WyrmPanel extends JPanel {

    private JFrame itsFrame;
    WyrmView wyrmView;
    EpsilonView epsilonView;

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

        wyrmView.paint(g2D);

        epsilonView = new EpsilonView(itsFrame);
        epsilonView.paint(g2D);

        ShotGunView shotGunView = new ShotGunView(itsFrame);
        shotGunView.paint(g2D);
    }

    public void setBound(Rectangle bound) {
    }

    public void setItsFrame(JFrame itsFrame) {
        this.itsFrame = itsFrame;
    }
    public void setWyrmView(WyrmView wyrmView) {
        this.wyrmView = wyrmView;
    }

}
