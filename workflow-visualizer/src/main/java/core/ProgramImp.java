package core;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static graphic.GraphicsConst.*;

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
        programs.put(this.name, this);
    }

    @Override
    public int[] draw(Graphics2D g) {
        return draw(g, new int[]{STARTX, STARTY});
    }

    @Override
    public int[] draw(Graphics2D g, int[] xy) {
        int[] maxSize = {xy[0], xy[1]};

        for(Function fonction : getCalledFunctions()){
            int[] newXY = fonction.draw(g, xy);

            if (maxSize[0] < newXY[0]) maxSize[0] = newXY[0];
            if (maxSize[1] < newXY[1]) maxSize[1] = newXY[1];

            xy[1] = newXY[1] + MARGINY;
        }

        return maxSize;
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
