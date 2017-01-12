package com.shc.modernjavadoc;

import com.sun.javadoc.RootDoc;

/**
 * @author Sri Harsha Chilakapati
 */
public class PackageWriter extends DocWriter
{
    PackageWriter(String pkgName, RootDoc root)
    {
        velocityContext.put("pkg", root.packageNamed(pkgName));
        outFileName = getLink(pkgName);
    }

    public static String getLink(String pkgName)
    {
        return pkgName.replaceAll("\\.", "/") + "/summary.html";
    }

    @Override
    protected String getTemplate()
    {
        return "templates/package.html.vm";
    }
}
