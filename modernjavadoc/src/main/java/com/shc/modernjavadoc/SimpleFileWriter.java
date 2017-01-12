package com.shc.modernjavadoc;

/**
 * @author Sri Harsha Chilakapati
 */
class SimpleFileWriter extends DocWriter
{
    private String templateName;

    SimpleFileWriter(String templateName, String filename)
    {
        this.templateName = templateName;
        outFileName = filename;
    }

    @Override
    protected String getTemplate()
    {
        return templateName;
    }
}
