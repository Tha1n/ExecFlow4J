package examples;

import core.FunctionImp;
import core.Program;
import core.ProgramImp;

/**
 * Created by steve on 30/11/2016.
 */
public class Demo2 {

    public static void main(String[] args){
        Program program = new ProgramImp(Demo2.class.getSimpleName());

        // Start main
        program.setCurrentFunction(new FunctionImp("main", "Main", Demo2.class.getSimpleName()));
        program.getCurrentFunction().startFunction();

        sleep(200);

        program.setCurrentFunction(new FunctionImp("additionner", "Main", Demo2.class.getSimpleName()));
        // Start additionner
        program.getCurrentFunction().startFunction();

        sleep(500);

        // End additionner
        program.getCurrentFunction().endFunction();

        program.setCurrentFunction(new FunctionImp("multiplier", "Main", Demo2.class.getSimpleName()));
        // Start multiplier
        program.getCurrentFunction().startFunction();

        sleep(1000);

        // End multiplier
        program.getCurrentFunction().endFunction();

        sleep(200);

        // End main
        program.getCurrentFunction().endFunction();
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {}
    }
}
