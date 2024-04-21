package View;

import Controller.CurrentAbility;

import javax.swing.*;

public class GameInfo  {
    private int time;
    private static int XP = 2000;
    private int HP;
    private int waveNumber;
    private static CurrentAbility currentAbility;

    private JLabel epsilonsHP;
    private static boolean aresUnlocked;
    private static boolean acesoUnlocked;
    private static boolean proteusUnlocked;
    private static boolean canActivateAbility = true;
    private static boolean useCurrentAbility = false;

    public  GameInfo(){

    }

    public static void setXP(int XP) {
        GameInfo.XP = XP;
    }

    public static int getXP() {
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
}
