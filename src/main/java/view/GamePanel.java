package view;

import Controller.Game;
import Model.entity.Vertex;
import Controller.MouseListener;
import view.entityViews.*;
import view.entityViews.barricados.BarricadosView;
import view.entityViews.blackOrb.BlackOrbView;
import view.entityViews.blackOrb.LaserView;
import view.entityViews.smiley.FireView;
import view.entityViews.smiley.SmileyPointerView;
import view.entityViews.smiley.SmileyPunchView;
import view.entityViews.smiley.SmileyView;
import view.entityViews.wyrm.WyrmView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GamePanel extends JPanel {
    static GamePanel INSTANCE;
    private static int FRAME_WIDTH = 700;
    private static int FRAME_HEIGHT = 700;
    private static double angle;
    private final NecropickView necropickView = new NecropickView(GlassFrame.getINSTANCE());
    private final ArchmireView archmireView = new ArchmireView(GlassFrame.getINSTANCE());
    private final WyrmView wyrmView = new WyrmView(GlassFrame.getINSTANCE());
    private final BarricadosView barricadosView = new BarricadosView(GlassFrame.getINSTANCE());
    private final BlackOrbView blackOrbView = new BlackOrbView(GlassFrame.getINSTANCE());
    private final LaserView laserView = new LaserView(GlassFrame.getINSTANCE());
    private final SmileyView smileyView = new SmileyView(GlassFrame.getINSTANCE());
    private final SmileyPointerView smileyPointerView = new SmileyPointerView(GlassFrame.getINSTANCE());
    private final FireView fireView = new FireView(GlassFrame.getINSTANCE());
    private final SmileyPunchView smileyPunchView = new SmileyPunchView(GlassFrame.getINSTANCE());
    public GamePanel() throws IOException {


        MouseListener mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);
        setDoubleBuffered(true);

 //       setBorder(BorderFactory.createLineBorder(Color.black, 5));
        setBackground(new Color(0,0,0));
        Dimension SCREEN_SIZE = new Dimension(700, 700);
        setSize(SCREEN_SIZE);

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
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());

        necropickView.paint(g2D);

        archmireView.paint(g2D);

        wyrmView.paint(g2D);
        barricadosView.paint(g2D);
        blackOrbView.paint(g2D);
        laserView.paint(g2D);

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


        OmenoctView omenoctView = new OmenoctView(GlassFrame.getINSTANCE());
        omenoctView.paint(g2D);

        smileyView.paint(g2D);
        smileyPointerView.paint(g2D);
        fireView.paint(g2D);
        smileyPunchView.paint(g2D);

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
