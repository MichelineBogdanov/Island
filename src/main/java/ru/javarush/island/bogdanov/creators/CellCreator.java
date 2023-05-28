package ru.javarush.island.bogdanov.creators;

import ru.javarush.island.bogdanov.biosphere.Biosphere;
import ru.javarush.island.bogdanov.constants.Constants;
import ru.javarush.island.bogdanov.field.Cell;
import ru.javarush.island.bogdanov.util.UtilRandom;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.javarush.island.bogdanov.util.PrototypeEntityData.getAnimalPrototypeList;

public class CellCreator {

    private final List<Biosphere> listOfPrototypes = getAnimalPrototypeList(Constants.BIOSPHERE_PACKAGE_NAME);

    public Cell initCell() {
        Cell result = new Cell();
        Map<String, Set<Biosphere>> map = result.getCellAnimalCollection();
        for (Biosphere prototype : listOfPrototypes) {
            if (UtilRandom.getRandomNumber(100) > 0) {
                Class<?> clazz = prototype.getClass();
                String species = clazz.getSimpleName();
                int countOfAnimals = UtilRandom.getRandomNumber(prototype.getMaxPopulationOnCell());
                Set<Biosphere> animalSetOnCell = new HashSet<>();
                for (int i = 0; i < countOfAnimals; i++) {
                    try {
                        Biosphere candidate = prototype.clone();
                        candidate.setGender(UtilRandom.getRandomGender());
                        candidate.safeSetWeight(result, UtilRandom.getRandomWeight(candidate.getMaxWeight()));
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
