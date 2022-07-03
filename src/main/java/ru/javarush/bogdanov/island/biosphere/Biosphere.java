package ru.javarush.bogdanov.island.biosphere;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.bogdanov.island.biosphere.actions.Fertile;
import ru.javarush.bogdanov.island.field.Cell;
import ru.javarush.bogdanov.island.util.Util;

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
                    clone.safeSetWeight(childWeight);
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

    public synchronized void safeSetWeight(double weight) {
        this.weight = weight;
    }

    public synchronized void setAlive(boolean alive) {
        isAlive = alive;
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

    protected boolean safeAddTo(Cell cell) {
        cell.getLock().lock();
        try {
            Set<Biosphere> biospheres = cell
                    .getCellAnimalCollection()
                    .get(this.getClass().getSimpleName());
            int maxCount = getMaxPopulationOnCell();
            int size = biospheres.size();
            return size < maxCount && biospheres.add(this);
        } finally {
            cell.getLock().unlock();
        }
    }

    protected boolean safePollFrom(Cell cell) {
        cell.getLock().lock();
        try {
            Set<Biosphere> biospheres = cell
                    .getCellAnimalCollection()
                    .get(this.getClass().getSimpleName());
            return biospheres.remove(this);
        } finally {
            cell.getLock().unlock();
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
