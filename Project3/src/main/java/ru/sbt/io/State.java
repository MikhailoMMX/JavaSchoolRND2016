package ru.sbt.io;

/**
 * Интерфейс состояния.
 * Позволяет выполнять ввод данных после выбора конкретной команды
 */
interface State {
    void readCommand (Context context);
    String stateName();
}
