package view;

import Controller.KeyCodeCapture;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Objects;

public class SettingsFrame extends JFrame  {

    private final JSlider sound;
    private final JSlider keySensitivity;
    private final JSlider level;
    private static  HashMap<String,Integer> keyBindings = new HashMap<>();
    private static int chosenLevel;
    private static int chosenSound;
    private static int chosenSensitivity;

    JTextField keyCodeField = new JTextField(10);


    public SettingsFrame() {

        keyCodeField.setEditable(false);

        int MENU_WIDTH = 700;
        int MENU_HEIGHT = 700;
        Dimension SCREEN_SIZE = new Dimension(MENU_WIDTH, MENU_HEIGHT);
        this.setSize(SCREEN_SIZE);
        this.setTitle("Window Kill");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setUndecorated(true);

        ImageIcon image = new ImageIcon("src/images/settings.JPG");
        JLabel label = new JLabel(image);
        label.setSize(SCREEN_SIZE);
        this.add(label);

        //sound text
        Font fontStyle = new Font("Magneto",Font.BOLD,30);
        JLabel soundText = new JLabel("sound");
        soundText.setFont(fontStyle);
        Color fontClr = Color.WHITE;
        soundText.setForeground(fontClr);
        Color backClr = Color.WHITE;
        soundText.setBackground(backClr);
        soundText.setBounds(30,120,150,50);
        label.add(soundText);


        //sound slider
        sound = new JSlider(SwingConstants.HORIZONTAL,0,100,0);
        sound.setMajorTickSpacing(50);
        sound.setMinorTickSpacing(50);
        sound.setPaintTicks(true);
        sound.setPaintLabels(true);
        sound.setSnapToTicks(true);
        sound.setBounds(330,100,300,80);
        sound.setBorder(null);
        sound.setBackground(Color.WHITE);
        sound.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                chosenSound = sound.getValue();

            }
        });
        label.add(sound);

        // keySensitivity text

        JLabel keySensitivityText = new JLabel("Key Sensitivity");
        keySensitivityText.setFont(fontStyle);
        keySensitivityText.setForeground(fontClr);
        keySensitivityText.setBackground(backClr);
        keySensitivityText.setBounds(30,220,250,50);

        label.add(keySensitivityText);

        //keySensitivity slider
        keySensitivity = new JSlider(SwingConstants.HORIZONTAL,10,30,30);
        keySensitivity.setMajorTickSpacing(10);
        keySensitivity.setMinorTickSpacing(10);
        keySensitivity.setPaintTicks(true);
        keySensitivity.setPaintLabels(true);
        keySensitivity.setSnapToTicks(true);
        keySensitivity.setBounds(330,200,300,80);
        keySensitivity.setBorder(null);
        keySensitivity.setBackground(Color.WHITE);
        keySensitivity.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                chosenSensitivity = keySensitivity.getValue();

            }
        });
        label.add(keySensitivity);

        //level text
        JLabel levelText = new JLabel("Level");
        levelText.setFont(fontStyle);
        levelText.setForeground(fontClr);
        levelText.setBackground(backClr);
        levelText.setBounds(30,320,250,50);

        label.add(levelText);

        //level slider
       level = new JSlider(SwingConstants.HORIZONTAL,0,2,0);


        Hashtable<Integer,JLabel> levels = new Hashtable<>();
        levels.put(0,new JLabel("easy"));
        levels.put(1,new JLabel("medium"));
        levels.put(2,new JLabel("hard"));
        level.setLabelTable(levels);
       level.setMajorTickSpacing(1);
       level.setMinorTickSpacing(1);
       level.setPaintTicks(true);
       level.setPaintLabels(true);
       level.setBounds(330,300,300,80);
       level.setBorder(null);
       level.setBackground(Color.WHITE);
       level.addChangeListener(new ChangeListener() {
           @Override
           public void stateChanged(ChangeEvent e) {
               chosenLevel = level.getValue();

           }
       });
       label.add(level);

