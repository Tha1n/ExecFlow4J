package examples;

import core.FunctionImp;
import core.Program;
import core.ProgramImp;

/**
 * Created by steve on 30/11/2016.
 */
public class Demo1 {

    public static void main(String[] args){
        Program program = new ProgramImp(Demo1.class.getSimpleName());

        program.setCurrentFunction(new FunctionImp("main", "Main", Demo1.class.getSimpleName()));
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
