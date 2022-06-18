package com.javarush.bogdanov.island.biosphere.animals.predators;

import com.javarush.bogdanov.island.util.Util;

public class Eagle extends Predators {

    public Eagle() {
        super("Орел", 6, 20, 3, 1);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }
}
