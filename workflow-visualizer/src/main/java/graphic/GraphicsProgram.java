package graphic;

import core.Program;

import javax.swing.*;
import java.awt.*;

/**
 * Created by steve on 30/11/2016.
 */
public class GraphicsProgram extends JFrame {

    private PanelProgram drawing;
    private boolean isLooped;
    private long delta;

    public GraphicsProgram(Program program, long delta){
        super(program.getName() + " Workflow Execution");
        this.drawing = new PanelProgram(program);
        this.delta = delta;
        isLooped = false;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 700));
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

        JScrollPane scrollable = new JScrollPane(drawing);
        getContentPane().add(scrollable);
        setVisible(true);
    }

    public void startLoop(){
        if (! isLooped) {
            isLooped = true;
            new Thread() {
                public void run() {
                    int loop = 0;

                    while (true) {
                        if (loop > delta){
                            loop = 0;
                            drawing.repaint();
                        }
                        else {
                            loop++;
                            try {
                                sleep(1);
                            } catch (InterruptedException e) {}
                        }
                    }
                }
            }.start();
        }
    }
}
