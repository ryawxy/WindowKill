package view.entityViews.blackOrb;

import javax.swing.*;
import java.awt.*;

public class InvisibleFrame extends JFrame {

    Rectangle bound;
    public InvisibleFrame(int x, int y,int width,int height){
        this.setSize(width,height);
        this.setUndecorated(true);
        this.setBackground(new Color(0,0,0,0));
        this.setLocation(x,y);
        this.setLayout(new BorderLayout());
        this.setTitle("Invisible frame");
        InvisiblePanel invisiblePanel= new InvisiblePanel(x,y);
        this.setContentPane(invisiblePanel);

     invisiblePanel.setBound(new Rectangle(x,y,width,height));
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
