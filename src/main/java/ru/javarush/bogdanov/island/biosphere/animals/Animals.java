package ru.javarush.bogdanov.island.biosphere.animals;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.biosphere.actions.AbleToEat;
import ru.javarush.bogdanov.island.biosphere.actions.Movable;
import ru.javarush.bogdanov.island.constants.Constants;
import ru.javarush.bogdanov.island.field.Cell;

import java.util.ArrayList;
import java.util.List;

public abstract class Animals extends Biosphere implements AbleToEat, Movable {

    public Animals(String name, double maxWeight, int maxPopulationOnCell, int maxSpeed, double maxDiet, String icon) {
        super(name, maxWeight, maxPopulationOnCell, maxSpeed, maxDiet, icon);
    }

    @Override
    public void safeEat(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            double eatenFood = 0;
            while (eatenFood < this.getMaxDiet()) {
                if (findFood(currentCell) != 0) {
                    eatenFood += findFood(currentCell);
                } else {
                    break;
                }
            }
            if (this.getWeight() > this.getMaxWeight() / 5) {
                loseWeight();
            } else {
                die(currentCell);
            }
        } finally {
            currentCell.getLock().unlock();
        }
    }

    private double findFood(Cell currentCell) {
        return 0;
    }

    private void loseWeight() {
        this.setWeight(this.getMaxWeight() * 0.1);
    }

    private void die(Cell currentCell) {
        //currentCell.getCellAnimalCollection().entrySet().remove(this);
    }

    @Override
    public Cell safeMove(Cell currentCell) {
        return null;
    }

    public List<Integer> getChanceRateAnimalTarget() {
        List<Integer> result = new ArrayList<>();

        int position = Constants.ANIMAL_NAMES.indexOf(this.getName());
        int[] foodMap = Constants.CHANCE_TO_EAT[position];
        /*for (int i = 0; i < foodMap.length; i++) {

        }*/
        return result;
    }

}
