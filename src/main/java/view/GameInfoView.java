package view;

import Controller.Game;
import Model.Drawable;
import Model.GameInfo;
import Model.GameLoop;
import myproject.MyProject;

import java.awt.*;

public class GameInfoView implements Drawable {
    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.drawString("✦"+ MyProject.getGameInfo().getXP(),5,20);
        if(Game.getEpsilon().getHP()>=0) {
            g.drawString("♥" + Game.getEpsilon().getHP(), 100, 20);
        }else{
            g.drawString("♥" + 0, 100, 20);
        }
        g.drawString(GameLoop.getMinutes()+":"+GameLoop.getSeconds(),195,20);
        g.drawString(MyProject.getGameInfo().getCurrentWave()+"/3",290,20);
    }
    }

