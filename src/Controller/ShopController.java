package Controller;

import Model.*;
import View.ShopFrame;

import java.util.HashMap;

public class ShopController {


    private HashMap<String,Integer> itemsXP;
    private int banishTimer;
    private int index=ShotGun.getShots().size()-3;
    //balls shooting one by on in empower mode
    private int time=3;
    //amount of time since shooting the next fire
    private int empowerTime;
    //amount of time that has passed since empower item is activated
    private long slaughterCoolDown;
    private static boolean canUse = false;
    private static long currentTime;
    private static long lastUsed;


    public ShopController(){

        itemsXP = new HashMap<>();
        itemsXP.put(String.valueOf(ShopItem.Banish),100);
        itemsXP.put(String.valueOf(ShopItem.Empower),75);
        itemsXP.put(String.valueOf(ShopItem.Heal),50);
        itemsXP.put(String.valueOf(ShopItem.Dismay),120);
        itemsXP.put(String.valueOf(ShopItem.Slumber),150);
        itemsXP.put(String.valueOf(ShopItem.Slaughter),200);

    }
    public void activate(){

        if(GameInfo.getCurrentShopItem().containsKey(ShopItem.Banish)){
            banishTimer++;
//            for(GameObjects enemy : Game.getEnemies()) {
//                if(banishTimer<40){
//                System.out.println(banishTimer);
//                enemy.setxVelocity(enemy.getxVelocity() * -1);
//                enemy.setyVelocity(enemy.getyVelocity() * -1);
//
//            }
//            }
            if(banishTimer>=40){
                banishTimer = 0;
                GameInfo.getCurrentShopItem().remove(ShopItem.Banish);
            }
        } if(GameInfo.getCurrentShopItem().containsKey(ShopItem.Heal)){

                Game.getEpsilon().setHP(Game.getEpsilon().getHP()+10);
                GameInfo.getCurrentShopItem().remove(ShopItem.Heal);
        }if(GameInfo.getCurrentShopItem().containsKey(ShopItem.Empower)){

            empowerTime++;

            if (MouseListener.isShootinEmpowerMode()) {
                time++;

                for (int i = 0; i <= index; i++) {
                    ShotGun.getShots().get(i).move();
                }
                if (time >= 2) {
                    if (index <= ShotGun.getShots().size() - 2) {
                        index++;
                        time = 0;
                    }
                }
            }
            if(empowerTime >= 500){
                GameInfo.getCurrentShopItem().remove(ShopItem.Empower);
                empowerTime = 0;
            }

        } if(GameInfo.getCurrentShopItem().containsKey(ShopItem.Slaughter)&& canUse){

            for(IntersectionPoint point : Intersection.getIntersectionPoints()){
                if(point.getEntity1() instanceof  ShotGun){

                    if(point.getEntity2()!=null){
                        point.getEntity2().decreaseHP(50);
                        canUse = false;
                    }
                }else if(point.getEntity2() instanceof ShotGun){
                    if(point.getEntity1()!=null){
                        point.getEntity1().decreaseHP(50);
                        canUse = false;
                    }

                }
            }
        }

    }
    public static void canUSe(){
            currentTime = System.currentTimeMillis();
        if((currentTime - lastUsed)/(1000)>=120) {
            lastUsed = currentTime;
            canUse = true;
        }

    }

    public HashMap<String, Integer> getItemsXP() {
        return itemsXP;
    }

    public static boolean isCanUse() {
        return canUse;
    }
}
