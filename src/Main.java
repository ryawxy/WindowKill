
import View.GamePanel;
import View.GlassFrame;
import View.ShopFrame;
import View.SkillTreeFrame;
import myproject.MyProject;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(() -> {

//            try {
//                new MyProject();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
           new SkillTreeFrame();

        });



    }
}