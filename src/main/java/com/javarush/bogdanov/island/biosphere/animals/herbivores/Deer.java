package com.javarush.bogdanov.island.biosphere.animals.herbivores;

import com.javarush.bogdanov.island.util.Util;

public class Deer extends Herbivores {

    public Deer() {
        super("Олень", 300, 20, 4, 50);
        this.setGender(Util.getRandomGender());
        this.setWeight(Util.getRandomWeight(this.getMaxWeight()));
    }
}
