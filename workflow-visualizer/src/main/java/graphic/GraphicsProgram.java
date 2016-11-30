package graphic;

import core.Program;

import javax.swing.*;

/**
 * Created by steve on 30/11/2016.
 */
public class GraphicsProgram extends JFrame {

    private Program program;

    public GraphicsProgram(Program program){
        super(program.getName() + " Workflow Execution");

        // TODO

        setVisible(true);
    }
}