//upward key
        fontStyle = new Font("MS PGothic",Font.BOLD,25);
        fontClr = Color.black;
        JButton upward = new JButton("Upward");
        upward.setFont(fontStyle);
        upward.setForeground(fontClr);
        upward.setBackground(backClr);
        upward.setBorderPainted(false);
        upward.setFocusPainted(false);
        upward.setBounds(80,400,150,50);
        upward.addActionListener(e -> {
            KeyCodeCapture dialog = new KeyCodeCapture(SettingsFrame.this,"up");
            dialog.setVisible(true);
        });

        label.add(upward);

        //downward key
        JButton downward = new JButton("Downward");
        downward.setFont(fontStyle);
        downward.setForeground(fontClr);
        downward.setBackground(backClr);
        downward.setBorderPainted(false);
        downward.setFocusPainted(false);
        downward.setBounds(80,460,150,50);
        downward.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyCodeCapture dialog = new KeyCodeCapture(SettingsFrame.this,"down");
                dialog.setVisible(true);
            }
        });
        label.add(downward);
        //leftward key
        JButton leftward = new JButton("Leftward");
        leftward.setFont(fontStyle);
        leftward.setForeground(fontClr);
        leftward.setBackground(backClr);
        leftward.setBorderPainted(false);
        leftward.setFocusPainted(false);
        leftward.setBounds(80,520,150,50);
        leftward.addActionListener(e -> {
            KeyCodeCapture dialog = new KeyCodeCapture(SettingsFrame.this,"left");
            dialog.setVisible(true);
        });
        label.add(leftward);
        //rightward key
        JButton rightward = new JButton("Rightward");
        rightward.setFont(fontStyle);
        rightward.setForeground(fontClr);
        rightward.setBackground(backClr);
        rightward.setBorderPainted(false);
        rightward.setFocusPainted(false);
        rightward.setBounds(80,580,150,50);
        rightward.addActionListener(e -> {
            KeyCodeCapture dialog = new KeyCodeCapture(SettingsFrame.this,"right");
            dialog.setVisible(true);
        });
        label.add(rightward);
        // shop key
        JButton shop = new JButton("Shop");
        shop.setFont(fontStyle);
        shop.setForeground(fontClr);
        shop.setBackground(backClr);
        shop.setBorderPainted(false);
        shop.setFocusPainted(false);
        shop.setBounds(240,400,150,50);
        shop.addActionListener(e -> {
            KeyCodeCapture dialog = new KeyCodeCapture(SettingsFrame.this,"shop");
            dialog.setVisible(true);
        });
        label.add(shop);
        //ability key
        JButton ability = new JButton("Ability");
        ability.setFont(fontStyle);
        ability.setForeground(fontClr);
        ability.setBackground(backClr);
        ability.setBorderPainted(false);
        ability.setFocusPainted(false);
        ability.setBounds(240,460,150,50);
        ability.addActionListener(e -> {
            KeyCodeCapture dialog = new KeyCodeCapture(SettingsFrame.this,"ability");
            dialog.setVisible(true);
        });
        label.add(ability);

        JButton exit = new JButton("Exit");
        fontStyle = new Font("Ravie",Font.ITALIC,30);
        exit.setFont(fontStyle);
        exit.setForeground(fontClr);
        exit.setBackground(backClr);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setBounds(500,600,150,50);
        exit.addActionListener(e -> {
            new StarterMenu();
            dispose();
        });
        label.add(exit);

        //   this.setLayout(null);
        this.setVisible(true);

    }
    public static void setKeyBinding(String action, int keyCode) {

        keyBindings.put(action, keyCode);
    }

    public static int getKeyBinding(String action) {

        return keyBindings.getOrDefault(action, -1);
    }

    public static HashMap<String, Integer> getKeyBindings() {
        return keyBindings;
    }

    public static int getChosenLevel() {
        return chosenLevel;
    }

    public static int getChosenSound() {
        return chosenSound;
    }

    public static int getChosenSensitivity() {
        return chosenSensitivity;
    }

    public static void setKeyBindings(HashMap<String, Integer> keyBindings) {
        SettingsFrame.keyBindings = keyBindings;
    }
}
