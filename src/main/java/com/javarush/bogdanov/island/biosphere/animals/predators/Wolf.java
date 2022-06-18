package com.javarush.bogdanov.island.biosphere.animals.predators;

import com.javarush.bogdanov.island.util.Util;

public class Wolf extends Predators {

    public Wolf() {
        super("Волк", 50, 30, 3, 8);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }
}
