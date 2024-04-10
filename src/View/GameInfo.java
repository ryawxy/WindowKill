package View;

import javax.swing.*;

public class GameInfo extends JLabel {
    private int time;
    private int XP;
    private int HP;
    private int waveNumber;

    private JLabel epsilonsHP;

    public  GameInfo(JPanel parent){
       epsilonsHP = new JLabel("HP:"+GamePanel.getEpsilon().getHP());
       epsilonsHP.setBounds(parent.getX()+10,100,200,100);
       parent.add(epsilonsHP);

    }


}
