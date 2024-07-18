package view.entityViews.smiley;

import Controller.Game;
import Controller.KeyListener;
import Controller.MouseListener;
import Model.FrameType;
import Model.entity.smiley.SmileyPointFinger;
import Model.enums.PunchType;

import javax.swing.*;
import java.awt.*;

public class SmileyPointerFrame extends JFrame implements FrameType {

  private SmileyPointFinger smileyPointFinger;
  private Rectangle bound;



    public SmileyPointerFrame(int x, int y, PunchType punchType){

        this.setSize(150,200);
        this.setLocation(x,y);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));
        this.setTitle("Smiley frame");
        SmileyPointerPanel smileyPointerPanel = new SmileyPointerPanel(150,200);
        this.setContentPane(smileyPointerPanel);
        smileyPointerPanel.setBound(new Rectangle(x,y,150,200));
        smileyPointerPanel.setItsFrame(this);
        smileyPointerPanel.setSmileyPointerView(new SmileyPointerView(this));
        smileyPointerPanel.setSmileyView(new SmileyView(this));
        this.setVisible(true);
        smileyPointFinger = new SmileyPointFinger(30,20);
        smileyPointFinger.setSmileyHandSide(punchType);
        smileyPointFinger.setLocalFrame(this);
        bound = new Rectangle(getX(),getY(),getWidth(),getHeight());
        Game.getFrames().add(this);
        Game.getEnemies().add(smileyPointFinger);
        Game.getSmileyPointFingers().add(smileyPointFinger);
        Game.getSmileyPointerFrames().add(this);
        new KeyListener(smileyPointerPanel);
        new MouseListener(smileyPointerPanel);
    }

    @Override
    public boolean isometric() {
        return false;
    }

    @Override
    public boolean solid() {
        return true;
    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }


}
