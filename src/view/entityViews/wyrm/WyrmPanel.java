package view.entityViews.wyrm;

import Controller.MouseListener;
import view.entityViews.*;
import view.entityViews.barricados.BarricadosView;
import view.entityViews.blackOrb.BlackOrbView;
import view.entityViews.blackOrb.LaserView;

import javax.swing.*;
import java.awt.*;

public class WyrmPanel extends JPanel {

    private JFrame itsFrame;
   private WyrmView wyrmView;
    private NecropickView necropickView;
    private OmenoctView omenoctView;
    private ArchmireView archmireView;
    private BarricadosView barricadosView;
    private BlackOrbView blackOrbView;
    private LaserView laserView;


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



        EpsilonView epsilonView = new EpsilonView(itsFrame);
        epsilonView.paint(g2D);

        necropickView.paint(g2D);
        omenoctView.paint(g2D);
        archmireView.paint(g2D);
        barricadosView.paint(g2D);
        blackOrbView.paint(g2D);
        laserView.paint(g2D);
        wyrmView.paint(g2D);

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

    public void setNecropickView(NecropickView necropickView) {
        this.necropickView = necropickView;
    }

    public void setOmenoctView(OmenoctView omenoctView) {
        this.omenoctView = omenoctView;
    }

    public void setArchmireView(ArchmireView archmireView) {
        this.archmireView = archmireView;
    }

    public void setBarricadosView(BarricadosView barricadosView) {
        this.barricadosView = barricadosView;
    }

    public void setBlackOrbView(BlackOrbView blackOrbView) {
        this.blackOrbView = blackOrbView;
    }

    public void setLaserView(LaserView laserView) {
        this.laserView = laserView;
    }
}
