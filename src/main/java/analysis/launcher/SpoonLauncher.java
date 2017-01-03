package analysis.launcher;

import analysis.processor.MainProcessor;
import analysis.processor.MethodProcessor;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.support.StandardEnvironment;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * Created by steve on 19/10/2016.
 */
public class SpoonLauncher {

    public static void main(String[] args) {
        spoonProcessing("./src/test", "./src/test/resources/java/SecondFolder/Sample2.java");
    }

    public static void spoonProcessing(String rootFolder, String entryPoint) {
        StandardEnvironment env = new StandardEnvironment();
        env.setAutoImports(true);
        env.setComplianceLevel(8);
        env.useTabulations(true);

        // Main Spoon API is handle here to ensure particular treatment
        SpoonAPI spoon;
        spoon = new Launcher();
        MethodProcessor methProc = new MethodProcessor();
        spoon.addProcessor(methProc);

        MainProcessor mainProc = new MainProcessor(entryPoint);
        spoon.addProcessor(mainProc);

        addInputResource(spoon, rootFolder);
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

                    addImportForFile(child.getAbsolutePath());
                }
            }
        }
    }

    private static void addImportForFile(String absolutePath) {
        try {
            String content = "import core.*;\n" + "import graphic.*;";
            insert(absolutePath, 1, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void insert(String filename, int position, String content) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        ArrayList list = new ArrayList();

        try {
            reader = new BufferedReader(new FileReader(filename));
            String tmp;
            while ((tmp = reader.readLine()) != null)
                list.add(tmp);
            reader.close();

            list.add(position, content);

            writer = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < list.size(); i++)
                writer.write(list.get(i) + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
            writer.close();
        }
    }
}
