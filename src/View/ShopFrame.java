package View;

import Controller.KeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ShopFrame extends JFrame {

    JPanel panel;
    private final int SHOP_WIDTH = 700;
    private final int SHOP_HEIGHT = 700;
    private final Dimension SCREEN_SIZE = new Dimension(SHOP_WIDTH,SHOP_HEIGHT);
    private final ImageIcon image;
    private JLabel label;
    private JButton banish;
    private JButton empower;
    private JButton heal;
    private JButton exit;
    private static boolean healItem;
    private static boolean empowerItem;
    private String[] option = {"yes","no"};

    public ShopFrame(){

        this.setSize(SCREEN_SIZE);
        this.setTitle("Window Kill");
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);

        image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/Images/shop.jpg")));
        label = new JLabel(image);
        label.setSize(SCREEN_SIZE);
        this.add(label);

        //banish button
        banish = new JButton("O'Hephaestus,Banish");
        Font fontStyle = new Font("Imprint MT Shadow",Font.BOLD,30);
        banish.setFont(fontStyle);
        Color fontClr = Color.WHITE;
        banish.setForeground(fontClr);
        Color backClr = Color.BLACK;
        banish.setBackground(backClr);
        banish.setBorderPainted(false);
        banish.setFocusPainted(false);
        banish.setBounds(200,100,400,80);

        //empower button
        empower = new JButton("O'Athena,Empower");
        empower.setFont(fontStyle);
        empower.setForeground(fontClr);
        empower.setBackground(backClr);
        empower.setBorderPainted(false);
        empower.setFocusPainted(false);
        empower.setBounds(200,210,400,80);
        empower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GamePanel.getEpsilon().getXP()<75){
                    JOptionPane.showMessageDialog(ShopFrame.this,
                            "You don't have enough XP to purchase this item.");
                }else{

                    int purchase = JOptionPane.showOptionDialog(ShopFrame.this,
                            "this item costs 75 XP.DO you want to purchase it?", null, JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                    if(purchase == 0){
                        empowerItem = true;
                    }
                }
            }
        });

        //heal button
        heal = new JButton("O'Opollo,Heal");
        heal.setFont(fontStyle);
        heal.setForeground(fontClr);
        heal.setBackground(backClr);
        heal.setBorderPainted(false);
        heal.setFocusPainted(false);
        heal.setBounds(200,320,400,80);
        heal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(GamePanel.getEpsilon().getXP()<50){
                    JOptionPane.showMessageDialog(ShopFrame.this,
                            "You don't have enough XP to purchase this item.");
                }else{
                    int purchase = JOptionPane.showOptionDialog(ShopFrame.this,
                            "this item costs 50 XP.DO you want to purchase it?", null, JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
                    if(purchase == 0){
                        GamePanel.getEpsilon().setXP(GamePanel.getEpsilon().getXP()-50);
                        healItem = true;
                    }
                }
            }
        });

        //exit button
        exit = new JButton("exit");
        exit.setFont(fontStyle);
        exit.setForeground(fontClr);
        exit.setBackground(backClr);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setBounds(200,430,400,80);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                KeyListener.setPauseGame(false);

            }
        });




        this.add(banish);
        this.add(empower);
        this.add(heal);
        this.add(exit);
        this.setLayout(null);
        this.setVisible(true);

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
}
