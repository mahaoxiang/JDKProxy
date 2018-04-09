package com.mhx.lang.reflect;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author MHX
 * @date 2018/4/8
 */
public class Proxy {
    public static Object newProxyInstance(Class[] interfaces, InvocationHandler h) throws Exception {
        final String rn = "\r\n";
        String methodStr = "";
        for (Class clazz : interfaces) {
            for (Method method : clazz.getMethods()) {
                methodStr += "    @Override" + rn
                           + "    public void " + method.getName() + "() {" + rn
                           + "        try {" + rn
                           + "            Method method = " + clazz.getName() + ".class.getMethod(\"" + method.getName() + "\");" + rn
                           + "            h.invoke(this, method);" + rn
                           + "        } catch (Throwable e) {" + rn
                           + "            e.printStackTrace();" + rn
                           + "        }" + rn
                           + "    }" + rn;
            }
        }

        String interfaceStrs = Arrays.stream(interfaces).map(clazz -> clazz.getName()).collect(Collectors.toList()).toString().replaceAll("\\[|\\]", "");
        String classStr = "package com.mhx.lang.reflect;" + rn
                        + "import java.lang.reflect.Method;" + rn
                        + "public class $Proxy1 implements " + interfaceStrs + " {" + rn
                        + "    public $Proxy1(InvocationHandler h) {" + rn
                        + "        this.h = h;" + rn
                        + "    }" + rn
                        + "    private com.mhx.lang.reflect.InvocationHandler h;" + rn
                        + methodStr
                        + "}";

        String proxyFilePath = System.getProperty("user.dir") + "/src/com/mhx/lang/reflect/$Proxy1.java";
        File proxyFile = new File(proxyFilePath);

        Files.write(proxyFile.toPath(), classStr.getBytes(StandardCharsets.UTF_8));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(proxyFile);
        CompilationTask t = compiler.getTask(null, fileManager, null, null, null, units);
        t.call();
        fileManager.close();

        URL[] urls = new URL[]{new URL("file:/" + System.getProperty("user.dir") + "/src/")};
        URLClassLoader ul = new URLClassLoader(urls);
        Class c = ul.loadClass("com.mhx.lang.reflect.$Proxy1");

        Constructor ctr = c.getConstructor(InvocationHandler.class);
        return ctr.newInstance(h);
    }
}
