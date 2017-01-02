package analysis.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtStatementList;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.reflect.code.CtStatementListImpl;

import java.util.Set;

/**
 * TODO --> Adding Comment
 * Created by alxqu on 16/11/2016.
 */
public class MethodProcessor extends AbstractProcessor<CtClass> {

    @Override
    public void process(CtClass ctClass) {
        // Insert correct import for visitor TODO
        //ctClass.insertBefore(null);

        // Retrieve methods for current class
        Set<CtMethod> methods = ctClass.getMethods();

        // For Each methods, insert our visitor
        for (CtMethod method : methods) {
            processMethod(method, ctClass);
        }
    }

    private void processMethod(final CtMethod method, final CtClass ctClass) {
        CtStatementList injectedCode = new CtStatementListImpl<>();
        // Statements are addded in reverse order
        if (method.getSimpleName().equals("main")) {
            injectedCode.addStatement(getFactory().Code().createCodeSnippetStatement("frame.startLoop()"));
            injectedCode.addStatement(getFactory().Code().createCodeSnippetStatement("GraphicsProgram frame = new GraphicsProgram(program, 100)"));
            injectedCode.addStatement(getFactory().Code().createCodeSnippetStatement("Program program = new ProgramImp(Demo1.class.getSimpleName())"));
        } else {
            injectedCode.addStatement(getFactory().Code().createCodeSnippetStatement("ProgramImp.getProgram().getCurrentFunction().startFunction()"));
            injectedCode.addStatement(getFactory().Code().createCodeSnippetStatement(
                    "ProgramImp.getProgram().setCurrentFunction(new FunctionImp(\"" +
                            method.getSimpleName() + "\", " +
                            ctClass.getSimpleName() + ", " +
                            ctClass.getSimpleName() + "))"));
        }

        // Insert at the beginning of the method
        injectedCode.setParent(method.getBody());
        method.getBody().insertBegin(injectedCode);

        // Insert at the end of the method
        // If method body is empty or if it's a void method, insert after
        if (method.getBody().getStatements().size() == 0 || method.getSignature().substring(0, 4).equals("void")) {
            method.getBody().getLastStatement().insertAfter(getFactory().Code().createCodeSnippetStatement("ProgramImp.getProgram().getCurrentFunction().endFunction()"));
        } else {
            method.getBody().getLastStatement().insertBefore(getFactory().Code().createCodeSnippetStatement("ProgramImp.getProgram().getCurrentFunction().endFunction()"));
        }
    }
}
