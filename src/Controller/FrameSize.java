package Controller;

import View.GamePanel;

import java.awt.*;
import java.io.IOException;

public class FrameSize {

    GamePanel gamePanel;
    private int newX;
    private int newY;

    public FrameSize(GamePanel gamePanel) throws IOException {

        this.gamePanel = gamePanel;
    }
    public void shrink (){
        if(GamePanel.getFRAME_WIDTH()>=200 && GamePanel.getFRAME_HEIGHT()>=200){

            gamePanel.setFRAME_WIDTH(GamePanel.getFRAME_WIDTH()-2*Constants.shrinkAmount());
            //   Constants.setFrameWidth(Constants.getFrameWidth()-2*Constants.shrinkAmount());
            gamePanel.setFRAME_HEIGHT(GamePanel.getFRAME_HEIGHT()-2*Constants.shrinkAmount());
            //   Constants.setFrameHeight(Constants.getFrameHeight()-2*Constants.shrinkAmount());
            newX = gamePanel.getX() + Constants.shrinkAmount();
            newY = gamePanel.getY() + Constants.shrinkAmount();
            gamePanel.setSize(GamePanel.getFRAME_WIDTH(), GamePanel.getFRAME_HEIGHT());
            //    Constants.setScreenSize(new Dimension(Constants.getFrameWidth(),Constants.getFrameHeight()));
            gamePanel.setBounds(newX,newY, GamePanel.getFRAME_WIDTH(), GamePanel.getFRAME_HEIGHT());
            //  GameFrame.getPanel().setBounds(newX,newY,gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
            //   gamePanel.getRootPane().setSize(GamePanel.getFRAME_WIDTH(), GamePanel.getFRAME_HEIGHT());
            //  GameFrame.getPanel().setSize(gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
        }
    }
    public void expand(Direction direction){

        //expand game frame from right side
        if(direction.equals(Direction.RIGHT)){
            gamePanel.setFRAME_WIDTH(GamePanel.getFRAME_WIDTH()+Constants.expandAmount());
            Constants.setFrameWidth(GamePanel.getFRAME_WIDTH()+Constants.expandAmount());
            gamePanel.setSize(GamePanel.getFRAME_WIDTH(), GamePanel.getFRAME_HEIGHT());
            Constants.setScreenSize(new Dimension(Constants.getFrameWidth(),Constants.getFrameHeight()));
            gamePanel.setLocation(gamePanel.getX()+1, gamePanel.getY());

        }
        //expand game frame from left side
        if(direction.equals(Direction.LEFT)){
            gamePanel.setFRAME_WIDTH(GamePanel.getFRAME_WIDTH()+Constants.expandAmount());
            Constants.setFrameWidth(GamePanel.getFRAME_WIDTH()+Constants.expandAmount());
            gamePanel.setSize(GamePanel.getFRAME_WIDTH(), GamePanel.getFRAME_HEIGHT());
            Constants.setScreenSize(new Dimension(Constants.getFrameWidth(),Constants.getFrameHeight()));
            gamePanel.setLocation(gamePanel.getX()-(Constants.expandAmount()+1), gamePanel.getY());
        }
        //expand game frame from downside
        if(direction.equals(Direction.DOWN)){
            gamePanel.setFRAME_HEIGHT(GamePanel.getFRAME_HEIGHT()+Constants.expandAmount());
            Constants.setFrameHeight(GamePanel.getFRAME_HEIGHT()+Constants.expandAmount());
            gamePanel.setSize(GamePanel.getFRAME_WIDTH(), GamePanel.getFRAME_HEIGHT());
            Constants.setScreenSize(new Dimension(Constants.getFrameWidth(),Constants.getFrameHeight()));
            gamePanel.setLocation(gamePanel.getX(), gamePanel.getY()+1);
        }
        //expand game frame from upside
        if(direction.equals(Direction.UP)){
            gamePanel.setFRAME_HEIGHT(GamePanel.getFRAME_HEIGHT()+Constants.expandAmount());
            Constants.setFrameHeight(GamePanel.getFRAME_HEIGHT()+Constants.expandAmount());
            gamePanel.setSize(GamePanel.getFRAME_WIDTH(), GamePanel.getFRAME_HEIGHT());
            Constants.setScreenSize(new Dimension(Constants.getFrameWidth(),Constants.getFrameHeight()));
            gamePanel.setLocation(gamePanel.getX(), gamePanel.getY()-(Constants.expandAmount()+1));
        }

    }

}

