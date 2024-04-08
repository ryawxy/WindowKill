package View;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SettingsFrame extends JFrame {

    private final int MENU_WIDTH = 700;
    private final int MENU_HEIGHT = 700;
    private final Dimension SCREEN_SIZE = new Dimension(MENU_WIDTH,MENU_HEIGHT);
//    private final ImageIcon image;
//    private final JLabel label;

    private JSlider sound;

    public SettingsFrame(){

        this.setSize(SCREEN_SIZE);
        this.setTitle("Window Kill");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setUndecorated(true);


//        image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/Images/settings.jpg")));
//        label = new JLabel(image);
//        label.setSize(SCREEN_SIZE);
//        this.add(label);

        //sound slider
        sound = new JSlider(SwingConstants.HORIZONTAL,0,100,50);
        sound.setMajorTickSpacing(10);
        sound.setMinorTickSpacing(1);
        sound.setPaintTicks(true);
        sound.setPaintLabels(true);
        this.add(sound);




        //   this.setLayout(null);
        this.setVisible(true);

    }
}
