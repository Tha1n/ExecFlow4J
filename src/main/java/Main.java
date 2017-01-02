import analysis.processor.MainProcessor;
import analysis.processor.MethodProcessor;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.support.StandardEnvironment;

import java.io.File;

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

        MethodProcessor proc = new MethodProcessor();
        MainProcessor mainProc = new MainProcessor();
        spoon.addProcessor(proc);
        spoon.addProcessor(mainProc);

        addInputResource(spoon, "./src/test");

        spoon.run();
    }

    private static void addInputResource(SpoonAPI spoon, String root) {
        File dir = new File(root);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (child.isDirectory()) {
                    addInputResource(spoon, child.getAbsolutePath());
                } else if (child.isFile() && child.getAbsolutePath().endsWith(".java")) {
                    spoon.addInputResource(child.getAbsolutePath());
            }
        }
    }
    }
}
