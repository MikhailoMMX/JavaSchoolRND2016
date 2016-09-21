package ru.sbt.les21_JDBC.HomeWork;

import java.util.Map;

interface CacheHolder {
    /**
     * Возвращает кэшированные результаты
     * @return кэшированные результаты
     */
    Map<Tuple<String>, Integer> getCache();

    /**
     * Сохраняет новыую пару ключ+значение
     * @param key Ключ, пара строк
     * @param value Значение, расстояние между парой строк ключа
     */
    void putNewValue(Tuple<String> key, Integer value);
}
