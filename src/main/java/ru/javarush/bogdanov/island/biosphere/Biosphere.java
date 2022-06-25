package ru.javarush.bogdanov.island.biosphere;

import ru.javarush.bogdanov.island.biosphere.actions.Fertile;
import lombok.Getter;
import lombok.Setter;
import ru.javarush.bogdanov.island.biosphere.actions.Growable;
import ru.javarush.bogdanov.island.field.Cell;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public abstract class Biosphere implements Fertile, Growable, Cloneable {

    private final static AtomicInteger idCounter = new AtomicInteger(1);

    // параметры отдельно взятого животного или растения
    private int id = idCounter.incrementAndGet();
    private double weight;
    // true - мужской, false - женский
    private boolean gender;

    // общие параметры для отдельно взятого вида
    private final String name;
    private final double maxWeight;
    private final int maxPopulationOnCell;
    private final int maxSpeed;
    private final double maxDiet;

    public Biosphere(String name, double maxWeight, int maxPopulationOnCell, int maxSpeed, double maxDiet) {
        this.name = name;
        this.maxWeight = maxWeight;
        this.maxPopulationOnCell = maxPopulationOnCell;
        this.maxSpeed = maxSpeed;
        this.maxDiet = maxDiet;
    }

    @Override
    public void multiple(Cell currentCell) {
        Map<String, Set<Biosphere>> cellAnimalCollection = currentCell.getCellAnimalCollection();

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
