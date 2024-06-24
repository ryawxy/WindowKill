package View.entityViews;

import Controller.Game;
import Model.Drawable;
import Model.Entity.Vertex;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class VertexView implements Drawable {
    private double angle;
    public VertexView(){
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

            double vertexAngle = angle + Game.getEpsilon().getVertex().indexOf(vertex) * 2 * Math.PI / Game.getEpsilon().getVertexNumber();
            vertex.setX((int) (Game.getEpsilon().getxCenter() + Game.getEpsilon().getRadius()/2 * Math.cos(vertexAngle))-18);
            vertex.setY((int) (Game.getEpsilon().getyCenter() + Game.getEpsilon().getRadius()/2 * Math.sin(vertexAngle))-18);
            g.setColor(Color.WHITE);
            g.fillOval((vertex.getX()), (vertex.getY()), vertex.getRadius(), vertex.getRadius());
        }
    }
}
