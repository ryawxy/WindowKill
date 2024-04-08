package Controller;

import View.GameFrame;

import java.awt.*;
import java.io.IOException;

public class FrameSize {

    GameFrame gameFrame;
    private int newX;
    private int newY;

    public FrameSize(GameFrame gameFrame) throws IOException {

        this.gameFrame = gameFrame;
    }
    public void shrink (){
        if(gameFrame.getFRAME_WIDTH()>=200 && gameFrame.getFRAME_HEIGHT()>=200){

            gameFrame.setFRAME_WIDTH(gameFrame.getFRAME_WIDTH()-2*Constants.shrinkAmount());
            //   Constants.setFrameWidth(Constants.getFrameWidth()-2*Constants.shrinkAmount());
            gameFrame.setFRAME_HEIGHT(gameFrame.getFRAME_HEIGHT()-2*Constants.shrinkAmount());
            //   Constants.setFrameHeight(Constants.getFrameHeight()-2*Constants.shrinkAmount());
            newX = gameFrame.getX() + Constants.shrinkAmount();
            newY = gameFrame.getY() + Constants.shrinkAmount();
            gameFrame.setSize(gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
            //    Constants.setScreenSize(new Dimension(Constants.getFrameWidth(),Constants.getFrameHeight()));
            gameFrame.setBounds(newX,newY,gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
            //  GameFrame.getPanel().setBounds(newX,newY,gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
            gameFrame.getRootPane().setSize(gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
            //  GameFrame.getPanel().setSize(gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
        }
    }
    public void expand(Direction direction){

        //expand game frame from right side
        if(direction.equals(Direction.RIGHT)){
            gameFrame.setFRAME_WIDTH(gameFrame.getFRAME_WIDTH()+Constants.expandAmount());
            Constants.setFrameWidth(gameFrame.getFRAME_WIDTH()+Constants.expandAmount());
            gameFrame.setSize(gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
            Constants.setScreenSize(new Dimension(Constants.getFrameWidth(),Constants.getFrameHeight()));
            gameFrame.setLocation(gameFrame.getX()+1,gameFrame.getY());

        }
        //expand game frame from left side
        if(direction.equals(Direction.LEFT)){
            gameFrame.setFRAME_WIDTH(gameFrame.getFRAME_WIDTH()+Constants.expandAmount());
            Constants.setFrameWidth(gameFrame.getFRAME_WIDTH()+Constants.expandAmount());
            gameFrame.setSize(gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
            Constants.setScreenSize(new Dimension(Constants.getFrameWidth(),Constants.getFrameHeight()));
            gameFrame.setLocation(gameFrame.getX()-(Constants.expandAmount()+1),gameFrame.getY());
        }
        //expand game frame from downside
        if(direction.equals(Direction.DOWN)){
            gameFrame.setFRAME_HEIGHT(gameFrame.getFRAME_HEIGHT()+Constants.expandAmount());
            Constants.setFrameHeight(gameFrame.getFRAME_HEIGHT()+Constants.expandAmount());
            gameFrame.setSize(gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
            Constants.setScreenSize(new Dimension(Constants.getFrameWidth(),Constants.getFrameHeight()));
            gameFrame.setLocation(gameFrame.getX(),gameFrame.getY()+1);
        }
        //expand game frame from upside
        if(direction.equals(Direction.UP)){
            gameFrame.setFRAME_HEIGHT(gameFrame.getFRAME_HEIGHT()+Constants.expandAmount());
            Constants.setFrameHeight(gameFrame.getFRAME_HEIGHT()+Constants.expandAmount());
            gameFrame.setSize(gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
            Constants.setScreenSize(new Dimension(Constants.getFrameWidth(),Constants.getFrameHeight()));
            gameFrame.setLocation(gameFrame.getX(),gameFrame.getY()-(Constants.expandAmount()+1));
        }

    }

}

