package analysis.processor;

import analysis.visitor.MethodVisitor;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

import java.util.Set;

/**
 * TODO --> Adding Comment
 * Created by alxqu on 16/11/2016.
 */
public class MethodProcessor extends AbstractProcessor<CtClass> {

    @Override
    public void process(CtClass ctClass) {
        // Insert correct import for visitor
        //ctClass.insertBefore(null);

        // Retrieve methods for current class
        Set<CtMethod> methods = ctClass.getMethods();

        // For Each methods, insert our visitor
        for (CtMethod method : methods) {
            processMethod(method, ctClass);
        }
    }

    private void processMethod(final CtMethod method, final CtClass ctClass) {
        CtCodeSnippetStatement injectedCode;
        if (method.getSimpleName().equals("main")) {
            injectedCode = getFactory().Code().createCodeSnippetStatement(
                    "Program program = new ProgramImp(Demo1.class.getSimpleName());\n" +
                            "        GraphicsProgram frame = new GraphicsProgram(program, 100);\n" +
                            "        frame.startLoop();"
            );
        } else {
            injectedCode = getFactory().Code().createCodeSnippetStatement(
                    "ProgramImp.getProgram().setCurrentFunction(new FunctionImp(\"" +
                            method.getSimpleName() + "\", " +
                            ctClass.getSimpleName() + ", " +
                            ctClass.getSimpleName() + "));\n" +
                            "ProgramImp.getProgram().getCurrentFunction().startFunction();"
            );
        }

        // Insert at the beginning of the method
        injectedCode.setParent(method.getBody());
        method.getBody().insertBegin(injectedCode);

        injectedCode = getFactory().Code().createCodeSnippetStatement("program.getCurrentFunction().endFunction();");
        // Insert at the end of the method
        method.getBody().insertEnd(injectedCode);
    }
}
