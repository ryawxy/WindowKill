package View;

import Controller.CurrentAbility;
import Controller.GameInfo;
import myproject.MyProject;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SkillTreeFrame extends JFrame {

    private final int SkillTree_WIDTH = 700;
    private final int SkillTree_HEIGHT = 700;
    private final Dimension SCREEN_SIZE = new Dimension(SkillTree_WIDTH,SkillTree_HEIGHT);
    ImageIcon image;
    JLabel label;
    private String[] option = {"yes","no"};
    JLabel attack;
    JButton ares;
    JLabel defence;
    JButton aceso;
    JLabel transformation;
    JButton proteus;
    JButton exit;
    private static int currentXP;
    private GameInfo gameInfo;

    public SkillTreeFrame(){

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

//attack ability
        Font fontStyle = new Font("Magneto",Font.BOLD,40);
        attack = new JLabel("Attack");
        attack.setFont(fontStyle);
        Color fontClr = Color.BLACK;
        attack.setForeground(fontClr);
        Color backClr = Color.WHITE;
        attack.setBackground(backClr);
        attack.setBounds(30,100,170,50);
        label.add(attack);

//ares button
        ares = new JButton("Ares");
         fontStyle = new Font("Algerian",Font.BOLD,30);
        ares.setFont(fontStyle);
         fontClr = Color.WHITE;
        ares.setForeground(fontClr);
        if(!GameInfo.isAresUnlocked()) backClr = Color.GRAY;
        else{
            backClr = Color.BLACK;
        }
        ares.setBackground(backClr);
        ares.setBorderPainted(false);
        //  start.setContentAreaFilled(false);
        ares.setFocusPainted(false);
        ares.setBounds(280,80,150,80);
        ares.setBorder(null);
      //  ares.setContentAreaFilled(false);
        ares.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!GameInfo.isAresUnlocked()) {
                    if (MyProject.getGameInfo().getXP() < 750) {
                        JOptionPane.showMessageDialog(SkillTreeFrame.this,
                                "You don't have enough XP to unlock this ability.");
                    } else {

                        int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                                "this ability costs 750 XP.DO you want to unlock it?", null, JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                        if (purchase == 0) {
                            GameInfo.setAresUnlocked(true);
                            MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP()-750);

                        }
                    }
                }else{
                    int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                            "Do you want to use this ability?", null, JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                    if (purchase == 0) {

                        GameInfo.setCurrentAbility(CurrentAbility.Ares);
                    }
                }
            }
        });
        ares.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if(GameInfo.isAresUnlocked()){

                    ares.setBackground(Color.BLACK);
                }
            }
        });

        label.add(ares);

        //defence ability
         fontStyle = new Font("Magneto",Font.BOLD,40);
        defence = new JLabel("Defence");
        defence.setFont(fontStyle);
         fontClr = Color.BLACK;
        defence.setForeground(fontClr);
         backClr = Color.WHITE;
        defence.setBackground(backClr);
        defence.setBounds(30,200,200,50);
        label.add(defence);

//aceso button
        aceso = new JButton("Aceso");
        fontStyle = new Font("Algerian",Font.BOLD,30);
        aceso.setFont(fontStyle);
        fontClr = Color.WHITE;
        aceso.setForeground(fontClr);
        if(!GameInfo.isAcesoUnlocked()) backClr = Color.GRAY;
        else{
            backClr = Color.BLACK;
        }
        aceso.setBackground(backClr);
        aceso.setBorderPainted(false);
        //  start.setContentAreaFilled(false);
        aceso.setFocusPainted(false);
        aceso.setBounds(280,180,150,80);
        aceso.setBorder(null);
        //  ares.setContentAreaFilled(false);
        aceso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!GameInfo.isAcesoUnlocked()) {
                    if(MyProject.getGameInfo().getXP() < 500) {
                        JOptionPane.showMessageDialog(SkillTreeFrame.this,
                                "You don't have enough XP to unlock this ability.");
                    } else {

                        int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                                "this ability costs 500 XP.DO you want to unlock it?", null, JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                        if (purchase == 0) {
                            GameInfo.setAcesoUnlocked(true);
                            MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP()-500);


                        }
                    }
                }else{
                    int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                            "Do you want to use this ability?", null, JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                    if (purchase == 0) {

                        GameInfo.setCurrentAbility(CurrentAbility.Aceso);
                    }
                }
            }
        });
        aceso.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if(GameInfo.isAcesoUnlocked()){

                    aceso.setBackground(Color.BLACK);
                }
            }
        });

        label.add(aceso);

        //transformation ability
        fontStyle = new Font("Magneto",Font.BOLD,40);
        transformation = new JLabel("Transform");
        transformation.setFont(fontStyle);
        fontClr = Color.BLACK;
        transformation.setForeground(fontClr);
        backClr = Color.WHITE;
        transformation.setBackground(backClr);
        transformation.setBounds(30,300,250,50);
        label.add(transformation);

//proteus button
        proteus = new JButton("Proteus");
        fontStyle = new Font("Algerian",Font.BOLD,30);
        proteus.setFont(fontStyle);
        fontClr = Color.WHITE;
        proteus.setForeground(fontClr);
        if(!GameInfo.isProteusUnlocked()) backClr = Color.GRAY;
        else{
            backClr = Color.BLACK;
        }
        proteus.setBackground(backClr);
        proteus.setBorderPainted(false);
        proteus.setFocusPainted(false);
        proteus.setBounds(280,280,150,80);
        proteus.setBorder(null);

        proteus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!GameInfo.isProteusUnlocked()) {
                    if (MyProject.getGameInfo().getXP() < 1000) {
                        JOptionPane.showMessageDialog(SkillTreeFrame.this,
                                "You don't have enough XP to unlock this ability.");
                    } else {

                        int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                                "this ability costs 1000 XP.DO you want to unlock it?", null, JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                        if (purchase == 0) {
                            GameInfo.setProteusUnlocked(true);

                            MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP()-1000);

                            System.out.println(currentXP);
                        }
                    }
                }else{
                    int purchase = JOptionPane.showOptionDialog(SkillTreeFrame.this,
                            "Do you want to use this ability?", null, JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                    if (purchase == 0) {

                        GameInfo.setCurrentAbility(CurrentAbility.Proteus);
                    }
                }
            }
        });
        proteus.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if(GameInfo.isProteusUnlocked()){

                    proteus.setBackground(Color.BLACK);
                }
            }
        });

        label.add(proteus);

        //exit button
        exit = new JButton("Exit");
        fontStyle = new Font("Algerian",Font.BOLD,30);
        exit.setFont(fontStyle);
        fontClr = Color.WHITE;
        exit.setForeground(fontClr);
        backClr = new Color(100,100,200);
        exit.setBackground(backClr);
        exit.setBorderPainted(false);
        //  start.setContentAreaFilled(false);
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
