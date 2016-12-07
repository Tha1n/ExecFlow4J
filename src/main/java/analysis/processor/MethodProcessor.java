package analysis.processor;

import analysis.template.MethodVisitorAcceptTemplate;
import analysis.visitor.MethodVisitor;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.template.StatementTemplate;
import spoon.template.Template;
import spoon.template.TemplateParameter;

import java.util.Set;

/**
 * TODO --> Adding Comment
 * Created by alxqu on 16/11/2016.
 */
public class MethodProcessor extends AbstractProcessor<CtClass> {

    /**
     *
     */
    private MethodVisitor m_visitor;

    /**
     * Default constructor of <code>{@link MethodProcessor}</code>
     * @param v A correct initialized object of <code>{@link MethodVisitor}</code>
     */
    public MethodProcessor(MethodVisitor v) {
        m_visitor = v;
    }

    @Override
    public void process(CtClass ctClass) {
        // Retrieve methods for current class
        Set<CtMethod> methods = ctClass.getMethods();

        // For Each methods, insert our visitor
        for (CtMethod method : methods) {
            processMethod(method, m_visitor);
        }
    }

    private void processMethod(final CtMethod method, final MethodVisitor visitor) {
        Template t = new MethodVisitorAcceptTemplate();
        ((MethodVisitorAcceptTemplate) t)._visit_ = new TemplateParameter<MethodVisitor>() {
            @Override
            public MethodVisitor S() {
                return visitor;
            }
        };
        ((MethodVisitorAcceptTemplate) t)._elem_ = new TemplateParameter<CtMethod>() {
            @Override
            public CtMethod S() {
                return method;
            }
        };

        CtClass<?> type = (CtClass<?>) method.getParent(CtClass.class);
        CtStatement injectedCode = (CtStatement) t.apply(type);

        // Insert at the beginning of the method
        method.getBody().insertBegin(injectedCode); //TODO


        // Insert at the end of the method
        //method.getBody().insertEnd(); // TODO
    }
}
