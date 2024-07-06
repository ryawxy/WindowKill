package view.entityViews.barricados;

import model.enums.BarricadosType;
import view.entityViews.EpsilonView;
import view.entityViews.TrigorathView;

import javax.swing.*;
import java.awt.*;

public class BarricadosPanel extends JPanel {

    Rectangle bounds;
    JFrame itsFrame;
    BarricadosType barricadosType;
    BarricadosView barricadosView;

    public  BarricadosPanel(int x, int y, BarricadosType barricadosType) {

     this.setSize(x,y);
     this.barricadosType = barricadosType;
     this.setBackground(Color.BLACK);
    barricadosView = new BarricadosView(barricadosType);


}

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);


        barricadosView.paint(g2D);


        EpsilonView epsilonView = new EpsilonView(getItsFrame());
        epsilonView.paint(g2D);

//        ShotGunView shotGunView = new ShotGunView(getItsFrame());
//        shotGunView.paint(g2D);

        TrigorathView trigorathView = new TrigorathView(getItsFrame());
        trigorathView.paint(g2D);


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
