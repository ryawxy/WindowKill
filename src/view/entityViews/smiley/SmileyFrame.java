package view.entityViews.smiley;

import Controller.Game;
import Controller.KeyListener;
import Controller.MouseListener;
import Model.FrameType;
import Model.entity.smiley.Smiley;
import javax.swing.*;
import java.awt.*;

public class SmileyFrame extends JFrame implements FrameType {

    private Smiley smiley;
    private Rectangle bound;

    public SmileyFrame(int x, int y){

        this.setSize(200,200);
        this.setBackground(Color.BLACK);
        this.setLocation(x,y);
        this.setUndecorated(true);
        this.setTitle("Smiley frame");
        SmileyPanel smileyPanel = new SmileyPanel(200,200);
        this.setContentPane(smileyPanel);
        smileyPanel.setBound(new Rectangle(x,y,200,200));
        smileyPanel.setItsFrame(this);
        this.setVisible(true);
        smiley = new Smiley(45,45);
        smiley.setLocalFrame(this);
        bound = new Rectangle(getX(),getY(),getWidth(),getHeight());
        Game.getFrames().add(this);
        Game.getEnemies().add(smiley);
        Game.getSmilies().add(smiley);
        Game.getSmileyFrames().add(this);
        new KeyListener(smileyPanel);
        new MouseListener(smileyPanel);

    }

    public Smiley getSmiley() {
        return smiley;
    }

    @Override
    public boolean isometric() {
        return false;
    }

    @Override
    public boolean solid() {
        return false;
    }

    public Rectangle getBound() {
        return bound;
    }
}
