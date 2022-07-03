package ru.javarush.bogdanov.island.creators;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.constants.Constants;
import ru.javarush.bogdanov.island.field.Cell;
import ru.javarush.bogdanov.island.util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.javarush.bogdanov.island.util.PrototypeEntityData.getAnimalPrototypeList;

public class CellCreator {

    private final List<Biosphere> listOfPrototypes = getAnimalPrototypeList(Constants.BIOSPHERE_PACKAGE_NAME);

    public Cell initCell() {
        Cell result = new Cell();
        Map<String, Set<Biosphere>> map = result.getCellAnimalCollection();
        for (Biosphere prototype : listOfPrototypes) {
            if (Util.getRandomNumber(100) > 0) {
                Class<?> clazz = prototype.getClass();
                String species = clazz.getSimpleName();
                int countOfAnimals = Util.getRandomNumber(prototype.getMaxPopulationOnCell());
                Set<Biosphere> animalSetOnCell = new HashSet<>();
                for (int i = 0; i < countOfAnimals; i++) {
                    try {
                        Biosphere candidate = prototype.clone();
                        candidate.setGender(Util.getRandomGender());
                        candidate.safeSetWeight(Util.getRandomWeight(candidate.getMaxWeight()));
                        animalSetOnCell.add(candidate);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
                map.put(species, animalSetOnCell);
            }
        }
        return result;
    }

}
