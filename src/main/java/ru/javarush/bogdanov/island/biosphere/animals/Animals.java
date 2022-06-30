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
    public void safeEat(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            findFood(currentCell);
            if (this.getWeight() > this.getMaxWeight() * 0.5) {
                LoseWeight();
            } else {
                die(currentCell);
            }
        } finally {
            currentCell.getLock().unlock();
        }
    }

    private void findFood(Cell currentCell) {

    }

    private void LoseWeight() {
        this.setWeight(this.getMaxWeight() * 0.1);
    }

    private void die(Cell currentCell) {
        //currentCell.getCellAnimalCollection().entrySet().remove(this);
    }

    @Override
    public Cell safeMove(Cell currentCell) {
        return null;
    }


    protected int getProbabilityOfEating(Biosphere target) {
        int namePositionOfHunter = Arrays.asList(Constants.ANIMAL_NAMES).indexOf(this.getName());
        int namePositionOfTarget = Arrays.asList(Constants.ANIMAL_NAMES).indexOf(target.getName());
        return Constants.CHANCE_TO_EAT[namePositionOfHunter][namePositionOfTarget];
    }
}
