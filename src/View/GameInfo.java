package View;

import Controller.CurrentAbility;

import javax.swing.*;

public class GameInfo  {
    private int time;
    private static int XP = 800;
    private int HP;
    private int waveNumber;
    private static CurrentAbility currentAbility;

    private JLabel epsilonsHP;
    private static boolean aresUnlocked;
    private static boolean acesoUnlocked;
    private static boolean proteusUnlocked;

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
}
