package Controller;

import Model.Epsilon;
import View.GameFrame;

import javax.swing.*;
import java.awt.event.*;

public class KeyListener {

    InputMap inputMap;
    ActionMap actionMap;
    Epsilon epsilon;
    GameFrame gameFrame;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    private final int absVelocity = 2;
    private int mouseXPose;
    private int mouseYPose;


    public KeyListener(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        epsilon = GameFrame.getEpsilon();
        createKeyBindings();
        createKeyActions();

    }
    public KeyListener(){}

    public void createKeyBindings(){


        inputMap = gameFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = gameFrame.getRootPane().getActionMap();

        //key press
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),"upPress");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),"downPress");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0),"leftPress");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0),"rightPress");



        // key release
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,true),"upRelease");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,true),"downRelease");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,true),"leftRelease");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,true),"rightRelease");



    }
    public void createKeyActions(){

        // up
        actionMap.put("upPress", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (epsilon.getY() - epsilon.getRadius() > 0) {
                    if (!downPressed) {
                        epsilon.setyVelocity(-1 * absVelocity);
                    }else {
                        epsilon.setyVelocity(0);
                    }

                } else {
                    epsilon.setyVelocity(0);
                }
                upPressed = true;
            }


        });

        actionMap.put("upRelease", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilon.setyVelocity(0);
                upPressed = false;
            }
        });

        // down
        actionMap.put("downPress", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(epsilon.getY() + epsilon.getRadius()< gameFrame.getFRAME_HEIGHT()-5) {
                    if (!upPressed) {
                        epsilon.setyVelocity(absVelocity);
                    }else {
                        epsilon.setyVelocity(0);
                    }
                }else {
                    epsilon.setyVelocity(0);
                }
                downPressed = true;
            }

        });

        actionMap.put("downRelease", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilon.setyVelocity(0);
                downPressed = false;
            }
        });

        // left
        actionMap.put("leftPress", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(epsilon.getX() - epsilon.getRadius()>0) {
                    if(!rightPressed) {
                        epsilon.setxVelocity(-1 * absVelocity);
                    }else {
                        epsilon.setxVelocity(0);
                    }
                }else{
                    epsilon.setxVelocity(0);
                }
                leftPressed = true;
            }
        });

        actionMap.put("leftRelease", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilon.setxVelocity(0);
                leftPressed = false;
            }
        });

        // right
        actionMap.put("rightPress", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(epsilon.getX() + epsilon.getRadius()<gameFrame.getFRAME_WIDTH()-5) {
                    if(!leftPressed) {
                        epsilon.setxVelocity(absVelocity);
                    }else {
                        epsilon.setxVelocity(0);
                    }
                }else {
                    epsilon.setxVelocity(0);
                }
                rightPressed = true;
            }
        });

        actionMap.put("rightRelease", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilon.setxVelocity(0);
                rightPressed = false;
            }
        });


    }


}
