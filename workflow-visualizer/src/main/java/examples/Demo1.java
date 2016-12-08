package examples;

import core.FunctionImp;
import core.Program;
import core.ProgramImp;
import graphic.GraphicsProgram;

/**
 * Created by steve on 30/11/2016.
 */
public class Demo1 {

    public static void main(String[] args){
        Program program = new ProgramImp(Demo1.class.getSimpleName());
        GraphicsProgram frame = new GraphicsProgram(program, 100);
        frame.startLoop();

        program.setCurrentFunction(new FunctionImp("main", Demo1.class.getSimpleName(), Demo1.class.getSimpleName()));
        program.getCurrentFunction().startFunction();

        sleep(500);

        program.getCurrentFunction().endFunction();
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {}
    }
}
