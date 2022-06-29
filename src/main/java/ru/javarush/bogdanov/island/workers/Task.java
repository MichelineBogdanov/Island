package ru.javarush.bogdanov.island.workers;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.field.Cell;

public class Task {

    private final Biosphere organism;
    private final Cell cell;

    public Task(Biosphere organism, Cell cell) {
        this.organism = organism;
        this.cell = cell;
    }

    public void doTask() {
        organism.multiple(cell);
    }

}