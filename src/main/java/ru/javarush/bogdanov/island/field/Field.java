package ru.javarush.bogdanov.island.field;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Field {

    @Getter
    private final Cell[][] field;

    public Field(Cell[][] cells) {
        this.field = cells;
    }

    public Cell getCellFromField(int row, int col){
        return field[row][col];
    }

    public List<String[]> getListOfMaxSpeciesOnCellCount() {
        List<String[]> result = new ArrayList<>();
        for (Cell[] row : field) {
            for (Cell cell : row) {
                result.add(cell.getSpeciesWithMaxCount());
            }
        }
        return result;
    }


}
