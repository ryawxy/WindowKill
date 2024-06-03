package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameInfo  {

    private  int XP = 10000;

    private static HashMap<Ability,Boolean> currentAbility = new HashMap<>();
    private static boolean aresUnlocked;


    private static boolean acesoUnlocked;
    private static boolean proteusUnlocked;
    private static LinkedHashMap<String,Boolean> trnsformLocks = new LinkedHashMap<>();
    private static LinkedHashMap<String,Boolean> defenceLocks = new LinkedHashMap<>();
    private static LinkedHashMap<String,Boolean> attackLocks = new LinkedHashMap<>();
    private static int currentWave;

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

    public static void setCurrentAbility(HashMap<Ability,Boolean> currentAbility) {
        GameInfo.currentAbility = currentAbility;
    }

    public static boolean isAresUnlocked() {
        return aresUnlocked;
    }

    public static void setAresUnlocked(boolean aresUnlocked) {
        GameInfo.aresUnlocked = aresUnlocked;
    }

    public static boolean isAcesoUnlocked() {
        return acesoUnlocked;
    }

    public static void setAcesoUnlocked(boolean acesoUnlocked) {
        GameInfo.acesoUnlocked = acesoUnlocked;
    }


    public static void setProteusUnlocked(boolean proteusUnlocked) {
        GameInfo.proteusUnlocked = proteusUnlocked;
    }

    public static int getCurrentWave() {
        return currentWave;
    }

    public static void setCurrentWave(int currentWave) {
        GameInfo.currentWave = currentWave;
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
}
