package Controller;

import Model.Game;
import View.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GameLoop {

    private Game game;
    private Timer timer;
    private FrameSize frameSize;
    private KeyListener keyListener;
    public GameLoop(Game game) throws IOException {
        this.game = game;
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame.getEpsilon().move();
                frameSize = new FrameSize(game.getGameFrame());
                // frameSize.shrink();

//                GameFrame.getShotGun().setX(GameFrame.getEpsilon().getX());
//                GameFrame.getShotGun().setY(GameFrame.getEpsilon().getY());

                GameFrame.getShotGun().move();






                game.getGameFrame().repaint();
            }
        });
        timer.start();

    }


}
