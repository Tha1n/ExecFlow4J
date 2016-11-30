package analysis.processor;

import analysis.template.MethodVisitorAcceptTemplate;
import analysis.visitor.MethodVisitor;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.template.Template;
import spoon.template.TemplateParameter;

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
            processMethod(method, visitor);
        }
    }

    private void processMethod(final CtMethod method, final MethodVisitor visitor) {
        MethodVisitorAcceptTemplate t = new MethodVisitorAcceptTemplate();
        t._visit_ = new TemplateParameter<MethodVisitor>() {
            @Override
            public MethodVisitor S() {
                return visitor; //TODO
            }
        };
        t._elem_ = new TemplateParameter<CtMethod>() {
            @Override
            public CtMethod S() {
                return method;
            }
        };
        CtStatement injectedCode = t.apply();

        // Insert at the beginning of the method
        method.getBody().insertBegin(injectedCode); //TODO


        // Insert at the end of the method
        //method.getBody().insertEnd(); // TODO
    }
}
