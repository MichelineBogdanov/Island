package ru.javarush.island.bogdanov.biosphere.actions;

import ru.javarush.island.bogdanov.field.Cell;

public interface Movable {

    @SuppressWarnings("UnusedReturnValue")
    boolean move(Cell currentCell);

}
