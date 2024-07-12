package view.entityViews.blackOrb;


import Controller.Game;
import Model.FrameType;
import Model.entity.blackOrb.BlackOrb;
import view.entityViews.ArchmireView;
import view.entityViews.NecropickView;
import view.entityViews.OmenoctView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BlackOrbFrame extends JFrame implements FrameType {

    Model.entity.blackOrb.BlackOrb blackOrb;
    Rectangle bounds;

    public BlackOrbFrame(int x,int y) throws IOException {
        this.setSize(150,150);
        this.setBackground(Color.BLACK);
        this.setLocation(x,y);
        this.setUndecorated(true);
        this.setTitle("Black-Orb frame");
        view.entityViews.blackOrb.BlackOrbPanel blackOrbPanel = new view.entityViews.blackOrb.BlackOrbPanel(150,150);
        this.setContentPane(blackOrbPanel);


        blackOrbPanel.setItsFrame(this);
        this.setVisible(true);
        blackOrb = new Model.entity.blackOrb.BlackOrb(30,20);
        blackOrb.setLocalFrame(this);
        blackOrb.setPreviousLocalFrame(this);
        blackOrbPanel.setBlackOrbView(new view.entityViews.blackOrb.BlackOrbView(blackOrb,this));
        blackOrbPanel.setOmenoctView(new OmenoctView(this));
        blackOrbPanel.setNecropickView(new NecropickView(this));
        blackOrbPanel.setArchmireView(new ArchmireView(this));
        Game.getBlackOrbs().add(blackOrb);
        Game.getEnemies().add(blackOrb);
        bounds = new Rectangle(getX(),getY(),getWidth(),getHeight());

    }

    @Override
    public boolean isometric() {
        return true;
    }

    @Override
    public boolean solid() {
        return false;
    }

    @Override
    public Rectangle getBound() {
        return null;
    }

    public BlackOrb getBlackOrb() {
        return blackOrb;
    }
}
