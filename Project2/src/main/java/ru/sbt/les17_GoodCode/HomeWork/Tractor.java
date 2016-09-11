package ru.sbt.les17_GoodCode.HomeWork;

public class Tractor {
    private int positionX;
    private int positionY;
    private Orientation orientation = Orientation.NORTH;

    //откуда берутся размеры поля? Можно куда-то вынести
    private final int fieldWidth = 5;
    private final int fieldHeight = 5;

    //откуда берется команда? Можно сделать enum или именованные константы
    public void move(String command) {
        if (command.equals("F")) {
            moveForwards();
        } else if (command.equals("T")) {
            turnClockwise();
        }
    }

    public void moveForwards() {
        switch (orientation) {
            case NORTH  :  positionY += 1;  break;
            case EAST   :  positionX += 1;  break;
            case SOUTH  :  positionY -= 1;  break;
            case WEST   :  positionX -= 1;  break;
        }

        if (    positionX < 0
             || positionY < 0
             || positionX > fieldWidth
             || positionY > fieldHeight
           )
        {
            throw new TractorInDitchException();
        }
    }

    public void turnClockwise() {
        switch (orientation) {
            case NORTH  :  orientation = Orientation.EAST;   break;
            case EAST   :  orientation = Orientation.SOUTH;  break;
            case SOUTH  :  orientation = Orientation.WEST;   break;
            case WEST   :  orientation = Orientation.NORTH;  break;
        }
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}

// в отрыве от Main'а тяжело рефакторить дальше