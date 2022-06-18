package com.javarush.bogdanov.island.biosphere.plants;

import com.javarush.bogdanov.island.biosphere.Biosphere;
import com.javarush.bogdanov.island.biosphere.actions.Fertile;
import com.javarush.bogdanov.island.field.Cell;
import com.javarush.bogdanov.island.util.Util;

public class Plant extends Biosphere implements Fertile {

    public Plant() {
        super("Растение", 1, 200, 0, 0);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }

    @Override
    public void multiple(Cell currentCell) {

    }
}
