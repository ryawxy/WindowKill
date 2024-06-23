package View;

import Controller.Game;
import Controller.MouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OmenoctPanel extends JPanel {

    private static int FRAME_WIDTH = 300;
    private static int FRAME_HEIGHT = 300;
    private Dimension SCREEN_SIZE = new Dimension(300, 300);
    private MouseListener mouseListener;
    public OmenoctPanel(){

      //  mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);

        setBorder(BorderFactory.createLineBorder(Color.black, 5));
        setBackground(Color.BLUE);
        setSize(SCREEN_SIZE);
        setLocation(0,0);

        GlassFrame.getINSTANCE().add(this);


    }

    @Override
    protected void printComponent(Graphics g) {
        super.printComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        Game.getOmenoctView().paint(g2D);
        System.out.println(this.getX()+" "+this.getWidth());
    }
}
