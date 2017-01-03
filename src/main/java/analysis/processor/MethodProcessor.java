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
 * A Processor for all methods of a class
 * Created by alxqu on 16/11/2016.
 */
public class MethodProcessor extends AbstractProcessor<CtClass> {

    @Override
    public void process(CtClass ctClass) {
        // Insert correct import for visitor
        ctClass.insertBefore(getFactory().Code().createCodeSnippetStatement("import core.Program"));

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
        injectedCode.addStatement(getFactory().Code().createCodeSnippetStatement("ProgramImp.getProgram(\"prg\").getCurrentFunction().startFunction()"));
        injectedCode.addStatement(getFactory().Code().createCodeSnippetStatement(
                "ProgramImp.getProgram(\"prg\").setCurrentFunction(new FunctionImp(\"" +
                        method.getSimpleName() + "\", " +
                        ctClass.getSimpleName() + ".class.getSimpleName(), \"prg\"))"));

        // Insert at the beginning of the method
        injectedCode.setParent(method.getBody());
        method.getBody().insertBegin(injectedCode);

        // Insert at the end of the method
        // If method body is empty or if it's a void method, insert after
        if (method.getBody().getStatements().size() == 0 || method.getSignature().substring(0, 4).equals("void")) {
            method.getBody().getLastStatement().insertAfter(getFactory().Code().createCodeSnippetStatement("ProgramImp.getProgram(\"prg\").getCurrentFunction().endFunction()"));
        } else {
            method.getBody().getLastStatement().insertBefore(getFactory().Code().createCodeSnippetStatement("ProgramImp.getProgram(\"prg\").getCurrentFunction().endFunction()"));
        }
    }
}
