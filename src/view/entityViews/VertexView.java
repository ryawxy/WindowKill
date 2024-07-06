package view.entityViews;

import controller.Game;
import model.Drawable;
import model.entity.Epsilon;
import model.entity.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class VertexView implements Drawable {
    private double angle;
    private JFrame frame;
    public VertexView(JFrame frame){
        this.frame = frame;
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                double dx = e.getX() - Game.getEpsilon().getxCenter();
                double dy = e.getY() - Game.getEpsilon().getyCenter();
                angle = Math.atan2(dy, dx);


            }
        });
    }
    @Override
    public void paint(Graphics2D g) {
        for (Vertex vertex : Game.getEpsilon().getVertex()) {


            Epsilon epsilon = Game.getEpsilon();
            double vertexAngle = angle + Game.getEpsilon().getVertex().indexOf(vertex) * 2 * Math.PI / Game.getEpsilon().getVertexNumber();
            vertex.setX((int) (Game.getEpsilon().getxCenter() + Game.getEpsilon().getRadius()/2 * Math.cos(vertexAngle))-18);
            vertex.setY((int) (Game.getEpsilon().getyCenter() + Game.getEpsilon().getRadius()/2 * Math.sin(vertexAngle))-18);

            int globalX = vertex.getLocalX()+vertex.getLocalFrame().getX();
            int globalY = vertex.getLocalY()+vertex.getLocalFrame().getY();


            Rectangle bounds = new Rectangle(frame.getX(), frame.getY()
                    , frame.getWidth(), frame.getHeight());


            if (bounds.contains(globalX, globalY) || bounds.contains(globalX + vertex.getRadius(), globalY) ||
                    bounds.contains(globalX, globalY + vertex.getRadius()) ||
                    bounds.contains(globalX + vertex.getRadius(), globalY + vertex.getRadius())) {
                if (epsilon.getLocalFrames().size() == 1) {
                    g.setColor(Color.WHITE);
                    g.fillOval((int) (vertex.getLocalX()), (int) (vertex.getLocalY()), vertex.getRadius(), vertex.getRadius());


                } else {
                    g.setColor(Color.WHITE);
                    g.fillOval((int) (globalX - bounds.x), (int) (globalY - bounds.y), vertex.getRadius(), vertex.getRadius());
                }
            }

        }
    }
}
