package view;

import control.SelectAdressAction;
import control.SelectPointEntryAction;
import control.spoonProcessAction;

import javax.swing.*;
import java.awt.*;

/**
 * Created by steve on 02/01/2017.
 */
public class MainWindow extends JFrame {

    private JTextField address;
    private JTextField main;
    private JButton selectMain;
    private JButton spoonProcess;

    public MainWindow(){
        super("ExecFlow4J");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(500, 150));
        setResizable(true);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Panel
        JPanel panelAction = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = GridBagConstraints.RELATIVE;
        gc.fill=GridBagConstraints.BOTH;
        gc.anchor=GridBagConstraints.CENTER;
        gc.insets = new Insets(2, 2, 2, 2);
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridy = 0;
        address = new JTextField();
        address.setEnabled(false);
        JButton selectAddress = new JButton("Select Directory");
        selectAddress.addActionListener(new SelectAdressAction(this));
        selectAddress.setPreferredSize(new Dimension(150, 25));
        panelAction.add(address, gc);
        panelAction.add(selectAddress, gc);

        gc.gridy = 1;
        main = new JTextField();
        main.setEnabled(false);
        selectMain = new JButton("Select Point Entry");
        selectMain.addActionListener(new SelectPointEntryAction(this));
        selectMain.setPreferredSize(new Dimension(150, 25));
        selectMain.setEnabled(false);
        panelAction.add(main, gc);
        panelAction.add(selectMain, gc);

        gc.gridy = 2;
        spoonProcess = new JButton("Process");
        spoonProcess.setPreferredSize(new Dimension(300, 25));
        spoonProcess.addActionListener(new spoonProcessAction(this));
        spoonProcess.setEnabled(false);
        panelAction.add(spoonProcess, gc);

        getContentPane().add(panelAction);
        setVisible(true);
    }

    public JTextField getAddress() {
        return address;
    }

    public JTextField getMain() {
        return main;
    }

    public JButton getSpoonProcess() {
        return spoonProcess;
    }

    public JButton getSelectMain() {
        return selectMain;
    }

    public static void main(String[] args){
        new MainWindow();
    }
}