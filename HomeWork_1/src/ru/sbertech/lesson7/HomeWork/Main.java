package ru.sbertech.lesson7.HomeWork;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting application...");
        CommonClass.info();
        System.out.println("Loading plugins...");
        PluginManager pm = new PluginManager(System.getProperty("user.dir")+"\\out\\production\\HomeWork_1\\ru\\sbertech\\lesson7\\HomeWork\\Plugins\\");
        for (int i = 0; i< pm.count(); ++i){
            Plugin plugin = pm.getPlugin(i);
            System.out.print(i + ": ");
            plugin.doUseful();
        }
    }

}
