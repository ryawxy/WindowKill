package Model;

import Controller.Game;
import Model.entity.*;


import Model.entity.blackOrb.BlackOrb;
import Model.entity.blackOrb.BlackOrbArray;
import Model.enums.ArchmireType;
import Model.enums.BarricadosType;
import Model.enums.EnemyType;
import myproject.MyProject;

import view.GlassFrame;
import view.entityViews.barricados.BarricadosFrame;
import view.entityViews.blackOrb.BlackOrbFrame;
import view.entityViews.wyrm.WyrmFrame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Wave {
    private final Random random = new Random();
    private final LinkedList<EnemyType> enemyTypes = new LinkedList<>();
    public final ArrayList<GameObjects> waveEnemy = new ArrayList<>();
    private boolean stopWave1;
    private boolean stopWave2;
    private boolean stopWave3;
    private boolean stopWave4;
    private boolean stopWave5;


    public Wave() {

        enemyTypes.add(EnemyType.Omenoct);
        enemyTypes.add(EnemyType.Necropick);
        enemyTypes.add(EnemyType.Wyrm);
//        enemyTypes.add(EnemyType.MiniArchmire);
//        enemyTypes.add(EnemyType.LargeArchmire);
        enemyTypes.add(EnemyType.Blackorb);
        enemyTypes.add(EnemyType.Barricados1);
        enemyTypes.add(EnemyType.Barricados2);

        MyProject.getGameInfo().setCurrentWave(1);
    }

    public void initWave1() {
        if (MyProject.getGameInfo().getCurrentWave() == 1) {
            if (Math.random() < 0.005) {
                if (Game.getKilledEnemies().isEmpty()) {
                    if (!stopWave1) {
                        System.out.println(111);
                        int index = random.nextInt(4);
                        EnemyType enemyType = enemyTypes.get(index);
                        int x = random.nextInt(GlassFrame.getINSTANCE().getWidth());
                        int y = random.nextInt(GlassFrame.getINSTANCE().getHeight());

                       generateEnemy(enemyType, x, y);


                    }
                } else stopWave1 = true;
            }

            if (stopWave1) {
                if (Game.getKilledEnemies().size() == waveEnemy.size()) {
                    MyProject.getGameInfo().setCurrentWave(2);
                    waveEnemy.clear();

                    Game.getKilledEnemies().clear();

                }
            }
        }
    }
    public void initWave2(){
        if(MyProject.getGameInfo().getCurrentWave()==2){
            if(Math.random()<0.005) {
                if (Game.getKilledEnemies().isEmpty()) {
                    if (!stopWave2) {
                        int index = random.nextInt(6);
                        EnemyType enemyType = enemyTypes.get(index);
                        int x = random.nextInt(GlassFrame.getINSTANCE().getWidth());
                        int y = random.nextInt(GlassFrame.getINSTANCE().getHeight());

                        generateEnemy(enemyType, x, y);


                    }
                } else stopWave2 = true;
            }

            if(stopWave2) {
                if (Game.getKilledEnemies().size() == waveEnemy.size()) {
                    MyProject.getGameInfo().setCurrentWave(3);
                    waveEnemy.clear();
                    Game.getKilledEnemies().clear();

                }
            }
        }
    }
    public void initWave3(){
        if(MyProject.getGameInfo().getCurrentWave()==3){
            if(Math.random()<0.005) {
                if (Game.getKilledEnemies().isEmpty()) {
                    if (!stopWave3) {
                        int index = random.nextInt(6);
                        EnemyType enemyType = enemyTypes.get(index);
                        int x = random.nextInt(GlassFrame.getINSTANCE().getWidth());
                        int y = random.nextInt(GlassFrame.getINSTANCE().getHeight());

                        generateEnemy(enemyType, x, y);


                    }
                } else stopWave3 = true;
            }

            if(stopWave3) {
                if (Game.getKilledEnemies().size() == waveEnemy.size()) {
                    MyProject.getGameInfo().setCurrentWave(4);
                    waveEnemy.clear();
                    Game.getKilledEnemies().clear();

                }
            }
        }
    }
    public void initWave4(){
        if(MyProject.getGameInfo().getCurrentWave()==4){
            if(Math.random()<0.005) {
                if (Game.getKilledEnemies().isEmpty()) {
                    if (!stopWave4) {
                        int index = random.nextInt(6);
                        EnemyType enemyType = enemyTypes.get(index);
                        int x = random.nextInt(GlassFrame.getINSTANCE().getWidth());
                        int y = random.nextInt(GlassFrame.getINSTANCE().getHeight());

                       generateEnemy(enemyType, x, y);


                    }
                } else stopWave4 = true;
            }

            if(stopWave4) {
                if (Game.getKilledEnemies().size() == waveEnemy.size()) {
                    MyProject.getGameInfo().setCurrentWave(5);
                    waveEnemy.clear();
                    Game.getKilledEnemies().clear();

                }
            }
        }
    }
    public void initWave5(){
        if(MyProject.getGameInfo().getCurrentWave()==5){
            if(Math.random()<0.005) {
                if (Game.getKilledEnemies().isEmpty()) {
                    if (!stopWave5) {
                        int index = random.nextInt(4);
                        EnemyType enemyType = enemyTypes.get(index);
                        int x = random.nextInt(GlassFrame.getINSTANCE().getWidth());
                        int y = random.nextInt(GlassFrame.getINSTANCE().getHeight());

                        generateEnemy(enemyType, x, y);


                    }
                } else stopWave5 = true;
            }

            if(stopWave5) {
                if (Game.getKilledEnemies().size() == waveEnemy.size()) {
                    MyProject.getGameInfo().setCurrentWave(6);
                    waveEnemy.clear();
                    Game.getKilledEnemies().clear();

                }
            }
        }
    }

    public void generateEnemy(EnemyType enemyType,int x,int y)  {
        GameObjects enemy;
        if(enemyType.equals(EnemyType.Omenoct)){
            enemy = new Omenoct(x,y);
            waveEnemy.add(enemy);
        }
        else if(enemyType.equals(EnemyType.Barricados1)){
          BarricadosFrame barricadosFrame =   new BarricadosFrame(x,y,BarricadosType.T1);
            enemy = barricadosFrame.getBarricados();

        }
        else if(enemyType.equals(EnemyType.Barricados2)){
            BarricadosFrame barricadosFrame =   new BarricadosFrame(x,y,BarricadosType.T2);
            enemy = barricadosFrame.getBarricados();
        }
//        else if(enemyType.equals(EnemyType.MiniArchmire)){
//            enemy =  new Archmire(x,y, ArchmireType.MINI);
//            waveEnemy.add(enemy);
//        }
//        else if(enemyType.equals(EnemyType.LargeArchmire)){
//            enemy =  new Archmire(x,y, ArchmireType.LARGE);
//            waveEnemy.add(enemy);
//        }
        else if(enemyType.equals(EnemyType.Wyrm)) {
            WyrmFrame wyrmFrame = new WyrmFrame(x,y);
            enemy = wyrmFrame.getWyrm();
            waveEnemy.add(enemy);

        }
        else if(enemyType.equals(EnemyType.Blackorb)) {
            boolean hasMAde = false;
            for (GameObjects gameObjects : waveEnemy) {
                if (gameObjects instanceof BlackOrb) {
                    hasMAde = true;
                    break;
                }
            }
            if (!hasMAde) {
                BlackOrbArray blackOrbArray = new BlackOrbArray();
                try {
                    blackOrbArray.createBlackOrbArray(x, y);
                    blackOrbArray.createInvisibleFrame();
                    for (BlackOrbFrame blackOrbFrame : blackOrbArray.getBlackOrbArray())
                        waveEnemy.add(blackOrbFrame.getBlackOrb());
                } catch (IOException ignored) {

                }

            }
        }
        else if(enemyType.equals(EnemyType.Necropick)) {
            enemy = new Necropick(x,y);
            waveEnemy.add(enemy);
        }


    }


}
