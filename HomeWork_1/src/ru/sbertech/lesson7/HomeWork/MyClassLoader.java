package ru.sbertech.lesson7.HomeWork;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MyClassLoader extends ClassLoader {
    private ClassLoader parent;
    private Map<String, Class<?>> cacheClass = new HashMap<>();
    private String jarFileName;
    private HashSet<String> definedClasses = new HashSet<>();

    public MyClassLoader(String jarFileName, ClassLoader parent) {
        this.parent = parent;
        this.jarFileName = jarFileName;
        fillDefinedClasses();
    }

    private void fillDefinedClasses(){
        try {
            JarFile jarFile = new JarFile(jarFileName);
            Enumeration<JarEntry> jarEntries = jarFile.entries();

            while (jarEntries.hasMoreElements()) {
                JarEntry jarEntry = jarEntries.nextElement();
                if (jarEntry.isDirectory())
                    continue;

                if (jarEntry.getName().endsWith(".class")) {
                    String clName = jarEntry.getName().replace('/', '.').substring(0,jarEntry.getName().length() - 6);
                    definedClasses.add(clName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> result = null;
        if (name.equals(Plugin.class.getName()))
            return parent.loadClass(name);
        else
            result = cacheClass.get(name);

        if (result == null && definedClasses.contains(name)) {
            try {
                JarFile jarFile = new JarFile(jarFileName);
                Enumeration<JarEntry> jarEntries = jarFile.entries();

                while (jarEntries.hasMoreElements()) {
                    JarEntry jarEntry = jarEntries.nextElement();
                    if (jarEntry.isDirectory())
                        continue;

                    if (jarEntry.getName().endsWith(".class")) {
                        String clName = jarEntry.getName().replace('/', '.').substring(0,jarEntry.getName().length() - 6);

                        if (!clName.equals(name))
                            continue;

                        byte[] classData = loadClassData(jarFile, jarEntry);
                        if (classData != null) {
                            Class<?> cl = defineClass(clName, classData, 0, classData.length);
                            cacheClass.put(cl.getName(), cl);
                            result = cl;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (result == null)
            result = parent.loadClass(name);
        return result;
    }

    public Plugin GetPlugin() {
        try {
            Iterator<String> it = definedClasses.iterator();
            while (it.hasNext()){
                String clName = it.next();
                Class<?> cl = loadClass(clName);

                Class<?>[] interfaces = cl.getInterfaces();
                for (int i=0; i< interfaces.length; ++i)
                    if (interfaces[i].equals(Plugin.class))
                        return (Plugin) cl.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] loadClassData(JarFile jarFile, JarEntry jarEntry) throws IOException {
        long size = jarEntry.getSize();
        if (size <= 0)
            return null;
        else if (size > Integer.MAX_VALUE) {
            throw new IOException("Class size too long");
        }

        byte[] buffer = new byte[(int) size];
        InputStream is = jarFile.getInputStream(jarEntry);
        is.read(buffer);

        return buffer;
    }

}
