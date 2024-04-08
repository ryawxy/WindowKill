
import View.GamePanel;
import View.GlassFrame;
import View.ShopFrame;
import myproject.MyProject;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(() -> {

            //  GlassFrame.getINSTANCE();
//            try {
//                new GamePanel();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            //   System.out.println(gamePanel.getWidth());
            try {
                new MyProject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //   new ShopFrame();
        });



    }
}