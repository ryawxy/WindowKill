package View;

import Controller.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public  class GlassFrame extends JFrame {

    private static GlassFrame INSTANCE;

    private static int FRAME_WIDTH = 700;
    private static int FRAME_HEIGHT = 700;

    private GlassFrame() throws HeadlessException{
        setUndecorated(true);
        setBackground(Color.BLACK);
        setSize(700,700);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        setTitle("Window Kill");

    }
    public static GlassFrame getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new GlassFrame();
        }
        return INSTANCE;
    }

    public static void setINSTANCE(GlassFrame INSTANCE) {
        GlassFrame.INSTANCE= INSTANCE;
    }
    public void closeFrame(){
        Window window = SwingUtilities.getWindowAncestor(this);
        window.dispose();
    }

    public static int getFrameWidth() {
        return FRAME_WIDTH;
    }

    public static void setFrameWidth(int frameWidth) {
        FRAME_WIDTH = frameWidth;
    }

    public static int getFrameHeight() {
        return FRAME_HEIGHT;
    }

    public static void setFrameHeight(int frameHeight) {
        FRAME_HEIGHT = frameHeight;
    }
}