package examples;

import core.FunctionImp;
import core.Program;
import core.ProgramImp;
import graphic.GraphicsProgram;

/**
 * Created by steve on 30/11/2016.
 */
public class Demo5 {

    private static int NUMBER_REC = 4;

    public static void main(String[] args){
        Program program = new ProgramImp(Demo5.class.getSimpleName());
        GraphicsProgram frame = new GraphicsProgram(program, 100);
        frame.startLoop();

        // Start main
        program.setCurrentFunction(new FunctionImp("main", Demo5.class.getSimpleName(), Demo5.class.getSimpleName()));
        program.getCurrentFunction().startFunction();

        sleep(1000);

        for(int i = 0; i < NUMBER_REC; i++) {
            program.setCurrentFunction(new FunctionImp("recursiveFunc", Demo5.class.getSimpleName(), Demo5.class.getSimpleName()));
            // Start recursiveFunc
            program.getCurrentFunction().startFunction();
            sleep((int) (200 * Math.random()) + 100);
        }

        for(int i = 0; i < NUMBER_REC; i++) {
            // end recursiveFunc
            program.getCurrentFunction().endFunction();
        }

        sleep(1000);

        // End main
        program.getCurrentFunction().endFunction();
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {}
    }
}
