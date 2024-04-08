package Controller;

import View.GameFrame;

public class FrameSize {

    GameFrame gameFrame;
    public FrameSize(GameFrame gameFrame){

        this.gameFrame = gameFrame;
    }
    public void shrink (){
        if(gameFrame.getFRAME_WIDTH()>=200 && gameFrame.getFRAME_HEIGHT()>=200){
            gameFrame.setFRAME_WIDTH(gameFrame.getFRAME_WIDTH()-1);
            gameFrame.setFRAME_HEIGHT(gameFrame.getFRAME_HEIGHT()-1);
            gameFrame.setSize(gameFrame.getFRAME_WIDTH(),gameFrame.getFRAME_HEIGHT());
        }
    }
}
