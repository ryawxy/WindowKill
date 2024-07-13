package view.entityViews.barricados;

import Controller.Game;
import Controller.KeyListener;
import Model.entity.Barricados;
import Model.FrameType;
import Model.entity.Wyrm;
import Model.enums.BarricadosType;
import view.entityViews.NecropickView;
import view.entityViews.wyrm.WyrmPanel;
import view.entityViews.wyrm.WyrmView;

import javax.swing.*;
import java.awt.*;
public class BarricadosFrame extends JFrame implements FrameType {
    private final Barricados barricados;

    BarricadosType barricadosType;

    public BarricadosFrame(int x,int y,BarricadosType barricadosType){

        this.setSize(200,200);
        this.setBackground(Color.BLACK);
        this.setLocation(x,y);
        this.setUndecorated(true);
        this.setTitle("Barricados frame");
        BarricadosPanel barricadosPanel = new BarricadosPanel(200,200,barricadosType);
        this.setContentPane(barricadosPanel);
        barricadosPanel.setBound(new Rectangle(x,y,200,200));
        barricadosPanel.setItsFrame(this);
        this.setVisible(true);
        barricados = new Barricados(0,0);
        barricados.setLocalFrame(this);
        barricados.setType(barricadosType);
        barricados.setItsFrame(this);
        barricadosPanel.setBarricadosView(new BarricadosView(this));
        barricadosPanel.setWyrmView(new WyrmView(this));
        barricadosPanel.setNecropickView(new NecropickView(this));
        Game.getFrames().add(this);
        Game.getBarricados().add(barricados);
        Game.getBarricadosFrames().add(this);
        Game.getEnemies().add(barricados);
        new KeyListener((JPanel) getContentPane());

    }

    @Override
    public boolean isometric() {
        return true;
    }

    @Override
    public boolean solid() {
        return barricados.getType().equals(BarricadosType.T2);
    }

    @Override
    public Rectangle getBound() {
        return null;
    }

    public Barricados getBarricados() {
        return barricados;
    }

    public BarricadosType getBarricadosType() {
        return barricadosType;
    }

    public void invisible(){
        if(!barricados.isVisible()) dispose();
    }
}
