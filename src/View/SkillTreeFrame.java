package View;

import Controller.Game;
import Controller.SkillTreeController;
import Model.Ability;
import Model.GameInfo;
import myproject.MyProject;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class SkillTreeFrame extends JFrame {

    ImageIcon image;
    JLabel label;
    private String[] option = {"yes","no"};
   private JLabel attack;
   private JButton ares;
   private JButton astrape;
   private JButton cerberus;
    private JLabel defence;
    private JButton aceso;
  private JButton melampus;
    private JButton chiron;
   private JButton athena;
   private JLabel transformation;
    private JButton proteus;
    private JButton empusa;
   private JButton dolus;
   private final JButton exit;
    private final ArrayList<JButton> attackButtons = new ArrayList<>();
    private final ArrayList<JButton> defenceButtons = new ArrayList<>();
    private final ArrayList<JButton> transformationButtons = new ArrayList<>();
    private SkillTreeController skillTreeController = new SkillTreeController();

    private static int currentXP;

    public SkillTreeFrame(){

        int skillTree_WIDTH = 700;
        int skillTree_HEIGHT = 700;
        Dimension SCREEN_SIZE = new Dimension(skillTree_WIDTH, skillTree_HEIGHT);
        this.setSize(SCREEN_SIZE);
        this.setTitle("Window Kill");
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setVisible(true);

        image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/Images/skillTree.jpg")));
        label = new JLabel(image);
        label.setSize(SCREEN_SIZE);
        this.add(label);
        label.setOpaque(false);


        ares = new JButton("Ares");
        astrape = new JButton("Astrape");
        cerberus = new JButton("Cerberus");

        attackButtons.add(ares);
        attackButtons.add(astrape);
        attackButtons.add(cerberus);


//attack ability
        Font fontStyle = new Font("Magneto",Font.BOLD,30);
        attack = new JLabel("Attack");
        attack.setFont(fontStyle);
        Color fontClr = Color.BLACK;
        attack.setForeground(fontClr);
        Color backClr = Color.WHITE;
        attack.setBackground(backClr);
        attack.setBounds(25,100,170,50);
        label.add(attack);

        //attack buttons
        for(int i=0; i<attackButtons.size(); i++){
            JButton button = attackButtons.get(i);
            fontStyle = new Font("Algerian",Font.BOLD,20);
            button.setFont(fontStyle);
            fontClr = Color.WHITE;

            button.setForeground(fontClr);
            if(!GameInfo.getAttackLocks().get(button.getText())) backClr = Color.GRAY;
            else{
                backClr = Color.BLACK;
            }
            button.setBackground(backClr);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setBounds(160+i*130,80,120,80);
            button.setBorder(null);
            label.add(button);

            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean canUnlock = true;
                    if (!GameInfo.getAttackLocks().get(button.getText())) {

                        for (int j = finalI - 1; j >= 0; j--) {

                            if (!GameInfo.getAttackLocks().get(GameInfo.getAttackLocks().keySet().toArray()[j])) {

                                canUnlock = false;
                                break;
                            }
                        }

                        if (canUnlock) {

                            if (MyProject.getGameInfo().getXP() < skillTreeController.getItemsXP().get(button.getText())) {
                                JOptionPane.showMessageDialog(SkillTreeFrame.this,
                                        "You don't have enough XP to unlock this ability.");
                            } else {

                                int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                                        "this ability costs" + skillTreeController.getItemsXP().get(button.getText()) +
                                                " XP.DO you want to unlock it?", null, JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                                if (purchase == 0) {
                                    GameInfo.getAttackLocks().replace(button.getText(), true);


                                    MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() - skillTreeController.getItemsXP().get(button.getText()));
                                }
                            }


                        } else {
                            JOptionPane.showMessageDialog(SkillTreeFrame.this,
                                    "You haven't unlocked past abilities yet.");
                        }
                    } else {
                        int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                                "Do you want to use this ability?", null, JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                        if (purchase == 0) {

                            GameInfo.getCurrentAbility().put(Ability.valueOf(button.getText()), true);
                        }
                    }
                }


            });
            button.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {

                    if(GameInfo.getAttackLocks().get(button.getText())){


                        button.setBackground(Color.BLACK);
                    }
                }
            });

            label.add(button);

        }


        //defence ability
         fontStyle = new Font("Magneto",Font.BOLD,30);
        defence = new JLabel("Defence");
        defence.setFont(fontStyle);
         fontClr = Color.BLACK;
        defence.setForeground(fontClr);
         backClr = Color.WHITE;
        defence.setBackground(backClr);
        defence.setBounds(25,200,200,50);
        label.add(defence);

        aceso = new JButton("Aceso");
        melampus = new JButton("Melampus");
        chiron = new JButton("Chiron");
        athena = new JButton("Athena");

        defenceButtons.add(aceso);
        defenceButtons.add(melampus);
        defenceButtons.add(chiron);
        defenceButtons.add(athena);
