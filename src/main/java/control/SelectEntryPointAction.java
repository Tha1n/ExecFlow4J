package control;

import view.MainWindow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by steve on 02/01/2017.
 */
public class SelectEntryPointAction implements ActionListener {

    private MainWindow window;

    public SelectEntryPointAction(MainWindow window){
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(window.getAddress().getText()));
        chooser.setDialogTitle("Choose Point Entry");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java files", "java", "java");
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            if (chooser.getSelectedFile().getParent().contains(window.getAddress().getText())) {
                window.getMain().setText(chooser.getSelectedFile().getPath());
                window.getSpoonProcess().setEnabled(true);
            }
            else
                JOptionPane.showMessageDialog(window, "Entry point is not in the chosen directory.",
                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
