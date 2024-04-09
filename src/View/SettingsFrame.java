package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class SettingsFrame extends JFrame implements KeyListener {

    private final int MENU_WIDTH = 700;
    private final int MENU_HEIGHT = 700;
    private final Dimension SCREEN_SIZE = new Dimension(MENU_WIDTH,MENU_HEIGHT);
    private final ImageIcon image;
    private final JLabel label;

    private JSlider sound;
    private JLabel soundText;
    private JSlider keySensitivity;
    private JLabel keySensitivityText;
    private JSlider level;
    private JLabel levelText;
    private JButton upward;
    private JButton downward;
    private JButton leftward;
    private JButton rightward;
    private JButton shop;
    private JButton ability;


    public SettingsFrame() {

        this.setSize(SCREEN_SIZE);
        this.setTitle("Window Kill");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setUndecorated(true);

        image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/Images/settings.jpg")));
        label = new JLabel(image);
        label.setSize(SCREEN_SIZE);
        this.add(label);

        //sound text
        Font fontStyle = new Font("Magneto",Font.BOLD,30);
        soundText = new JLabel("Sound");
        soundText.setFont(fontStyle);
        Color fontClr = Color.WHITE;
        soundText.setForeground(fontClr);
        Color backClr = Color.WHITE;
        soundText.setBackground(backClr);
        soundText.setBounds(30,120,150,50);

        label.add(soundText);




        //sound slider
        sound = new JSlider(SwingConstants.HORIZONTAL,0,100,50);
        sound.setMajorTickSpacing(10);
        sound.setMinorTickSpacing(1);
        sound.setPaintTicks(true);
        sound.setPaintLabels(true);
        sound.setBounds(330,100,300,80);
        sound.setBorder(null);
        sound.setBackground(Color.WHITE);
        label.add(sound);

        // keySensitivity text

        keySensitivityText = new JLabel("Key Sensitivity");
        keySensitivityText.setFont(fontStyle);
        keySensitivityText.setForeground(fontClr);
        keySensitivityText.setBackground(backClr);
        keySensitivityText.setBounds(30,220,250,50);

        label.add(keySensitivityText);

        //keySensitivity slider
        keySensitivity = new JSlider(SwingConstants.HORIZONTAL,0,100,50);
        keySensitivity.setMajorTickSpacing(10);
        keySensitivity.setMinorTickSpacing(1);
        keySensitivity.setPaintTicks(true);
        keySensitivity.setPaintLabels(true);
        keySensitivity.setBounds(330,200,300,80);
        keySensitivity.setBorder(null);
        keySensitivity.setBackground(Color.WHITE);
        label.add(keySensitivity);

        //level text
        levelText = new JLabel("Level");
        levelText.setFont(fontStyle);
        levelText.setForeground(fontClr);
        levelText.setBackground(backClr);
        levelText.setBounds(30,320,250,50);

        label.add(levelText);

        //level slider
       level = new JSlider(SwingConstants.HORIZONTAL,0,100,50);
       level.setMajorTickSpacing(10);
       level.setMinorTickSpacing(1);
       level.setPaintTicks(true);
       level.setPaintLabels(true);
       level.setBounds(330,300,300,80);
       level.setBorder(null);
       level.setBackground(Color.WHITE);
       label.add(level);

//upward key
        fontStyle = new Font("MS PGothic",Font.BOLD,25);
        fontClr = Color.black;
        upward = new JButton("Upward");
        upward.setFont(fontStyle);
        upward.setForeground(fontClr);
        upward.setBackground(backClr);
        upward.setBorderPainted(false);
        upward.setFocusPainted(false);
        upward.setBounds(80,400,150,50);
        upward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });





        label.add(upward);

        //downward key
        downward= new JButton("Downward");
        downward.setFont(fontStyle);
        downward.setForeground(fontClr);
        downward.setBackground(backClr);
        downward.setBorderPainted(false);
        downward.setFocusPainted(false);
        downward.setBounds(80,460,150,50);
        label.add(downward);
        //leftward key
        leftward= new JButton("Leftward");
        leftward.setFont(fontStyle);
        leftward.setForeground(fontClr);
        leftward.setBackground(backClr);
        leftward.setBorderPainted(false);
        leftward.setFocusPainted(false);
        leftward.setBounds(80,520,150,50);
        label.add(leftward);
        //rightward key
        rightward= new JButton("Rightward");
        rightward.setFont(fontStyle);
        rightward.setForeground(fontClr);
        rightward.setBackground(backClr);
        rightward.setBorderPainted(false);
        rightward.setFocusPainted(false);
        rightward.setBounds(80,580,150,50);
        label.add(rightward);
        // shop key
        shop= new JButton("Shop");
        shop.setFont(fontStyle);
        shop.setForeground(fontClr);
        shop.setBackground(backClr);
        shop.setBorderPainted(false);
        shop.setFocusPainted(false);
        shop.setBounds(240,400,150,50);
        label.add(shop);
        //ability key
        ability= new JButton("Ability");
        ability.setFont(fontStyle);
        ability.setForeground(fontClr);
        ability.setBackground(backClr);
        ability.setBorderPainted(false);
        ability.setFocusPainted(false);
        ability.setBounds(240,460,150,50);
        label.add(ability);





        //   this.setLayout(null);
        this.setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
