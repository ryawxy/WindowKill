package Model;

import Controller.Constants;
import Controller.Game;
import Model.enums.BarricadosType;
import Model.enums.Direction;
import View.GamePanel;
import View.GlassFrame;
import View.entityViews.Barricados.BarricadosFrame;


import java.io.IOException;
import java.util.ArrayList;

public class FrameSize {

    GamePanel gamePanel;
    private int newX;
    private int newY;

    private int deltaWidth;
    private int deltaHeight;
    private int minSize;

    public FrameSize(GamePanel gamePanel) throws IOException {

        this.gamePanel = gamePanel;
    }
    public void shrink (){
     minSize = 400;
     if(GameLoop.isWin()  && Game.getEpsilon().getRadius()>=GamePanel.getFRAME_HEIGHT()){
         minSize = 0;
     }
     if(GameLoop.isLose()){
         minSize = 0;
     }

        ArrayList<Direction> overlapSides = FrameIntersection.overlapSides(GlassFrame.getINSTANCE());

        if(GlassFrame.getINSTANCE().getWidth()>=minSize && GlassFrame.getINSTANCE().getHeight()>=minSize && gamePanel.getWidth()>=minSize
        && gamePanel.getHeight()>=minSize){



            if((overlapSides.contains(Direction.LEFT) && !overlapSides.contains(Direction.RIGHT)) ||
                    (!overlapSides.contains(Direction.LEFT) && overlapSides.contains(Direction.RIGHT))){
                deltaWidth = Constants.shrinkAmount();
                newX = GlassFrame.getINSTANCE().getX();
            }
            if(!overlapSides.contains(Direction.LEFT) && !overlapSides.contains(Direction.RIGHT)){
                deltaWidth = 2*Constants.shrinkAmount();
                newX = GlassFrame.getINSTANCE().getX() + Constants.shrinkAmount();
            }
            if(overlapSides.contains(Direction.LEFT) && overlapSides.contains(Direction.RIGHT)){
                deltaWidth = 0;
                newX = GlassFrame.getINSTANCE().getX();

            }

            if((overlapSides.contains(Direction.UP) && !overlapSides.contains(Direction.DOWN)) ||
                    (!overlapSides.contains(Direction.UP) && overlapSides.contains(Direction.DOWN))){
                deltaHeight = Constants.shrinkAmount();
                newY = GlassFrame.getINSTANCE().getY();
            }
            if(!overlapSides.contains(Direction.UP) && !overlapSides.contains(Direction.DOWN)){
                deltaHeight = 2*Constants.shrinkAmount();
                newY = GlassFrame.getINSTANCE().getY() + Constants.shrinkAmount();
            }
            if(overlapSides.contains(Direction.UP) && overlapSides.contains(Direction.DOWN)){
                deltaHeight = 0;
                newY = GlassFrame.getINSTANCE().getY();
            }





            GlassFrame.getINSTANCE().setSize(GlassFrame.getINSTANCE().getWidth()-deltaWidth,
                    GlassFrame.getINSTANCE().getHeight()-deltaHeight);
            GlassFrame.getINSTANCE().setLocation(newX,newY);
            gamePanel.setSize(gamePanel.getWidth()-deltaWidth,gamePanel.getHeight()-deltaHeight);

        }
    }
    public  void expand(Direction direction) {

        if (Game.getEpsilon().getLocalFrame() instanceof GlassFrame) {
            //expand game frame from right side
            if (direction.equals(Direction.RIGHT)) {

                boolean valid = true;
                for(BarricadosFrame barricadosFrame : Game.getBarricadosFrames()) {
                    if (barricadosFrame.getBarricados().getType().equals(BarricadosType.T2)) {
                        if (barricadosFrame.getX() >= GlassFrame.getINSTANCE().getX() + GlassFrame.getINSTANCE().getWidth()) {
                            if (GlassFrame.getINSTANCE().getX() + GlassFrame.getINSTANCE().getWidth() + (Constants.expandAmount() / 2) > barricadosFrame.getX()) {
                                valid = false;
                            }
                        }
                    }
                }
                    if(valid) {
                        GlassFrame.getINSTANCE().setSize(GlassFrame.getINSTANCE().getWidth() + Constants.expandAmount(), GlassFrame.getINSTANCE().getHeight());
                        gamePanel.setSize(gamePanel.getWidth() + Constants.expandAmount(), gamePanel.getHeight());

                        GlassFrame.getINSTANCE().setLocation(GlassFrame.getINSTANCE().getX() + Constants.expandAmount() / 2, GlassFrame.getINSTANCE().getY());

                    }

            }
            //expand game frame from left side
            if (direction.equals(Direction.LEFT)) {

                boolean valid = true;
                for (BarricadosFrame barricadosFrame : Game.getBarricadosFrames()) {
                    if (barricadosFrame.getBarricados().getType().equals(BarricadosType.T2)) {

                        if (barricadosFrame.getX() + barricadosFrame.getWidth() <= GlassFrame.getINSTANCE().getX()) {

                            if (GlassFrame.getINSTANCE().getX() - (Constants.expandAmount() / 2) <= barricadosFrame.getX() + barricadosFrame.getWidth()) {
                                valid = false;
                            }
                        }
                    }
                }

                if (valid) {
                    GlassFrame.getINSTANCE().setSize(GlassFrame.getINSTANCE().getWidth() + Constants.expandAmount(), GlassFrame.getINSTANCE().getHeight());
                    gamePanel.setSize(gamePanel.getWidth() + Constants.expandAmount(), gamePanel.getHeight());

                    GlassFrame.getINSTANCE().setLocation(GlassFrame.getINSTANCE().getX() - (Constants.expandAmount() / 2), GlassFrame.getINSTANCE().getY());
                }
            }

            //expand game frame from upside
            if (direction.equals(Direction.UP)) {


                boolean valid = true;
                for (BarricadosFrame barricadosFrame : Game.getBarricadosFrames()) {

                    if (barricadosFrame.getBarricados().getType().equals(BarricadosType.T2)) {
                        if (barricadosFrame.getY() + barricadosFrame.getHeight() <= GlassFrame.getINSTANCE().getY()) {
                            if (GlassFrame.getINSTANCE().getY() - (Constants.expandAmount() / 2) >= barricadosFrame.getY() + barricadosFrame.getHeight()) {
                                valid = false;
                            }
                        }
                    }
                }
                if (valid) {
                    GlassFrame.getINSTANCE().setSize(GlassFrame.getINSTANCE().getWidth(), GlassFrame.getINSTANCE().getHeight() + Constants.expandAmount());
                    gamePanel.setSize(gamePanel.getWidth(), gamePanel.getHeight() + Constants.expandAmount());
                    GlassFrame.getINSTANCE().setLocation(GlassFrame.getINSTANCE().getX(), GlassFrame.getINSTANCE().getY() - (Constants.expandAmount() / 2));
                }
            }
            //expand game frame from downside
            if (direction.equals(Direction.DOWN)) {

                boolean valid = true;
                for (BarricadosFrame barricadosFrame : Game.getBarricadosFrames()) {
                    if (barricadosFrame.getBarricados().getType().equals(BarricadosType.T2)) {
                        if(barricadosFrame.getY()<=GlassFrame.getINSTANCE().getY()+GlassFrame.getINSTANCE().getHeight()) {
                            if (GlassFrame.getINSTANCE().getY() + GlassFrame.getINSTANCE().getHeight() + (Constants.expandAmount() / 2) <= barricadosFrame.getY()) {
                                valid = false;
                            }
                        }
                    }
                }

                if (valid) {
                    GlassFrame.getINSTANCE().setSize(GlassFrame.getINSTANCE().getWidth(), GlassFrame.getINSTANCE().getHeight() + Constants.expandAmount());
                    gamePanel.setSize(gamePanel.getWidth(), gamePanel.getHeight() + Constants.expandAmount());
                    GlassFrame.getINSTANCE().setLocation(GlassFrame.getINSTANCE().getX(), GlassFrame.getINSTANCE().getY() + Constants.expandAmount() / 2);
                }
            }


        }
    }

}

