package graphic;

import core.Program;

import javax.swing.*;
import java.awt.*;

/**
 * Created by steve on 01/12/2016.
 */
public class PanelProgram extends JPanel {

    private Program program;

    public PanelProgram(Program program){
        super();
        this.program = program;
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int[] xy = program.draw(g2d);
        // TODO check drawing size if we have to change panel size
    }
}
