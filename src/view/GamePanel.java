package view;

import Controller.Game;
import Model.entity.Vertex;
import Controller.MouseListener;
import view.entityViews.EpsilonView;
import view.entityViews.ShotGunView;
import view.entityViews.TrigorathView;
import view.entityViews.VertexView;
import view.entityViews.smiley.FireView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class GamePanel extends JPanel {
    static GamePanel INSTANCE;
    private static int FRAME_WIDTH = 600;
    private static int FRAME_HEIGHT = 600;
    private Dimension SCREEN_SIZE = new Dimension(600, 600);
    private MouseListener mouseListener;
    private static double angle;

    FireView fireView = new FireView();


    public GamePanel() throws IOException {


        mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);

        setBorder(BorderFactory.createLineBorder(Color.black, 5));
        setBackground(new Color(0,0,0));

     //   setLocationToCenter(GlassFrame.getINSTANCE());
        setSize(FRAME_WIDTH,FRAME_HEIGHT);

        GlassFrame.getINSTANCE().add(this);


        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                double dx = e.getX() - Game.getEpsilon().getxCenter();
                double dy = e.getY() - Game.getEpsilon().getyCenter();
                angle = Math.atan2(dy, dx);

            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if(Game.getEpsilon().getX()+ Game.getEpsilon().getRadius() > FRAME_WIDTH){
                    Game.getEpsilon().setX(FRAME_WIDTH - Game.getEpsilon().getRadius());
                    for(Vertex vertex : Game.getEpsilon().getVertex()){
                        vertex.setX(FRAME_WIDTH- Game.getEpsilon().getRadius());
                    }
                } if(Game.getEpsilon().getY() + Game.getEpsilon().getRadius() > FRAME_HEIGHT){
                    Game.getEpsilon().setY(FRAME_HEIGHT - Game.getEpsilon().getRadius());
                    for(Vertex vertex : Game.getEpsilon().getVertex()){
                        vertex.setX(FRAME_HEIGHT- Game.getEpsilon().getRadius());
                    }
                }


                repaint();
            }
        });

        this.setLayout(null);

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        fireView.paint(g2D);

        Game.getNecropickView().paint(g2D);

        Game.getArchmireView().paint(g2D);

        //paint epsilon

        EpsilonView epsilonView = new EpsilonView(GlassFrame.getINSTANCE());
            epsilonView.paint(g2D);





        //paint epsilon's shotGun
        ShotGunView shotGunView = new ShotGunView(GlassFrame.getINSTANCE());
        shotGunView.paint(g2D);

        //paint trigorath
   //     Game.getTrigorathView().paint(g2D);
        TrigorathView trigorathView = new TrigorathView(GlassFrame.getINSTANCE());
        trigorathView.paint(g2D);

        //  paint squarantine
        Game.getSquarantineView().paint(g2D);
        //paint epsilons vertex
     //   Game.getVertexView().paint(g2D);
        VertexView vertexView = new VertexView(GlassFrame.getINSTANCE());
        vertexView.paint(g2D);

        //paint Game info
        Game.getGameInfoView().paint(g2D);

        //paint Cerberuses
        Game.getCerberusView().paint(g2D);



        Game.getOmenoctView().paint(g2D);


    }

    public static int getFRAME_WIDTH() {
        return FRAME_WIDTH;
    }

    public static void setFRAME_WIDTH(int FRAME_WIDTH) {
        GamePanel.FRAME_WIDTH = FRAME_WIDTH;
    }

    public static int getFRAME_HEIGHT() {
        return FRAME_HEIGHT;
    }

    public static void setFRAME_HEIGHT(int FRAME_HEIGHT) {
        GamePanel.FRAME_HEIGHT = FRAME_HEIGHT;
    }

    public void setLocationToCenter(GlassFrame glassFrame){
        setLocation(glassFrame.getWidth()/2-getWidth()/2,glassFrame.getHeight()/2-getHeight()/2);
    }
    public static GamePanel getINSTANCE() throws IOException {
        if(INSTANCE == null){
            INSTANCE = new GamePanel();
        }
        return INSTANCE;
    }

    public static double getAngle() {
        return angle;
    }
}
