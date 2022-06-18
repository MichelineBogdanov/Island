package com.javarush.bogdanov.island.biosphere;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
public abstract class Biosphere {

    private final static AtomicLong idCounter = new AtomicLong(1);

    // параметры отдельно взятого животного или растения
    private final long id = idCounter.incrementAndGet();
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
}
