package ru.javarush.island.bogdanov.creators;

import ru.javarush.island.bogdanov.constants.Constants;
import ru.javarush.island.bogdanov.field.Cell;
import ru.javarush.island.bogdanov.field.Field;

public class FieldCreator {

    private final CellCreator cellCreator = new CellCreator();

    public FieldCreator() {
    }

    public Field initField() {
        Cell[][] fieldCells = new Cell[Constants.ISLAND_ROWS][Constants.ISLAND_COLUMNS];
        for (int row = 0; row < fieldCells.length; row++) {
            for (int col = 0; col < fieldCells[0].length; col++) {
                fieldCells[row][col] = cellCreator.initCell();
            }
        }
        Field field = new Field(fieldCells);
        for (int row = 0; row < fieldCells.length; row++) {
            for (int col = 0; col < fieldCells[0].length; col++) {
                fieldCells[row][col].findNeighbourCells(field, row, col);
            }
        }
        return field;
    }

}
