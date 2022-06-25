package ru.javarush.bogdanov.island.field;

import lombok.Getter;
import ru.javarush.bogdanov.island.creators.FieldCreator;

public class Field {

    @Getter
    Cell[][] field;
    FieldCreator fieldCreator = new FieldCreator();

    public Cell getCellFromField(int row, int col){
        return field[row][col];
    }


}
