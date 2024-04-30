package View;

import Controller.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public final class GlassFrame extends JFrame {

    private static GlassFrame INSTANCE;

    private GlassFrame() throws HeadlessException{
        setUndecorated(true);
        setBackground(new Color(0,0,0,100));
        setSize(Constants.glassFrameDimension());
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        setTitle("Window Kill");
//        Robot robot = null;
//        try {
//            robot = new Robot();
//        } catch (AWTException ex) {
//            throw new RuntimeException(ex);
//        }
//        robot.keyPress(KeyEvent.VK_WINDOWS);
//        robot.keyPress(KeyEvent.VK_D);
//        robot.keyRelease(KeyEvent.VK_WINDOWS);
//        robot.keyRelease(KeyEvent.VK_D);

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