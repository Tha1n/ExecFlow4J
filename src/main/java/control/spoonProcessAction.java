package control;

import view.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by steve on 02/01/2017.
 */
public class spoonProcessAction implements ActionListener {

    private MainWindow window;

    public spoonProcessAction(MainWindow window){
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.getSpoonProcess().setEnabled(false);
        String projectDirectory = window.getAddress().getText();
        String entryPoint = window.getMain().getText();

        //TODO Alexandre :3

        JOptionPane.showMessageDialog(window, "Project has been spooned !",
                "Process finished", JOptionPane.INFORMATION_MESSAGE);
        window.getSpoonProcess().setEnabled(true);
    }
}
