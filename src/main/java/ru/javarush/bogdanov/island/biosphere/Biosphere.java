package ru.javarush.bogdanov.island.biosphere;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.bogdanov.island.biosphere.actions.Fertile;
import ru.javarush.bogdanov.island.field.Cell;
import ru.javarush.bogdanov.island.util.Util;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public abstract class Biosphere implements Fertile, Cloneable {

    private final static AtomicInteger idCounter = new AtomicInteger(1);

    // параметры отдельно взятого животного или растения
    private int id = idCounter.incrementAndGet();
    private double weight;
    // true - мужской, false - женский
    private boolean gender;
    private boolean isAlive = true;

    // общие параметры для отдельно взятого вида
    private final String name;
    private final double maxWeight;
    private final int maxPopulationOnCell;
    private final int maxSpeed;
    private final double maxDiet;
    private final String icon;

    public Biosphere(String name, double maxWeight, int maxPopulationOnCell, int maxSpeed, double maxDiet, String icon) {
        this.name = name;
        this.maxWeight = maxWeight;
        this.maxPopulationOnCell = maxPopulationOnCell;
        this.maxSpeed = maxSpeed;
        this.maxDiet = maxDiet;
        this.icon = icon;
    }

    @Override
    public void safeMultiple(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            String type = this.getClass().getSimpleName();
            int currentGenderCount = currentCell.getCellSpeciesGenderCollection(type, gender).size();
            boolean neededGenderToMakeChild = !this.isGender();
            int neededGenderCount = currentCell.getCellSpeciesGenderCollection(type, neededGenderToMakeChild).size();
            int chance = Math.min(currentGenderCount, neededGenderCount);
            if (Util.getRandomNumber(100) < chance
                    && this.maxPopulationOnCell > currentGenderCount + neededGenderCount
                    && isAlive()) {
                double childWeight = this.getMaxWeight() / 4;
                try {
                    Biosphere clone = this.clone();
                    clone.setWeight(childWeight);
                    currentCell.getCellAnimalCollection().get(type).add(clone);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            currentCell.getLock().unlock();
        }
    }

    @Override
    public Biosphere clone() throws CloneNotSupportedException {
        Biosphere result = (Biosphere) super.clone();
        result.id = idCounter.incrementAndGet();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biosphere biosphere = (Biosphere) o;
        return id == biosphere.id
                && Double.compare(biosphere.weight, weight) == 0
                && gender == biosphere.gender
                && Double.compare(biosphere.maxWeight, maxWeight) == 0
                && maxPopulationOnCell == biosphere.maxPopulationOnCell
                && maxSpeed == biosphere.maxSpeed
                && Double.compare(biosphere.maxDiet, maxDiet) == 0
                && name.equals(biosphere.name);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
