package view.entityViews.barricados;

import Controller.MouseListener;
import Model.enums.BarricadosType;
import view.entityViews.EpsilonView;
import view.entityViews.NecropickView;
import view.entityViews.TrigorathView;
import view.entityViews.wyrm.WyrmView;

import javax.swing.*;
import java.awt.*;

public class BarricadosPanel extends JPanel {

    Rectangle bounds;
    JFrame itsFrame;
    BarricadosType barricadosType;
    BarricadosView barricadosView;
    private WyrmView wyrmView;
    private NecropickView necropickView;


    public  BarricadosPanel(int x, int y, BarricadosType barricadosType) {

     this.setSize(x,y);
     this.barricadosType = barricadosType;
     this.setBackground(Color.BLACK);
    MouseListener mouseListener = new MouseListener(this);
    addMouseListener(mouseListener);


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
        wyrmView.paint(g2D);
        necropickView.paint(g2D);


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

    public void setWyrmView(WyrmView wyrmView) {
        this.wyrmView = wyrmView;
    }

    public void setNecropickView(NecropickView necropickView) {
        this.necropickView = necropickView;
    }

    public void setBarricadosView(BarricadosView barricadosView) {
        this.barricadosView = barricadosView;
    }
}
