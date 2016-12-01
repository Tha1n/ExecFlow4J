package graphic;

import core.Program;

import javax.swing.*;
import java.awt.*;

/**
 * Created by steve on 01/12/2016.
 */
public class PanelProgram extends JPanel {

    public final static int STARTX = 20;
    public final static int STARTY = 20;

    private Program program;
    public PanelProgram(Program program){
        super();
        this.program = program;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLACK);

        //TODO
    }
}
