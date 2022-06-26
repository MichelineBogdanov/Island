package ru.javarush.bogdanov.island.biosphere.animals;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.biosphere.actions.AbleToEat;
import ru.javarush.bogdanov.island.biosphere.actions.Movable;
import ru.javarush.bogdanov.island.constants.Constants;
import ru.javarush.bogdanov.island.field.Cell;

import java.util.Arrays;

public abstract class Animals extends Biosphere implements AbleToEat, Movable {

    public Animals(String name, double maxWeight, int maxPopulationOnCell, int maxSpeed, double maxDiet, String icon) {
        super(name, maxWeight, maxPopulationOnCell, maxSpeed, maxDiet, icon);
    }

    @Override
    public void eat(Cell currentCell) {

    }

    @Override
    public Cell move(Cell currentCell) {
        return null;
    }



    protected int getProbabilityOfEating(Biosphere target) {
        int namePositionOfHunter = Arrays.asList(Constants.ANIMAL_NAMES).indexOf(this.getName());
        int namePositionOfTarget = Arrays.asList(Constants.ANIMAL_NAMES).indexOf(target.getName());
        return Constants.CHANCE_TO_EAT[namePositionOfHunter][namePositionOfTarget];
    }
}
