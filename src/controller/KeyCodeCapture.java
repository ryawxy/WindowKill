package controller;

import view.SettingsFrame;

import javax.swing.*;

import java.awt.event.*;
import java.util.Map;


public class KeyCodeCapture extends JDialog {

    private int keyCode;
    String action;
    public KeyCodeCapture(JFrame parent,String action) {

        super(parent,null , true);


        this.action = action;
        JLabel instructionLabel = new JLabel("current key:"+KeyEvent.getKeyText(SettingsFrame.getKeyBinding(action)));

        JTextField keyCodeField = new JTextField(10);
        JButton ok = new JButton("ok");
        JButton cancel = new JButton("cancel");
        ok.setBounds(50,50,30,30);
        ok.setBounds(50,90,30,30);

        keyCodeField.setEditable(false);
        addKeyListener(new KeyAdapter() {

            @Override

            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() != KeyEvent.VK_ESCAPE) {
                    keyCode = e.getKeyCode();
                    keyCodeField.setText(KeyEvent.getKeyText(keyCode));
                }else{
                    dispose();
                }

            }

        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!keyCodeField.getText().isEmpty()) {
                    if(!SettingsFrame.getKeyBindings().containsValue(getKeyCode())) {
                        SettingsFrame.setKeyBinding(action, getKeyCode());

                    }else{
                        for(Map.Entry<String,Integer> entry : SettingsFrame.getKeyBindings().entrySet()) {
                            if (entry.getValue() == getKeyCode())

                                JOptionPane.showMessageDialog(parent, "this key is taken for task:"+entry.getKey());
                        }
                    }
                }
                dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        JPanel panel = new JPanel();
        panel.add(instructionLabel);
        panel.add(keyCodeField);
        panel.add(ok);
        panel.add(cancel);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parent);
        addComponentListener(new ComponentAdapter() {

            @Override

            public void componentShown(ComponentEvent e) {

                requestFocusInWindow();
            }

        });

    }
    public int getKeyCode() {

        return keyCode;

    }
}