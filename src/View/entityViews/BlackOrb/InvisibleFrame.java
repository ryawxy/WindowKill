package View.entityViews.BlackOrb;

import javax.swing.*;
import java.awt.*;

public class InvisibleFrame extends JFrame {

    Rectangle bound;
    public InvisibleFrame(int x, int y){
        this.setSize(150,150);
        this.setBackground(new Color(0,0,0,0));
        this.setLocation(x,y);
      this.setUndecorated(true);
        this.setTitle("Invisible frame");
        InvisiblePanel invisiblePanel= new InvisiblePanel(150,150);
        this.setContentPane(invisiblePanel);

     invisiblePanel.setBound(new Rectangle(x,y,150,150));
        invisiblePanel.setItsFrame(this);
        this.setVisible(true);

    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }
}
