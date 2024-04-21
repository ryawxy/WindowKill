package Controller;

import Model.Epsilon;
import Model.ShotGun;
import View.GameInfo;
import View.GamePanel;
import View.Settings.SettingsFrame;
import View.ShopFrame;

import javax.swing.*;
import java.awt.event.*;

public class KeyListener {

    InputMap inputMap;
    ActionMap actionMap;
    Epsilon epsilon;
    ShotGun shotGun;
    GamePanel gameFrame;
    SettingsFrame settingsFrame;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    private final int absVelocity = Constants.epsilonAbsVelocity();
    private int mouseXPose;
    private int mouseYPose;
    private static boolean pauseGame;
    private static boolean activate;
    //activates chosen ability;
    private static boolean canUseAbility;
    // can the player use the ability?
    private static boolean abilityKeyPressed;
    private static int keyPressedNumber;


    public KeyListener(GamePanel gameFrame){
        this.gameFrame = gameFrame;
        epsilon = GamePanel.getEpsilon();
        shotGun = GamePanel.getShotGun();
        createKeyBindings();
        createKeyActions();

    }

    public void createKeyBindings(){


        inputMap = gameFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = gameFrame.getRootPane().getActionMap();

        //key press
        inputMap.put(KeyStroke.getKeyStroke(SettingsFrame.getKeyBinding("up"),0),"upPress");
        inputMap.put(KeyStroke.getKeyStroke(SettingsFrame.getKeyBinding("down"),0),"downPress");
        inputMap.put(KeyStroke.getKeyStroke(SettingsFrame.getKeyBinding("left"),0),"leftPress");
        inputMap.put(KeyStroke.getKeyStroke(SettingsFrame.getKeyBinding("right"),0),"rightPress");
        inputMap.put(KeyStroke.getKeyStroke(SettingsFrame.getKeyBinding("shop"),0),"shopPress");
        inputMap.put(KeyStroke.getKeyStroke(SettingsFrame.getKeyBinding("ability"),0),"abilityPress");



        // key release
        inputMap.put(KeyStroke.getKeyStroke(SettingsFrame.getKeyBinding("up"),0,true),"upRelease");
        inputMap.put(KeyStroke.getKeyStroke(SettingsFrame.getKeyBinding("down"),0,true),"downRelease");
        inputMap.put(KeyStroke.getKeyStroke(SettingsFrame.getKeyBinding("left"),0,true),"leftRelease");
        inputMap.put(KeyStroke.getKeyStroke(SettingsFrame.getKeyBinding("right"),0,true),"rightRelease");



    }
    public void createKeyActions(){

        // up
        actionMap.put("upPress", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (epsilon.getY() - epsilon.getRadius() > 0) {
                    if (!downPressed) {
                        epsilon.setyVelocity(-1 * absVelocity);
                        shotGun.setyVelocity(-1 * absVelocity);

                    }else {
                        epsilon.setyVelocity(0);
                        shotGun.setyVelocity(0);
                    }

                } else {
                    epsilon.setyVelocity(0);
                    shotGun.setyVelocity(0);
                }
                upPressed = true;
            }


        });

        actionMap.put("upRelease", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilon.setyVelocity(0);
                shotGun.setyVelocity(0);
                upPressed = false;
            }
        });

        // down
        actionMap.put("downPress", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(epsilon.getY() + epsilon.getRadius()< GamePanel.getFRAME_HEIGHT()) {
                    if (!upPressed) {
                        epsilon.setyVelocity(absVelocity);
                        shotGun.setyVelocity(absVelocity);
                    }else {
                        epsilon.setyVelocity(0);
                        shotGun.setyVelocity(0);
                    }
                }else {
                    epsilon.setyVelocity(0);
                    shotGun.setyVelocity(0);
                }
                downPressed = true;
            }

        });

        actionMap.put("downRelease", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilon.setyVelocity(0);
                shotGun.setyVelocity(0);
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
                        shotGun.setxVelocity(-1 * absVelocity);
                    }else {
                        epsilon.setxVelocity(0);
                        shotGun.setxVelocity(0);
                    }
                }else{
                    epsilon.setxVelocity(0);
                    shotGun.setxVelocity(0);
                }
                leftPressed = true;
            }
        });

        actionMap.put("leftRelease", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilon.setxVelocity(0);
                shotGun.setxVelocity(0);
                leftPressed = false;
            }
        });

        // right
        actionMap.put("rightPress", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(epsilon.getX() + epsilon.getRadius()< GamePanel.getFRAME_WIDTH()) {
                    if(!leftPressed) {
                        epsilon.setxVelocity(absVelocity);
                        shotGun.setxVelocity(absVelocity);
                    }else {
                        epsilon.setxVelocity(0);
                        shotGun.setxVelocity(0);
                    }
                }else {
                    epsilon.setxVelocity(0);
                    shotGun.setxVelocity(0);
                }
                rightPressed = true;

            }
        });

        actionMap.put("rightRelease", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilon.setxVelocity(0);
                shotGun.setxVelocity(0);
                rightPressed = false;
            }
        });
        actionMap.put("shopPress", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShopFrame();
                pauseGame =true;
            }
        });
        actionMap.put("abilityPress", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(GameInfo.getCurrentAbility()!= null){
                    abilityKeyPressed = true;
                    keyPressedNumber++;
                }
            }
        });


    }
    public static boolean getPauseGame(){
        return pauseGame;
    }

    public static void setPauseGame(boolean pauseGame) {
        KeyListener.pauseGame = pauseGame;
    }

    public static boolean isActivate() {
        return activate;
    }

    public static void setActivate(boolean activate) {
        KeyListener.activate = activate;
    }

    public static boolean isCanUseAbility() {
        return canUseAbility;
    }

    public static void setCanUseAbility(boolean canUseAbility) {
        KeyListener.canUseAbility = canUseAbility;
    }

    public static boolean isAbilityKeyPressed() {
        return abilityKeyPressed;
    }

    public static void setAbilityKeyPressed(boolean abilityKeyPressed) {
        KeyListener.abilityKeyPressed = abilityKeyPressed;
    }

    public static int getKeyPressedNumber() {
        return keyPressedNumber;
    }

    public static void setKeyPressedNumber(int keyPressedNumber) {
        KeyListener.keyPressedNumber = keyPressedNumber;
    }
}
