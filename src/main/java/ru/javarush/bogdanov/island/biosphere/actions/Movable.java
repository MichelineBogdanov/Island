package ru.javarush.bogdanov.island.biosphere.actions;

import ru.javarush.bogdanov.island.field.Cell;

public interface Movable {

    @SuppressWarnings("UnusedReturnValue")
    boolean move(Cell currentCell);

}