//defence buttons
        for(int i=0; i<defenceButtons.size(); i++) {
            JButton button = defenceButtons.get(i);
            fontStyle = new Font("Algerian", Font.BOLD, 20);
            button.setFont(fontStyle);
            fontClr = Color.WHITE;
            button.setForeground(fontClr);

            if (!GameInfo.getDefenceLocks().get(button.getText())) backClr = Color.GRAY;
            else {
                backClr = Color.BLACK;
            }
            button.setBackground(backClr);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setBounds(160 + i * 130, 180, 120, 80);
            button.setBorder(null);
            label.add(button);

            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    boolean canUnlock = true;
                    if (!GameInfo.getDefenceLocks().get(button.getText())) {
                        for (int j = finalI - 1; j >= 0; j--) {

                            if (!GameInfo.getDefenceLocks().get(GameInfo.getDefenceLocks().keySet().toArray()[j])) {

                                canUnlock = false;
                                break;
                            }

                            if (button.getText().equals(String.valueOf(Ability.Athena))) {
                                if (!GameInfo.getAttackLocks().get(String.valueOf(Ability.Astrape))) canUnlock = false;
                                break;
                            }


                        }
                        if (canUnlock) {
                            if (MyProject.getGameInfo().getXP() < skillTreeController.getItemsXP().get(button.getText())) {
                                JOptionPane.showMessageDialog(SkillTreeFrame.this,
                                        "You don't have enough XP to unlock this ability.");
                            } else {

                                int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                                        "this ability costs" + skillTreeController.getItemsXP().get(button.getText()) +
                                                " XP.DO you want to unlock it?", null, JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                                if (purchase == 0) {

                                    GameInfo.getDefenceLocks().replace(button.getText(),false,true);

                                    MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() - skillTreeController.getItemsXP().get(button.getText()));
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(SkillTreeFrame.this,
                                    "You haven't unlocked past abilities yet.");
                        }
                        } else {
                            int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                                    "Do you want to use this ability?", null, JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                            if (purchase == 0) {

                                GameInfo.getCurrentAbility().put(Ability.valueOf(button.getText()), true);
                            }

                    }
                }
            });

            button.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {

                    if(GameInfo.getDefenceLocks().get(button.getText())){

                        button.setBackground(Color.BLACK);
                    }
                }
            });

            label.add(button);
        }

        //transformation ability
        fontStyle = new Font("Magneto",Font.BOLD,30);
        transformation = new JLabel("Transform");
        transformation.setFont(fontStyle);
        fontClr = Color.BLACK;
        transformation.setForeground(fontClr);
        backClr = Color.WHITE;
        transformation.setBackground(backClr);
        transformation.setBounds(30,300,250,50);
        label.add(transformation);


        proteus = new JButton("Proteus");
        empusa = new JButton("Empusa");
        dolus = new JButton("Dolus");

        transformationButtons.add(proteus);
        transformationButtons.add(empusa);
        transformationButtons.add(dolus);

        //transformation buttons
        for(int i=0; i<transformationButtons.size(); i++){
            JButton button = transformationButtons.get(i);
            fontStyle = new Font("Algerian",Font.BOLD,20);
            button.setFont(fontStyle);
            fontClr = Color.WHITE;
            button.setForeground(fontClr);
            if(!GameInfo.getTrnsformLocks().get(button.getText())) backClr = Color.GRAY;
            else{
                backClr = Color.BLACK;
            }
            button.setBackground(backClr);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setBounds(290+i*130,280,120,80);
            button.setBorder(null);

            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    boolean canUnlock = true;
                    if(!GameInfo.getTrnsformLocks().get(button.getText())) {

                        for (int j = finalI - 1; j >= 0; j--) {

                            if (!GameInfo.getTrnsformLocks().get(GameInfo.getTrnsformLocks().keySet().toArray()[j])) {

                                canUnlock = false;
                                break;
                            }
                        }
                        if(canUnlock) {
                            if (MyProject.getGameInfo().getXP() < skillTreeController.getItemsXP().get(button.getText())) {
                                JOptionPane.showMessageDialog(SkillTreeFrame.this,
                                        "You don't have enough XP to unlock this ability.");
                            } else {

                                int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                                        "this ability costs" + skillTreeController.getItemsXP().get(button.getText()) +
                                                " XP.DO you want to unlock it?", null, JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                                if (purchase == 0) {
                                    GameInfo.getTrnsformLocks().put(button.getText(), true);


                                    MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() - skillTreeController.getItemsXP().get(button.getText()));
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(SkillTreeFrame.this,
                                    "You haven't unlocked past abilities yet.");
                        }
                    }else{
                        int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                                "Do you want to use this ability?", null, JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                        if (purchase == 0) {

                            GameInfo.getCurrentAbility().put(Ability.valueOf(button.getText()),true);
                        }
                    }
                }
            });
            button.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {

                    if(GameInfo.getTrnsformLocks().get(button.getText())){

                        button.setBackground(Color.BLACK);
                    }
                }
            });

            label.add(button);

        }
        //exit button
        exit = new JButton("Exit");
        fontStyle = new Font("Algerian",Font.BOLD,30);
        exit.setFont(fontStyle);
        fontClr = Color.WHITE;
        exit.setForeground(fontClr);
        backClr = new Color(100,100,200);
        exit.setBackground(backClr);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setBounds(600,600,80,80);
        exit.setBorder(null);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StarterMenu();
                dispose();
            }
        });
        label.add(exit);
    }

    public static int getCurrentXP() {
        return currentXP;
    }

    public static void setCurrentXP(int currentXP) {
        SkillTreeFrame.currentXP = currentXP;
    }
}
