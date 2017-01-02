package analysis.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatementList;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.support.reflect.code.CtStatementListImpl;

import java.util.Set;

/**
 * A Processor to handle main methods
 * Created by alxqu on 02/01/2017.
 */
public class MainProcessor extends AbstractProcessor<CtClass> {

    private final String mainClass;

    public MainProcessor(String mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public void process(CtClass ctClass) {
        if (! this.mainClass.contains(ctClass.getSimpleName())) {
            return;
        }

        // Insert correct import for visitor
        ctClass.insertBefore(getFactory().Code().createCodeSnippetStatement("import core.Program"));
        ctClass.insertBefore(getFactory().Code().createCodeSnippetStatement("import core.GraphicsProgram"));

        // Retrieve methods for current class
        Set<CtMethod> methods = ctClass.getMethods();

        // For Each methods, insert our visitor
        for (CtMethod method : methods) {
            processMethod(method, ctClass);
        }
    }

    private void processMethod(CtMethod method, CtClass ctClass) {
        if (!method.getSimpleName().equals("main")) {
            return;
        }
        CtStatementList injectedCode = new CtStatementListImpl<>();

        injectedCode.addStatement(getFactory().Code().createCodeSnippetStatement("frame.startLoop()"));
        injectedCode.addStatement(getFactory().Code().createCodeSnippetStatement("GraphicsProgram frame = new GraphicsProgram(program, 100)"));
        injectedCode.addStatement(getFactory().Code().createCodeSnippetStatement("Program program = new ProgramImp(Demo1.class.getSimpleName())"));

        // Insert at the beginning of the method
        injectedCode.setParent(method.getBody());
        method.getBody().insertBegin(injectedCode);
    }
}
