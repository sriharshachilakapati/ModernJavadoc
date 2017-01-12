package com.shc.modernjavadoc;

import com.sun.javadoc.DocErrorReporter;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.RootDoc;

import java.io.File;

/**
 * @author Sri Harsha Chilakapati
 */
public class ModernDoclet extends Doclet
{
    public static boolean start(RootDoc root)
    {
        readOptions(root.options());

        new PackagesWriter(root).write();
        new SimpleFileWriter("templates/moderndoc.css.vm", "moderndoc.css").write();

        return true;
    }

    private static void readOptions(String[][] options)
    {
        for (String[] args : options)
        {
            switch (args[0])
            {
                case "-d":
                    Configuration.DEST_DIR = new File(args[1]);
                    break;

                case "-doctitle":
                    Configuration.TITLE = args[1];
                    break;

                case "-windowtitle":
                    Configuration.WINDOW_TITLE = args[1];
                    break;
            }
        }
    }

    public static boolean validOptions(String[][] options, DocErrorReporter reporter)
    {
        String[] required = {
                "-d",          // Destination dir
                "-doctitle",   // Document title
                "-windowtitle" // Window title
        };

        boolean[] found = new boolean[required.length];

        for (String[] args : options)
        {
            for (int i = 0; i < required.length; i++)
            {
                String opt = required[i];

                if (args[0].equals(opt))
                    found[i] = true;
            }
        }

        for (int i = 0; i < found.length; i++)
            if (!found[i])
            {
                reporter.printError("Cannot find option " + required[i]);
                return false;
            }

        return true;
    }

    public static int optionLength(String option)
    {
        switch (option)
        {
            case "-d":
            case "-doctitle":
            case "-windowtitle":
                return 2;
        }

        return 0;
    }
}
