package ru.javarush.island.bogdanov.workers;

import ru.javarush.island.bogdanov.biosphere.Biosphere;
import ru.javarush.island.bogdanov.biosphere.animals.Animals;
import ru.javarush.island.bogdanov.biosphere.plants.Plant;
import ru.javarush.island.bogdanov.field.Cell;

public class Task {

    private final Biosphere organism;
    private final Cell cell;

    public Task(Biosphere organism, Cell cell) {
        this.organism = organism;
        this.cell = cell;
    }

    public void doTask() {
        if (organism instanceof Animals animal) {
            animal.eat(cell);
            animal.move(cell);
        } else if (organism instanceof Plant plant) {
            plant.grow(cell);
        }
        organism.safeMultiple(cell);
    }

}
