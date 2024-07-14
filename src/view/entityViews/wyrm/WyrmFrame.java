package view.entityViews.wyrm;

import Controller.Game;
import Controller.KeyListener;
import Model.entity.Wyrm;
import view.entityViews.ArchmireView;
import view.entityViews.NecropickView;
import view.entityViews.OmenoctView;
import view.entityViews.barricados.BarricadosView;
import view.entityViews.blackOrb.BlackOrbView;
import view.entityViews.blackOrb.LaserView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class WyrmFrame extends JFrame {
    Wyrm wyrm;
    public WyrmFrame(int x, int y){

        this.setSize(150,150);
        this.setBackground(Color.BLACK);
        this.setLocation(x,y);
        this.setUndecorated(true);
        this.setTitle("Wyrm frame");
        WyrmPanel wyrmPanel = new WyrmPanel(150,150);
        this.setContentPane(wyrmPanel);
        wyrmPanel.setBound(new Rectangle(x,y,150,150));
        wyrmPanel.setItsFrame(this);
        this.setVisible(true);
        wyrm = new Wyrm(0,0);
        wyrm.setLocalFrame(this);
        wyrm.setItsPanel(wyrmPanel);
        wyrmPanel.setWyrmView(new WyrmView(this));
        wyrmPanel.setNecropickView(new NecropickView(this));
        wyrmPanel.setBarricadosView(new BarricadosView(this));
        wyrmPanel.setOmenoctView(new OmenoctView(this));
        wyrmPanel.setArchmireView(new ArchmireView(this));
        wyrmPanel.setLaserView(new LaserView(this));
        try {
            wyrmPanel.setBlackOrbView(new BlackOrbView(this));
        }catch (IOException ignored){

        }
        new KeyListener((JPanel)getContentPane());

        Game.getFrames().add(this);
        Game.getEnemies().add(wyrm);
        Game.getWyrms().add(wyrm);
        Game.getWyrmFrames().add(this);

    }

    public Wyrm getWyrm() {
        return wyrm;
    }
}
