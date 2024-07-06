package view.entityViews.barricados;

import Model.entity.Barricados;
import Model.FrameType;
import Model.enums.BarricadosType;

import javax.swing.*;
import java.awt.*;


public class BarricadosFrame extends JFrame implements FrameType {
    private final Barricados barricados;
    private final Rectangle bounds;
    BarricadosType barricadosType;

    public BarricadosFrame(int x,int y,BarricadosType barricadosType){
        this.setSize(200,200);
        this.setBackground(Color.BLACK);
        this.setLocation(x,y);
        this.setUndecorated(true);
        this.setTitle("Barricados frame");
        BarricadosPanel barricadosPanel = new BarricadosPanel(200,200,barricadosType);
        this.setContentPane(barricadosPanel);
        this.getContentPane().setLocation(x,y);
        barricadosPanel.setBound(new Rectangle(x,y,200,200));
        barricadosPanel.setItsFrame(this);
        this.setVisible(true);
        barricados = new Barricados(15,15);
        barricados.setLocalFrame(this);
        barricados.setType(barricadosType);
        barricados.setItsFrame(this);
        bounds = new Rectangle(getX(),getY(),getWidth(),getHeight());
        this.barricadosType = barricadosType;

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
        return bounds;
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
