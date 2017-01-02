package core;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static graphic.GraphicsConst.*;

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

        if (caller != null)
            caller.getCallees().add(this);
        else
            this.program.getCalledFunctions().add(this);
    }

    public void startFunction(){
        started = true;
        time = System.currentTimeMillis();
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

    @Override
    public int[] draw(Graphics2D g) {
       return draw(g, new int[]{STARTX, STARTY});
    }

    @Override
    public int[] draw(Graphics2D g, int[] xy) {
        int[] maxSize = {xy[0], xy[1]};
        int oldX = xy[0];
        int oldY = xy[1];

        if (started) {
            if (this == program.getCurrentFunction())
                g.setColor(ACTIVE_FUNCTION_COLOR);
            else
                g.setColor(FUNCTION_COLOR);

            g.fillRect(xy[0], xy[1], WIDTH, HEIGHT);

            g.setColor(Color.BLACK);
            g.drawRect(xy[0], xy[1], WIDTH, HEIGHT);

            g.drawString(toString(), xy[0] + 5, xy[1] + 15);

            if (! getCallees().isEmpty())
                for (Function fonction : getCallees()) {
                    int[] newXY = fonction.draw(g, new int[]{
                            oldX + (WIDTH / 2) + MARGINX,
                            xy[1] + HEIGHT + MARGINY });

                    g.drawLine(oldX + (WIDTH / 2), (int) (xy[1] + (1.5 * HEIGHT) + MARGINY),
                            oldX + (WIDTH / 2) + MARGINX, (int) (xy[1] + (1.5 * HEIGHT) + MARGINY));

                    xy[1] = newXY[1] + MARGINY;

                    if (maxSize[0] < newXY[0]) maxSize[0] = newXY[0];
                    if (maxSize[1] < newXY[1]) maxSize[1] = newXY[1];
                }
            else {
                xy[1] = xy[1] + HEIGHT + MARGINY;
                if (maxSize[1] < xy[1]) maxSize[1] = xy[1];
            }
            g.drawLine(oldX + (WIDTH / 2), oldY + HEIGHT, oldX + (WIDTH / 2), xy[1]);

            if (ended){
                if (this == program.getCurrentFunction())
                    g.setColor(ACTIVE_FUNCTION_COLOR);
                else
                    g.setColor(FUNCTION_COLOR);
                g.fillRect(xy[0], xy[1], WIDTH, HEIGHT);

                g.setColor(Color.BLACK);
                g.drawRect(xy[0], xy[1], WIDTH, HEIGHT);

                maxSize[1] = xy[1] + HEIGHT;
            }
        }

        return maxSize;
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

    public String toString(){
       return getName() + " :: " + getClassName();
    }
}
