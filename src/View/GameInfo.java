package View;

import Controller.CurrentAbility;

import javax.swing.*;

public class GameInfo  {

    private  int XP = 2000;

    private static CurrentAbility currentAbility;
    private static boolean aresUnlocked;
    private static boolean acesoUnlocked;
    private static boolean proteusUnlocked;
    private static boolean canActivateAbility = true;
    private static boolean useCurrentAbility = false;
    private static int currentWave;

    public  GameInfo(){

    }

    public void setXP(int XP) {this.XP = XP;
    }

    public  int getXP() {
        return XP;
    }

    public static CurrentAbility getCurrentAbility() {
        return currentAbility;
    }

    public static void setCurrentAbility(CurrentAbility currentAbility) {
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

    public static boolean isProteusUnlocked() {
        return proteusUnlocked;
    }

    public static void setProteusUnlocked(boolean proteusUnlocked) {
        GameInfo.proteusUnlocked = proteusUnlocked;
    }

    public static boolean isCanActivateAbility() {
        return canActivateAbility;
    }

    public static void setCanActivateAbility(boolean canActivateAbility) {
        GameInfo.canActivateAbility = canActivateAbility;
    }

    public static boolean isUseCurrentAbility() {
        return useCurrentAbility;
    }

    public static void setUseCurrentAbility(boolean useCurrentAbility) {
        GameInfo.useCurrentAbility = useCurrentAbility;
    }

    public static int getCurrentWave() {
        return currentWave;
    }

    public static void setCurrentWave(int currentWave) {
        GameInfo.currentWave = currentWave;
    }
}
