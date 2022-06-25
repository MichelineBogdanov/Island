package ru.javarush.bogdanov.island.creators;

import ru.javarush.bogdanov.island.constants.Constants;
import ru.javarush.bogdanov.island.field.Cell;
import ru.javarush.bogdanov.island.field.Field;

public class FieldCreator {

    private final CellCreator cellCreator = new CellCreator();

    public FieldCreator() {
    }

    public Field initField() {
        Field field = new Field();
        Cell[][] fieldCells = new Cell[Constants.ISLAND_ROWS][Constants.ISLAND_COLUMNS];
        for (int row = 0; row < fieldCells.length; row++) {
            for (int col = 0; col < fieldCells[0].length; col++) {
                fieldCells[row][col] = cellCreator.initCell();
            }
        }
        for (int row = 0; row < fieldCells.length; row++) {
            for (int col = 0; col < fieldCells[0].length; col++) {
                fieldCells[row][col].findNeighbourCells(field, row, col);
            }
        }
        return field;
    }

}
