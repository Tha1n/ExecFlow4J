package analysis.processor;

import spoon.processing.AbstractProcessor;
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
        // Retrieve methods for current class
        Set<CtMethod> methods = ctClass.getMethods();

        // Insert method accept in class
        //TODO --> Insert method accept in class

        // For Each methods, insert our visitor
        for (CtMethod method : methods) {
            processMethod(method);
        }
    }

    private void processMethod(CtMethod method) {
        // Insert at the beginning of the method
        //method.getBody().insertBegin(); //TODO

        // Insert at the end of the method
        //method.getBody().insertEnd(); // TODO
    }
}
