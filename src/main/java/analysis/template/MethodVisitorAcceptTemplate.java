package analysis.template;

import analysis.visitor.MethodVisitor;
import spoon.reflect.declaration.CtMethod;
import spoon.template.StatementTemplate;
import spoon.template.TemplateParameter;

/**
 * TODO --> Adding Comment
 * Created by alxqu on 30/11/2016.
 */
public class MethodVisitorAcceptTemplate extends StatementTemplate{

    public TemplateParameter<MethodVisitor> _visit_;
    public TemplateParameter<CtMethod> _elem_;

    @Override
    public void statement() {
        _visit_.S().visit(_elem_.S());
    }
}
