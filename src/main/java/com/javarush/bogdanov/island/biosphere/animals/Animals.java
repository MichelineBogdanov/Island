package com.javarush.bogdanov.island.biosphere.animals;

import com.javarush.bogdanov.island.biosphere.Biosphere;
import com.javarush.bogdanov.island.biosphere.actions.AbleToEat;
import com.javarush.bogdanov.island.biosphere.actions.Fertile;
import com.javarush.bogdanov.island.biosphere.actions.Movable;
import com.javarush.bogdanov.island.field.Cell;

public abstract class Animals extends Biosphere implements AbleToEat, Fertile, Movable {

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


}
