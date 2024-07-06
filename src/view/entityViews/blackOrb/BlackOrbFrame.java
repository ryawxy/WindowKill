package view.entityViews.blackOrb;

import model.entity.blackOrb.BlackOrb;
import model.FrameType;

import javax.swing.*;
import java.awt.*;

public class BlackOrbFrame extends JFrame implements FrameType {

    BlackOrb blackOrb;
    Rectangle bounds;

    public BlackOrbFrame(int x,int y){
        this.setSize(150,150);
        this.setBackground(Color.BLACK);
        this.setLocation(x,y);
        this.setUndecorated(true);
        this.setTitle("Black-Orb frame");
        BlackOrbPanel blackOrbPanel = new BlackOrbPanel(150,150);
        this.setContentPane(blackOrbPanel);

        blackOrbPanel.setBound(new Rectangle(x,y,150,150));
        blackOrbPanel.setItsFrame(this);
        this.setVisible(true);
        blackOrb = new BlackOrb(30,20);
        blackOrb.setLocalFrame(this);
        blackOrbPanel.setBlackOrbView(new BlackOrbView(blackOrb));
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
