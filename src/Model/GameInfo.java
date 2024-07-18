package Model;

import Model.enums.Ability;
import Model.enums.ShopItem;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class GameInfo  {
    private  int XP = 10000;
    private static HashMap<Ability,Boolean> currentAbility = new HashMap<>();
    private static HashMap<ShopItem,Boolean> currentShopItem = new HashMap<>();
    private static LinkedHashMap<String,Boolean> trnsformLocks = new LinkedHashMap<>();
    private static LinkedHashMap<String,Boolean> defenceLocks = new LinkedHashMap<>();
    private static LinkedHashMap<String,Boolean> attackLocks = new LinkedHashMap<>();
    private  int currentWave;

    public  GameInfo(){

        attackLocks.put(String.valueOf(Ability.Ares),false);
        attackLocks.put(String.valueOf(Ability.Astrape),false);
        attackLocks.put(String.valueOf(Ability.Cerberus),false);

        trnsformLocks.put(String.valueOf(Ability.Proteus),false);
        trnsformLocks.put(String.valueOf(Ability.Empusa),false);
        trnsformLocks.put(String.valueOf(Ability.Dolus),false);

        defenceLocks.put(String.valueOf(Ability.Aceso),false);
        defenceLocks.put(String.valueOf(Ability.Melampus),false);
        defenceLocks.put(String.valueOf(Ability.Chiron),false);
        defenceLocks.put(String.valueOf(Ability.Athena),false);



    }

    public void setXP(int XP) {this.XP = XP;
    }

    public  int getXP() {
        return XP;
    }

    public static HashMap<Ability,Boolean> getCurrentAbility() {
        return currentAbility;
    }

    public  int getCurrentWave() {
        return currentWave;
    }

    public  void setCurrentWave(int currentWave) {
        this.currentWave = currentWave;
    }

    public static LinkedHashMap<String, Boolean> getAttackLocks() {
        return attackLocks;
    }

    public static LinkedHashMap<String, Boolean> getTrnsformLocks() {
        return trnsformLocks;
    }

    public static LinkedHashMap<String, Boolean> getDefenceLocks() {
        return defenceLocks;
    }

    public static HashMap<ShopItem, Boolean> getCurrentShopItem() {
        return currentShopItem;
    }
}
