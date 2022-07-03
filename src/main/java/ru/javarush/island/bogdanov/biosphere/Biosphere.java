package ru.javarush.island.bogdanov.biosphere;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.island.bogdanov.biosphere.actions.Fertile;
import ru.javarush.island.bogdanov.field.Cell;
import ru.javarush.island.bogdanov.util.Util;

import java.util.Set;
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
                    clone.safeSetWeight(currentCell, childWeight);
                    //System.out.println(clone.name + " родился");
                    currentCell.getCellAnimalCollection().get(type).add(clone);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            currentCell.getLock().unlock();
        }
    }

    public void safeSetWeight(Cell currentCell, double weight) {
        currentCell.getLock().lock();
        try {
            this.weight = weight;
        } finally {
            currentCell.getLock().unlock();
        }
    }

    public void safeSetAlive(Cell currentCell, boolean alive) {
        currentCell.getLock().lock();
        try {
            isAlive = alive;
        } finally {
            currentCell.getLock().unlock();
        }
    }

    protected boolean safeMove(Cell source, Cell destination) {
        if (safeAddTo(destination)) {
            if (safePollFrom(source)) {
                return true;
            } else {
                safePollFrom(destination);
            }
        }
        return false;
    }

    protected boolean safeAddTo(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            Set<Biosphere> biospheres = currentCell
                    .getCellAnimalCollection()
                    .get(this.getClass().getSimpleName());
            int maxCount = getMaxPopulationOnCell();
            int size = biospheres.size();
            return size < maxCount && biospheres.add(this);
        } finally {
            currentCell.getLock().unlock();
        }
    }

    protected boolean safePollFrom(Cell currentCell) {
        currentCell.getLock().lock();
        try {
            Set<Biosphere> biospheres = currentCell
                    .getCellAnimalCollection()
                    .get(this.getClass().getSimpleName());
            return biospheres.remove(this);
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

    @Override
    public String toString() {
        return "Biosphere{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", weight=" + weight +
                ", isAlive=" + isAlive +
                '}';
    }
}
