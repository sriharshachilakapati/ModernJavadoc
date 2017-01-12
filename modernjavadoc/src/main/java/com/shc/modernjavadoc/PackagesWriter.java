package com.shc.modernjavadoc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.RootDoc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sri Harsha Chilakapati
 */
class PackagesWriter extends DocWriter
{
    private Map<String, String> packages;

    PackagesWriter(RootDoc root)
    {
        packages = new HashMap<>();

        for (ClassDoc cls : root.classes())
        {
            String pkgName = cls.qualifiedName().replaceAll("\\." + cls.typeName(), "");
            PackageDoc pkg = root.packageNamed(pkgName);

            packages.put(pkg.name(), pkg.commentText());
        }
    }

    @Override
    protected String getTemplate()
    {
        outFileName = "packages.html";
        velocityContext.put("pkgs", packages);

        return "templates/packages.html.vm";
    }
}
