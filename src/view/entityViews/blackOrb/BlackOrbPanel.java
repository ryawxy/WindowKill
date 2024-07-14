package view.entityViews.blackOrb;

import Controller.MouseListener;
import view.ShotGunView;
import view.entityViews.*;
import view.entityViews.barricados.BarricadosView;
import view.entityViews.wyrm.WyrmView;

import javax.swing.*;
import java.awt.*;

public class BlackOrbPanel extends JPanel {

    private JFrame itsFrame;
    view.entityViews.blackOrb.BlackOrbView blackOrbView;
  private OmenoctView omenoctView;
    private NecropickView necropickView;
    private ArchmireView archmireView;
    private LaserView laserView;
    private BarricadosView barricadosView;
    private WyrmView wyrmView;


    public BlackOrbPanel(int x, int y){

        this.setSize(x,y);
        this.setBackground(Color.BLACK);
        MouseListener mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());


        laserView.paint(g2D);
        blackOrbView.paint(g2D);

        EpsilonView epsilonView = new EpsilonView(itsFrame);
        epsilonView.paint(g2D);

        ShotGunView shotGunView = new ShotGunView(itsFrame);
        shotGunView.paint(g2D);

        VertexView vertexView = new VertexView(itsFrame);
        vertexView.paint(g2D);

        omenoctView.paint(g2D);
        necropickView.paint(g2D);
        archmireView.paint(g2D);
        barricadosView.paint(g2D);
        wyrmView.paint(g2D);


    }

    public void setItsFrame(JFrame itsFrame) {
        this.itsFrame = itsFrame;
    }

    public void setLaserView(LaserView laserView) {
        this.laserView = laserView;
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
}

