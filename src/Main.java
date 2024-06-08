

import Model.Ability;
import myproject.MyProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        SwingUtilities.invokeLater(() -> {

            try {
//                Robot robot = null;
//                try {
//                    robot = new Robot();
//                } catch (AWTException ex) {
//                    throw new RuntimeException(ex);
//                }
//                robot.keyPress(KeyEvent.VK_WINDOWS);
//                robot.keyPress(KeyEvent.VK_D);
//                robot.keyRelease(KeyEvent.VK_WINDOWS);
//                robot.keyRelease(KeyEvent.VK_D);

                new MyProject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        });



    }
}