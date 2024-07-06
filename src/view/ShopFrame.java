package view;

import controller.KeyListener;
import controller.ShopController;
import model.GameInfo;
import model.enums.ShopItem;
import myproject.MyProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class ShopFrame extends JFrame {

    private final int SHOP_WIDTH = 700;
    private final int SHOP_HEIGHT = 700;
    private final Dimension SCREEN_SIZE = new Dimension(SHOP_WIDTH,SHOP_HEIGHT);
    private final ImageIcon image;
    private JLabel label;
    private JButton banish,empower,heal,dismay,slumber,slaughter;
    private JButton exit;
    private ArrayList<JButton> buttons;
    private static boolean healItem;
    private static boolean empowerItem;
    private static boolean banishItem;
    private String[] option = {"yes","no"};
    private Font fontStyle;
    private Color fontClr;
    private  Color backClr;
    private ShopController shopController = new ShopController();

    public ShopFrame(){

        this.setSize(SCREEN_SIZE);
        this.setTitle("Window Kill");
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setVisible(true);
        buttons = new ArrayList<>();

        image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/Images/shop.jpg")));
        label = new JLabel(image);
        label.setSize(SCREEN_SIZE);
        this.add(label);

        banish = new JButton("Banish");
        empower = new JButton("Empower");
        heal = new JButton("Heal");
        dismay = new JButton("Dismay");
        slumber = new JButton("Slumber");
        slaughter = new JButton("Slaughter");

        buttons.add(banish);
        buttons.add(empower);
        buttons.add(heal);
        buttons.add(dismay);
        buttons.add(slumber);
        buttons.add(slaughter);


            for(int i=0; i<buttons.size(); i++) {
                JButton button = buttons.get(i);
                fontStyle = new Font("Imprint MT Shadow", Font.BOLD, 30);
                button.setFont(fontStyle);
                fontClr = Color.WHITE;
                button.setForeground(fontClr);
                backClr = Color.BLACK;
                button.setBackground(backClr);
                button.setBorderPainted(false);
                button.setFocusPainted(false);
                button.setBounds(200, 50 +i*100, 400, 80);
                label.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if(button.getText().equals(String.valueOf(ShopItem.Slaughter))){

                            if(!ShopController.isCanUse()){
                                JOptionPane.showMessageDialog(ShopFrame.this,
                                        "You have to wait longer to purchase this item again");

                            }
                        }
                        int XP = shopController.getItemsXP().get(button.getText());
                        if(MyProject.getGameInfo().getXP()<XP){
                            JOptionPane.showMessageDialog(ShopFrame.this,
                                    "You don't have enough XP to purchase this item.");
                        }else{

                            boolean available = !button.getText().equals(String.valueOf(ShopItem.Slaughter)) || ShopController.isCanUse();
                            if(available) {
                                int purchase = JOptionPane.showOptionDialog(ShopFrame.this,
                                        "This item costs" + XP
                                                + "XP.Do you want to purchase it?", null, JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                                if (purchase == 0) {
                                    GameInfo.getCurrentShopItem().put(ShopItem.valueOf(button.getText()), true);
                                    MyProject.getGameInfo().setXP(MyProject.getGameInfo().getXP() - XP);
                                }
                            }

                        }
                    }
                });
            }
        //exit button
        exit = new JButton("exit");
        exit.setFont(fontStyle);
        exit.setForeground(fontClr);
        exit.setBackground(backClr);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setBounds(10,630,100,60);
        label.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                KeyListener.setPauseGame(false);

            }
        });
        label.setLayout(null);
        label.setVisible(true);

    }

    public static boolean isHealItem() {
        return healItem;
    }

    public static void setHealItem(boolean healItem) {
        ShopFrame.healItem = healItem;
    }

    public static boolean isEmpowerItem() {
        return empowerItem;
    }

    public static void setEmpowerItem(boolean empowerItem) {
        ShopFrame.empowerItem = empowerItem;
    }

    public static boolean isBanishItem() {
        return banishItem;
    }

    public static void setBanishItem(boolean banishItem) {
        ShopFrame.banishItem = banishItem;
    }
}
