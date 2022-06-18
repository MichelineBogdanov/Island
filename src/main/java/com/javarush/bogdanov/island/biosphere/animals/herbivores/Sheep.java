package com.javarush.bogdanov.island.biosphere.animals.herbivores;

import com.javarush.bogdanov.island.util.Util;

public class Sheep extends Herbivores {

    public Sheep() {
        super("Овца", 70, 140, 3, 15);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }
}
