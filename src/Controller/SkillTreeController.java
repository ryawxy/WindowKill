package Controller;

import Model.*;
import Model.Entity.*;
import Model.enums.Ability;
import myproject.MyProject;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SkillTreeController {

    private long currentTime;
    private long lastUsed = 0;
    // last time ability key activated
    private boolean canUseAbility;
    private long acesoTimer = 0;
    // last time HP added
    private HashMap<String,Integer> itemsXP;

    private HashMap<Ability,Boolean> canUse = new HashMap<>();
    private boolean addCeberus;
    Intersection intersection = new Intersection();
    private long cerberusTimer;
    private long currentTime2;

    public SkillTreeController(){

        itemsXP = new HashMap<>();
        itemsXP.put(String.valueOf(Ability.Ares),750);
        itemsXP.put(String.valueOf(Ability.Aceso),500);
        itemsXP.put(String.valueOf(Ability.Proteus),1000);
        itemsXP.put(String.valueOf(Ability.Astrape),1000);
        itemsXP.put(String.valueOf(Ability.Cerberus),1500);
        itemsXP.put(String.valueOf(Ability.Melampus),750);
        itemsXP.put(String.valueOf(Ability.Chiron),900);
        itemsXP.put(String.valueOf(Ability.Athena),1200);
        itemsXP.put(String.valueOf(Ability.Empusa),750);
        itemsXP.put(String.valueOf(Ability.Dolus),1500);

        canUse.clear();
        for(String ability : itemsXP.keySet()) canUse.put(Ability.valueOf(ability),false);

    }
    public  void activate(){

     //   if(canUseAbility){

            if(GameInfo.getCurrentAbility().containsKey(Ability.Aceso)){
                currentTime = System.currentTimeMillis();

                if((currentTime - acesoTimer)/(1000)>=1) {
                    for(int i=1;i<=KeyListener.getKeyPressedNumber();i++) {
                        if (Game.getEpsilon().getHP() < 100) {
                            Game.getEpsilon().setHP(Game.getEpsilon().getHP() + 1);
                            acesoTimer = currentTime;
                        }
                    }

                }


            } if(GameInfo.getCurrentAbility().containsKey(Ability.Ares)) {
                if(canUse.get(Ability.Ares)) {
                    Trigorath.setHPDecrement(Trigorath.getHPDecrement() + 2 * KeyListener.getKeyPressedNumber());
                    Squarantine.setHPDecrement(Trigorath.getHPDecrement() + 2 * KeyListener.getKeyPressedNumber());
                    Trigorath.setHPDecrement2(Trigorath.getHPDecrement2() + 2 * KeyListener.getKeyPressedNumber());
                    Squarantine.setHPDecrement2(Trigorath.getHPDecrement2() + 2 * KeyListener.getKeyPressedNumber());
                    canUse.replace(Ability.Ares,false);
                }


            } if(GameInfo.getCurrentAbility().containsKey(Ability.Empusa)){

                if(canUse.get(Ability.Empusa)) {
                    Game.getEpsilon().setRadius((int) (Game.getEpsilon().getRadius() * 0.9));
                    for(Vertex vertex : Game.getEpsilon().getVertex()) vertex.setRadius((int) (vertex.getRadius()*0.9));

                    canUse.replace(Ability.Empusa,false);

                }
            }
             if(GameInfo.getCurrentAbility().containsKey(Ability.Proteus)){

                if(canUse.get(Ability.Proteus)) {

                    Game.getEpsilon().setVertexNumber(Game.getEpsilon().getVertexNumber() + 1);
                    Game.getEpsilon().addVertex();
                    canUse.replace(Ability.Proteus,false);

                }

            } if(GameInfo.getCurrentAbility().containsKey(Ability.Dolus)){

                 if(canUse.get(Ability.Dolus)) {
                     ArrayList<Ability> unlocked = new ArrayList<>();
                     for (String s : GameInfo.getAttackLocks().keySet()) {
                         if (GameInfo.getAttackLocks().get(s)) unlocked.add(Ability.valueOf(s));
                     }
                     for (String s : GameInfo.getDefenceLocks().keySet()) {
                         if (GameInfo.getDefenceLocks().get(s)) unlocked.add(Ability.valueOf(s));
                     }
                     for (String s : GameInfo.getTrnsformLocks().keySet()) {
                         if (!s.equals("Dolus")) {
                             if (GameInfo.getTrnsformLocks().get(s)) unlocked.add(Ability.valueOf(s));
                         }
                     }
                     Random random = new Random();

                     int first = random.nextInt(unlocked.size());
                     int second = random.nextInt(unlocked.size());
                     while (second == first) second = random.nextInt(unlocked.size());

                     GameInfo.getCurrentAbility().remove(Ability.Dolus);
                     GameInfo.getCurrentAbility().put(unlocked.get(first), true);
                     GameInfo.getCurrentAbility().put(unlocked.get(second), true);
                     canUse.replace(unlocked.get(first), true);
                     canUse.replace(unlocked.get(second), true);
                     canUse.replace(Ability.Dolus,false);

                 }


        }
             if(GameInfo.getCurrentAbility().containsKey(Ability.Melampus)){
//                     Random random = new Random();
//                     int x;
//                     for(IntersectionPoint point : Intersection.getIntersectionPoints()) {
//                         x = random.nextInt(100);
//                         if(point.isMeleeAttack()) {
//                             point.setMeleeAttack(false);
//                         }
//                     }
             } if(GameInfo.getCurrentAbility().containsKey(Ability.Chiron)){
                 if(canUse.get(Ability.Chiron)){

                     for(IntersectionPoint point : Intersection.getIntersectionPoints()){

                         if(point.isEnemyAttack()) {

                             Game.getEpsilon().increaseHP(3*KeyListener.getKeyPressedNumber());
                             point.setEnemyAttack(false);

                         }
                     }
                 }

        }
             if(GameInfo.getCurrentAbility().containsKey(Ability.Cerberus)){
                 if(canUse.get(Ability.Cerberus)) {
                     Epsilon epsilon = Game.getEpsilon();
                     if (!addCeberus) {

                         Game.getCerberuses().add(new Cerberus(epsilon.getxCenter() - 2 * epsilon.getRadius(), epsilon.getyCenter() - 2 * epsilon.getRadius()));
                         Game.getCerberuses().add(new Cerberus(epsilon.getxCenter() - epsilon.getRadius(), epsilon.getyCenter() + epsilon.getRadius()));
                         Game.getCerberuses().add(new Cerberus(epsilon.getxCenter() + epsilon.getRadius(), epsilon.getyCenter()));
                         addCeberus = true;
                     }
                     for (Trigorath trigorath : Game.getTrigoraths()) {
                         for (Cerberus cerberus : Game.getCerberuses()) {
                             Polygon trigorath1 = new Polygon(trigorath.getxPoints(), trigorath.getyPoints(), 3);
                             if (intersection.checkCollision(cerberus.getX(), cerberus.getY(), cerberus.getRadius(), trigorath1)) {

                                 currentTime2 = System.currentTimeMillis();
                                 
                                 if ((currentTime2 - cerberusTimer) / (1000) >= 10) {
                                     trigorath.decreaseHP(10*KeyListener.getKeyPressedNumber());

                                     cerberusTimer = currentTime2;
                                 }
                             }
                         }
                     }
                     for (Squarantine squarantine : Game.getSquarantine()) {
                         for (Cerberus cerberus : Game.getCerberuses()) {
                             Polygon squarantine1 = new Polygon(squarantine.getxPoints(), squarantine.getyPoints(), 4);
                             if (intersection.checkCollision(cerberus.getX(), cerberus.getY(), cerberus.getRadius(), squarantine1)) {

                                 currentTime2 = System.currentTimeMillis();

                                 if ((currentTime2 - cerberusTimer) / (1000) >= 15) {
                                     squarantine.decreaseHP(10*KeyListener.getKeyPressedNumber());

                                     cerberusTimer = currentTime2;
                                 }
                             }
                         }
                     }
                 }

                 }
             if(GameInfo.getCurrentAbility().containsKey(Ability.Astrape)){
                if(canUse.get(Ability.Astrape)){
                    for(IntersectionPoint point : Intersection.getIntersectionPoints()){

                        if(point.getEntity1() instanceof Epsilon ){
                            point.getEntity2().decreaseHP(2*KeyListener.getKeyPressedNumber());

                        }else if(point.getEntity2() instanceof Epsilon){
                            point.getEntity1().decreaseHP(2*KeyListener.getKeyPressedNumber());

                        }
                        point.setEntity1(null);
                        point.setEntity2(null);
                    }

                 }
             }
             }

    public void ableToUseAbility(){
        if(KeyListener.isAbilityKeyPressed()){

            currentTime = System.currentTimeMillis();


            if( (currentTime - lastUsed)/(1000)>=2){
                if(MyProject.getGameInfo().getXP()>=100) {

                    for(Ability ability : GameInfo.getCurrentAbility().keySet()) canUse.replace(ability,true);

                    MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP()-100);

                    lastUsed = currentTime;
                }else{
                    KeyListener.setKeyPressedNumber(KeyListener.getKeyPressedNumber()-1);
                }
            }else{
                KeyListener.setKeyPressedNumber(KeyListener.getKeyPressedNumber()-1);
            }
            KeyListener.setAbilityKeyPressed(false);
        }
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
    public void setLastUsed(long lastUsed) {
        this.lastUsed = lastUsed;
    }
    public void setAcesoTimer(long acesoTimer) {
        this.acesoTimer = acesoTimer;
    }

    public void setCanUseAbility(boolean canUseAbility) {
        this.canUseAbility = canUseAbility;
    }

    public HashMap<String, Integer> getItemsXP() {
        return itemsXP;
    }
}
