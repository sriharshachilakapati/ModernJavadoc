package com.shc.modernjavadoc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.RootDoc;

/**
 * @author Sri Harsha Chilakapati
 */
public class ModernDoclet extends Doclet
{
    public static boolean start(RootDoc root)
    {
        PackageDoc pkg = null;

        for (ClassDoc klass : root.classes())
        {
            String pkgName = klass.qualifiedName().replaceAll("\\." + klass.typeName(), "");
            PackageDoc pkg1 = root.packageNamed(pkgName);

            if (pkg == null || !pkg.name().equals(pkg1.name()))
            {
                pkg = pkg1;
                System.out.println("Package " + pkg.name());
            }

            System.out.println("  > Class " + klass.simpleTypeName());

            ConstructorDoc[] constructors = klass.constructors();
            for (ConstructorDoc ctor : constructors)
            {
                System.out.println("    > Ctor " + ctor.name());
                System.out.println("      > " + ctor.flatSignature());
            }

            MethodDoc[] methods = klass.methods();
            for (MethodDoc method : methods)
            {
                System.out.println("    > Method " + method.name());
                System.out.println("      > " + method.flatSignature());
            }
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
