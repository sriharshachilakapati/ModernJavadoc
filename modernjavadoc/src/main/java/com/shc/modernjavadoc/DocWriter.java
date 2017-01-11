package com.shc.modernjavadoc;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Sri Harsha Chilakapati
 */
abstract class DocWriter
{
    protected VelocityEngine  velocityEngine;
    protected VelocityContext velocityContext;

    protected String outFileName;

    DocWriter()
    {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        velocityEngine.init();

        velocityContext = new VelocityContext();
        velocityContext.put("windowTitle", Configuration.WINDOW_TITLE);
    }

    protected abstract String getTemplate();

    void write()
    {
        String template = getTemplate();

        File output = new File(Configuration.DEST_DIR, outFileName);
        output.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(output))
        {
            velocityEngine.mergeTemplate(template, "UTF-8", velocityContext, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
