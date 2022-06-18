package com.javarush.bogdanov.island.biosphere.animals.predators;

import com.javarush.bogdanov.island.biosphere.animals.Animals;

public abstract class Predators extends Animals {

    public Predators(String name, double maxWeight, int maxPopulationOnCell, int maxSpeed, double maxDiet) {
        super(name, maxWeight, maxPopulationOnCell, maxSpeed, maxDiet);
    }
}
