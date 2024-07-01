package View.entityViews.Barricados;

import Controller.Constants;
import Controller.Game;
import Model.Entity.Epsilon;
import Model.Entity.ShotGun;
import View.GlassFrame;
import View.ShotGunView;
import View.entityViews.EpsilonView;
import View.entityViews.TrigorathView;

import javax.swing.*;
import java.awt.*;

public class BarricadosPanel extends JPanel {

    Rectangle bounds;
    BarricadosView barricadosView = new BarricadosView();
    JFrame itsFrame;
    public  BarricadosPanel(int x,int y,int xLoc,int yLoc) {

     this.setSize(x,y);
     this.setLocation(xLoc,yLoc);
     this.setBackground(Color.BLACK);

}

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        g2D.setColor(Color.RED);
        barricadosView.paint(g2D);


        EpsilonView epsilonView = new EpsilonView(getItsFrame());
        epsilonView.paint(g2D);

        ShotGunView shotGunView = new ShotGunView(getItsFrame());
        shotGunView.paint(g2D);

        TrigorathView trigorathView = new TrigorathView(getItsFrame());
        trigorathView.paint(g2D);


    }


    public Rectangle getBound() {
        return bounds;
    }

    public void setBound(Rectangle bounds) {
        this.bounds = bounds;
    }

    public JFrame getItsFrame() {
        return itsFrame;
    }

    public void setItsFrame(JFrame itsFrame) {
        this.itsFrame = itsFrame;
    }
}
