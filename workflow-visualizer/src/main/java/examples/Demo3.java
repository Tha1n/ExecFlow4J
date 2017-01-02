package examples;

import core.FunctionImp;
import core.Program;
import core.ProgramImp;
import graphic.GraphicsProgram;

/**
 * Created by steve on 30/11/2016.
 */
public class Demo3 {

    public static void main(String[] args){
        Program program = new ProgramImp(Demo3.class.getSimpleName());
        GraphicsProgram frame = new GraphicsProgram(program, 100);
        frame.startLoop();

        // Start main
        program.setCurrentFunction(new FunctionImp("main", Demo3.class.getSimpleName(), Demo3.class.getSimpleName()));
        program.getCurrentFunction().startFunction();

        program.setCurrentFunction(new FunctionImp("firstFunc", Demo3.class.getSimpleName(), Demo3.class.getSimpleName()));
        // Start firstFunc
        program.getCurrentFunction().startFunction();

        sleep(1000);

        // End firstFunc
        program.getCurrentFunction().endFunction();


        sleep(1000);

        program.setCurrentFunction(new FunctionImp("secFunc", Demo3.class.getSimpleName(), Demo3.class.getSimpleName()));
        // Start secFunc
        program.getCurrentFunction().startFunction();

        sleep(1000);

        // End secFunc
        program.getCurrentFunction().endFunction();

        // End main
        program.getCurrentFunction().endFunction();
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {}
    }
}
