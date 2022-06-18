package com.javarush.bogdanov.island.biosphere.animals.herbivores;

import com.javarush.bogdanov.island.util.Util;

public class Caterpillar extends Herbivores {

    public Caterpillar() {
        super("Гусеница", 0.01, 1000, 0, 0);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }
}
