package Model;

import Controller.Constants;

import javax.swing.*;
import java.awt.*;

public final class GlassFrame extends JFrame {

    private static GlassFrame INSTANCE;

    private GlassFrame() throws HeadlessException{
        setUndecorated(true);
        setBackground(new Color(0,0,0,100));
        setSize(Constants.glassFrameDimension());
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);


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
}