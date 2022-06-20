package com.javarush.bogdanov.island.creators;

import com.javarush.bogdanov.island.biosphere.Biosphere;
import com.javarush.bogdanov.island.field.Cell;
import com.javarush.bogdanov.island.util.PrototypeEntityData;
import com.javarush.bogdanov.island.util.Util;

import java.util.*;

public class CellCreator {

    private final List<Biosphere> listOfPrototypes = PrototypeEntityData.getAnimalPrototypeList("com.javarush.bogdanov.island.biosphere");

    public Cell initCell() {
        Cell result = new Cell();
        result.setCellAnimalCollection(new HashMap<>());
        for (Biosphere prototype : listOfPrototypes) {
            if (Util.getRandomNumber(100) > 50) {
                Class<?> clazz = prototype.getClass();
                String species = clazz.getSimpleName();
                int countOfAnimals = Util.getRandomNumber(prototype.getMaxPopulationOnCell());
                Set<Biosphere> animalSetOnCell = new HashSet<>();
                for (int i = 0; i < countOfAnimals; i++) {
                    try {
                        Biosphere candidate = prototype.clone();
                        candidate.setGender(Util.getRandomGender());
                        candidate.setWeight(Util.getRandomWeight(candidate.getMaxWeight()));
                        animalSetOnCell.add(candidate);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
                result.getCellAnimalCollection().put(species, animalSetOnCell);
            }
        }
        return result;
    }

}
