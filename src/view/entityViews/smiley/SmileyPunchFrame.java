package view.entityViews.smiley;

import Controller.Game;
import Controller.KeyListener;
import Controller.MouseListener;
import Model.FrameType;
import Model.entity.smiley.SmileyPointFinger;
import Model.entity.smiley.SmileyPunch;
import Model.enums.SmileyHandSide;

import javax.swing.*;
import java.awt.*;

public class SmileyPunchFrame extends JFrame implements FrameType {
    Rectangle bound;
    SmileyPunch smileyPunch;

    public SmileyPunchFrame(int x, int y, SmileyHandSide smileyHandSide){

        this.setSize(200,200);
        this.setBackground(Color.BLACK);
        this.setLocation(x,y);
        this.setUndecorated(true);
        this.setTitle("Smiley frame");
        SmileyPunchPanel smileyPunchPanel = new SmileyPunchPanel(200,200);
        this.setContentPane(smileyPunchPanel);
        smileyPunchPanel.setBound(new Rectangle(x,y,200,200));
        smileyPunchPanel.setItsFrame(this);
        this.setVisible(true);
        smileyPunch = new SmileyPunch(30,20);
        smileyPunch.setSmileyHandSide(smileyHandSide);
        smileyPunch.setLocalFrame(this);
        bound = new Rectangle(getX(),getY(),getWidth(),getHeight());
        Game.getFrames().add(this);
        Game.getEnemies().add(smileyPunch);
        Game.getSmileyPunches().add(smileyPunch);
        Game.getSmileyPunchFrames().add(this);
        new KeyListener(smileyPunchPanel);
        new MouseListener(smileyPunchPanel);
    }

    @Override
    public boolean isometric() {
        return false;
    }

    @Override
    public boolean solid() {
        return false;
    }

    @Override
    public Rectangle getBound() {
        return bound;
    }
}
