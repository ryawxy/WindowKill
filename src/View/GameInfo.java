package View;

import Controller.CurrentAbility;

import javax.swing.*;

public class GameInfo extends JLabel {
    private int time;
    private static int XP = 800;
    private int HP;
    private int waveNumber;
    private static CurrentAbility currentAbility;

    private JLabel epsilonsHP;

    public  GameInfo(JPanel parent){
       epsilonsHP = new JLabel("HP:"+GamePanel.getEpsilon().getHP());
       epsilonsHP.setBounds(parent.getX()+10,100,200,100);
       parent.add(epsilonsHP);

    }

    public static void setXP(int XP) {
        GameInfo.XP = XP;
    }

    public static int getXP() {
        return XP;
    }

    public static CurrentAbility getCurrentAbility() {
        return currentAbility;
    }

    public static void setCurrentAbility(CurrentAbility currentAbility) {
        GameInfo.currentAbility = currentAbility;
    }
}
