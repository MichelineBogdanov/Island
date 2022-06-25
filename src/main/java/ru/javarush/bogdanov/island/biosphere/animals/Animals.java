package ru.javarush.bogdanov.island.biosphere.animals;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.biosphere.actions.AbleToEat;
import ru.javarush.bogdanov.island.biosphere.actions.Movable;
import ru.javarush.bogdanov.island.field.Cell;

public abstract class Animals extends Biosphere implements AbleToEat, Movable {

    public Animals(String name, double maxWeight, int maxPopulationOnCell, int maxSpeed, double maxDiet) {
        super(name, maxWeight, maxPopulationOnCell, maxSpeed, maxDiet);
    }

    @Override
    public void eat(Cell currentCell) {

    }

    @Override
    public void multiple(Cell currentCell) {

    }

    @Override
    public Cell move(Cell currentCell) {
        return null;
    }

    @Override
    public void grow() {

    }
}
