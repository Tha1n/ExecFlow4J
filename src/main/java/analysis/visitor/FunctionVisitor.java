package analysis.visitor;

import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;

/**
 * TODO --> Adding Comment
 * Created by alxqu on 16/11/2016.
 */
public class FunctionVisitor implements IVisitor {

    @Override
    public void visit(CtElement ctElement) {
        CtMethod m = (CtMethod) ctElement;
        this.visit(m);
    }

    private void visit(CtMethod ctMethod) {
        //TODO
    }
    
}
