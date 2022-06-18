package com.javarush.bogdanov.island.biosphere.actions;

import com.javarush.bogdanov.island.field.Cell;

public interface Movable {

    Cell move(Cell currentCell);

}
