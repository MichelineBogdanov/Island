package ru.javarush.bogdanov.island.biosphere.actions;

import ru.javarush.bogdanov.island.field.Cell;

public interface Movable {

    Cell move(Cell currentCell);

}