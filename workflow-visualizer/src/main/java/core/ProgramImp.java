package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by steve on 17/11/2016.
 */
public class ProgramImp implements Program {

    private static Map<String, Program> programs = new HashMap<String, Program>();
    private String name;
    private List<Function> functions;
    private Function currentFunction;

    public ProgramImp(String name){
        super();
        this.name = name;
        currentFunction = null;
        functions = new ArrayList<Function>();
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public List<Function> getCalledFunctions() {
        return functions;
    }

    @Override
    public Function getCurrentFunction() {
        return currentFunction;
    }

    @Override
    public void setCurrentFunction(Function function){
        this.currentFunction = function;
    }

    public static Program getProgram(String program) {
        return programs.get(program);
    }
}
