package com.javarush.bogdanov.island.biosphere.plants;

import com.javarush.bogdanov.island.biosphere.Biosphere;
import com.javarush.bogdanov.island.field.Cell;

public class Plant extends Biosphere {

    public Plant() {
        super("Растение", 1, 200, 0, 0);
    }

    @Override
    public void multiple(Cell currentCell) {

    }
}
