package View;

import Model.CurrentAbility;
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

    private final int SkillTree_WIDTH = 700;
    private final int SkillTree_HEIGHT = 700;
    private final Dimension SCREEN_SIZE = new Dimension(SkillTree_WIDTH,SkillTree_HEIGHT);
    ImageIcon image;
    JLabel label;
    private String[] option = {"yes","no"};
    JLabel attack;
    JButton ares;
    JButton astrape;
    JButton cerberus;
    JLabel defence;
    JButton aceso;
    JButton melampus;
    JButton chiron;
    JButton athena;
    JLabel transformation;
    JButton proteus;
    JButton empusa;
    JButton dolus;
    JButton exit;
    private final ArrayList<JButton> attackButtons = new ArrayList<>();
    private final ArrayList<JButton> defenceButtons = new ArrayList<>();
    private final ArrayList<JButton> transformationButtons = new ArrayList<>();

    private static int currentXP;

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
            if(!GameInfo.isAresUnlocked()) backClr = Color.GRAY;
            else{
                backClr = Color.BLACK;
            }
            button.setBackground(backClr);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setBounds(160+i*130,80,120,80);
            button.setBorder(null);
            label.add(button);

        }

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
        for(int i=0; i<defenceButtons.size(); i++){
            JButton button = defenceButtons.get(i);
            fontStyle = new Font("Algerian",Font.BOLD,20);
            button.setFont(fontStyle);
            fontClr = Color.WHITE;
            button.setForeground(fontClr);
            if(!GameInfo.isAresUnlocked()) backClr = Color.GRAY;
            else{
                backClr = Color.BLACK;
            }
            button.setBackground(backClr);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setBounds(160+i*130,180,120,80);
            button.setBorder(null);
            label.add(button);

        }
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
            if(!GameInfo.isAresUnlocked()) backClr = Color.GRAY;
            else{
                backClr = Color.BLACK;
            }
            button.setBackground(backClr);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setBounds(290+i*130,280,120,80);
            button.setBorder(null);
            label.add(button);

        }


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
