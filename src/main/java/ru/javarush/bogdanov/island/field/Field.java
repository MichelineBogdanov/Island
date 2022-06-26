package ru.javarush.bogdanov.island.field;

import lombok.Getter;
import ru.javarush.bogdanov.island.creators.FieldCreator;

public class Field {

    @Getter
    private final Cell[][] field;
    FieldCreator fieldCreator = new FieldCreator();

    public Field(Cell[][] field) {
        this.field = field;
    }

    public Cell getCellFromField(int row, int col){
        return field[row][col];
    }


}
