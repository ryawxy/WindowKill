package view;

import Model.FrameType;

import javax.swing.*;
import java.awt.*;

public  class GlassFrame extends JFrame implements FrameType {

    private static GlassFrame INSTANCE;

    private static int FRAME_WIDTH = 600;
    private static int FRAME_HEIGHT = 600;

    private GlassFrame() throws HeadlessException{
        setUndecorated(true);
        setBackground(Color.BLACK);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
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

    @Override
    public boolean isometric() {
        return false;
    }

    @Override
    public boolean solid() {
        return false;
    }

    @Override
    public Rectangle getBound() {
        return null;
    }
}