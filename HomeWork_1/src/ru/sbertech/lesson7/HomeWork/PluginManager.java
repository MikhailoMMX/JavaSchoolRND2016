package ru.sbertech.lesson7.HomeWork;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginManager {
    private List<Plugin> plugins;
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
        plugins = new ArrayList<>();

        File folder = new File(pluginRootDirectory);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().toLowerCase().endsWith(".jar")) {
                plugins.add(load(listOfFiles[i].getName()));
            }
        }
    }

    public Plugin getPlugin(int index) {
        if (index <0 || index >= plugins.size())
            throw new IndexOutOfBoundsException();
        return plugins.get(index);
    }
    public int count(){
        return plugins.size();
    }

//    упрощенная версия - это весь код загрузки плагина
//    собственный ClassLoader не используется

//    public Plugin load(String pluginFileName) {
//        try {
//            URLClassLoader ul = new URLClassLoader(new URL[]{new URL("file:/" + pluginRootDirectory + pluginFileName)}, ClassLoader.getSystemClassLoader());
//
//            JarFile jarFile = new JarFile(pluginRootDirectory + pluginFileName);
//            Enumeration<JarEntry> jarEntries = jarFile.entries();
//
//            while (jarEntries.hasMoreElements()) {
//                JarEntry jarEntry = jarEntries.nextElement();
//                if (jarEntry.isDirectory())
//                    continue;
//
//                if (jarEntry.getName().endsWith(".class")) {
//                    Class<?> cl = ul.loadClass(jarEntry.getName().substring(0, jarEntry.getName().length()-6).replace('/', '.'));
//                    Class<?> [] interfaces = cl.getInterfaces();
//                    for (int i = 0; i < interfaces.length; ++i)
//                        if (interfaces[i].equals(Plugin.class))
//                            return (Plugin)cl.newInstance();
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//    усложненная версия - см. также MyClassLoader

    public Plugin load(String pluginFileName) {
        String fileName = pluginRootDirectory+pluginFileName;
        MyClassLoader myLoader = new MyClassLoader(fileName, ClassLoader.getSystemClassLoader());
        return myLoader.GetPlugin();
    }

}
