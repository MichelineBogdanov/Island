package com.javarush.bogdanov.island.biosphere.animals.predators;

import com.javarush.bogdanov.island.util.Util;

public class Snake extends Predators {

    public Snake() {
        super("Удав", 15, 30, 1, 3);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }
}
