package ru.sbt.les21_JDBC.HomeWork;

import ru.sbt.les14_JavaUtilConcurrent.HomeWork.IDistance;
import ru.sbt.les14_JavaUtilConcurrent.HomeWork.LevenshteinDistance;

import java.lang.reflect.Proxy;

/**
 * Домашнее задание 21 - кэширующий прокси с сохранением кэша в базе данных
 */
public class Main {
    public static void main(String[] args) throws Exception {
        IDistance levenshteinDistance = new LevenshteinDistance();
        DataBaseCacheHolder cacheHolder = new DataBaseCacheHolder();
        IDistance loggedLD = (IDistance) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                levenshteinDistance.getClass().getInterfaces(),
                new CachedInvocationHandlerImpl(levenshteinDistance, cacheHolder)
        );

        //При первом запуске на пустой базе данных можно выполнить
        //initDistanceCache(loggedLD);

        System.out.println("Расстояние Левенштейна для пар строк:");
        printLevenshteinDistance(loggedLD, "1234567890", "ABCDEF");
        printLevenshteinDistance(loggedLD, "Java", "C#");
        printLevenshteinDistance(loggedLD, "C#", "C++");
        printLevenshteinDistance(loggedLD, "Apple", "Samsung");
        printLevenshteinDistance(loggedLD, "Последний", "элемент");

        //влияет на сохранение последнего элемента
        cacheHolder.close();
    }

    private static void printLevenshteinDistance(IDistance calculator, String str1, String str2){
        System.out.println("[\"" + str1 + "\", \"" + str2 + "\"] = " + calculator.CalculateDistance(str1, str2));
    }

//    private static void initDistanceCache(IDistance calculator){
//        calculator.CalculateDistance("A string", "Another string");
//        calculator.CalculateDistance("empty", "");
//        calculator.CalculateDistance("Java", "JavaScript");
//        calculator.CalculateDistance("C#", "Java");
//        calculator.CalculateDistance("Equals", "Equals");
//        calculator.CalculateDistance("ABCDE", "АВСDЕ");
//        calculator.CalculateDistance("MySQL", "PostgreSQL");
//        calculator.CalculateDistance("public static void Main(String[] args)", "int main(int argc, char *argv[])");
//        calculator.CalculateDistance("NullPointerException", "NullReferenceException");
//        calculator.CalculateDistance("different", " strings");
//    }
}
