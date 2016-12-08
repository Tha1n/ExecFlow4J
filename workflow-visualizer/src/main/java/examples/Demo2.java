package examples;

import core.FunctionImp;
import core.Program;
import core.ProgramImp;
import graphic.GraphicsProgram;

/**
 * Created by steve on 30/11/2016.
 */
public class Demo2 {

    public static void main(String[] args){
        Program program = new ProgramImp(Demo2.class.getSimpleName());
        GraphicsProgram frame = new GraphicsProgram(program, 100);
        frame.startLoop();

        // Start main
        program.setCurrentFunction(new FunctionImp("main", Demo2.class.getSimpleName(), Demo2.class.getSimpleName()));
        program.getCurrentFunction().startFunction();

        sleep(1000);

        program.setCurrentFunction(new FunctionImp("firstFunc", Demo2.class.getSimpleName(), Demo2.class.getSimpleName()));
        // Start firstFunc
        program.getCurrentFunction().startFunction();

        sleep(1000);

        // End firstFunc
        program.getCurrentFunction().endFunction();


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
