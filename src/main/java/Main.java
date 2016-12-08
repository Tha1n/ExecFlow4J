import analysis.processor.MethodProcessor;
import analysis.visitor.MethodVisitor;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.support.StandardEnvironment;

/**
 * Created by steve on 19/10/2016.
 */
public class Main {

    public static void main(String[] args){
        StandardEnvironment env = new StandardEnvironment();
        env.setAutoImports(true);
        env.setComplianceLevel(8);
        env.useTabulations(true);

        SpoonAPI spoon;
        spoon = new Launcher();


        MethodProcessor proc = new MethodProcessor(new MethodVisitor());
        spoon.addProcessor(proc);
        spoon.addInputResource("src/test/resources/java/testmethodprocessor/Sample1.java");
        spoon.run();
    }
}
