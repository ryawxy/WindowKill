package Controller;

import Model.Game;
import Model.ShotGun;
import View.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GameLoop {

    private Game game;
    private Timer timer;
    private FrameSize frameSize;
    public GameLoop(Game game) throws IOException {
        this.game = game;
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame.getEpsilon().move();
                frameSize = new FrameSize(game.getGameFrame());
                // frameSize.shrink();

                for(ShotGun shotGun : ShotGun.getShots()){
                    shotGun.move();
                }






                game.getGameFrame().repaint();
            }
        });
        timer.start();

    }


}
