package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steve on 16/11/2016.
 */
public class FunctionImp implements Function {

    private String name;
    private String classe;
    private List<Function> callees;
    private long time;
    private Function caller;
    private Program program;

    private boolean started;
    private boolean ended;

    public FunctionImp(String name, String classe, String program) {
        this.name = name;
        this.classe = classe;
        this.program = ProgramImp.getProgram(program);

        this.time = 0;
        this.callees = new ArrayList<Function>();
        this.caller = this.program.getCurrentFunction();
    }

    public void startFunction(){
        started = true;
        program.setCurrentFunction(this);
    }

    public void endFunction(){
        ended = true;
        time = System.currentTimeMillis() - time;
        program.setCurrentFunction(getCaller());
    }

    public long getTime(){
        if (started)
            if (! ended) return System.currentTimeMillis() - time;
            else return time;
        else return time;
    }

    public String getName(){
        return name;
    }

    public String getClassName(){
        return classe;
    }

    public Function getCaller(){
        return caller;
    }

    public List<Function> getCallees(){
        return callees;
    }

    public Program getProgram(){
        return program;
    }
}
