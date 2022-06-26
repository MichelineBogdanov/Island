package ru.javarush.bogdanov.island.biosphere.plants;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.field.Cell;

public class Plant extends Biosphere {

    public Plant() {
        super("Растение", 1, 200, 0, 0, "\u2F8B");
    }

    @Override
    public void multiple(Cell currentCell) {

    }

    @Override
    public void growAndLostWeight() {

    }
}
