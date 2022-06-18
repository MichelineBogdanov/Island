package com.javarush.bogdanov.island.biosphere.animals.herbivores;

import com.javarush.bogdanov.island.biosphere.animals.Animals;

public abstract class Herbivores extends Animals {

    public Herbivores(String name, double maxWeight, int maxPopulationOnCell, int maxSpeed, double maxDiet) {
        super(name, maxWeight, maxPopulationOnCell, maxSpeed, maxDiet);
    }
}
