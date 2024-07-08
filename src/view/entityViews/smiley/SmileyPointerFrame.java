package view.entityViews.smiley;

import Controller.Game;
import Controller.KeyListener;
import Controller.MouseListener;
import Model.FrameType;
import Model.entity.smiley.SmileyPointFinger;
import Model.enums.SmileyHandSide;

import javax.swing.*;
import java.awt.*;

public class SmileyPointerFrame extends JFrame implements FrameType {

  private SmileyPointFinger smileyPointFinger;
  private Rectangle bound;



    public SmileyPointerFrame(int x, int y, SmileyHandSide smileyHandSide){

        this.setSize(150,200);
        this.setBackground(Color.BLACK);
        this.setLocation(x,y);
        this.setUndecorated(true);
        this.setTitle("Smiley frame");
        SmileyPointerPanel smileyPointerPanel = new SmileyPointerPanel(150,200);
        this.setContentPane(smileyPointerPanel);
        smileyPointerPanel.setBound(new Rectangle(x,y,150,200));
        smileyPointerPanel.setItsFrame(this);
        this.setVisible(true);
        smileyPointFinger = new SmileyPointFinger(30,20);
        smileyPointFinger.setSmileyHandSide(smileyHandSide);
        smileyPointFinger.setLocalFrame(this);
        bound = new Rectangle(getX(),getY(),getWidth(),getHeight());
        Game.getFrames().add(this);
        Game.getEnemies().add(smileyPointFinger);
        Game.getSmileyPointFingers().add(smileyPointFinger);
        Game.getSmileyPointerFrames().add(this);
        new KeyListener(smileyPointerPanel);
        new MouseListener(smileyPointerPanel);
    }

    public SmileyPointFinger getSmileyPointFinger() {
        return smileyPointFinger;
    }

    public void setSmileyPointFinger(SmileyPointFinger smileyPointFinger) {
        this.smileyPointFinger = smileyPointFinger;
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
