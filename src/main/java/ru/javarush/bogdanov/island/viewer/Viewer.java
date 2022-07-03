package ru.javarush.bogdanov.island.viewer;

import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.biosphere.animals.herbivores.Herbivores;
import ru.javarush.bogdanov.island.biosphere.animals.predators.Predators;
import ru.javarush.bogdanov.island.biosphere.plants.Plant;
import ru.javarush.bogdanov.island.constants.Constants;
import ru.javarush.bogdanov.island.field.Cell;
import ru.javarush.bogdanov.island.field.Field;
import ru.javarush.bogdanov.island.util.PrototypeEntityData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Viewer {

    Field field;

    public Viewer(Field field) {
        this.field = field;
    }

    public void showField() {
        List<String[]> listOfMaxSpeciesOnCellCount = field.getListOfMaxSpeciesOnCellCount();
        int count = 0;
        System.out.println("─".repeat(11 * Constants.ISLAND_COLUMNS));
        for (int line = 0; line < Constants.ISLAND_ROWS; line++) {
            for (int i = 0; i < 3; i++) {
                for (int col = 0; col < Constants.ISLAND_COLUMNS; col++) {
                    System.out.printf("│%-10s", listOfMaxSpeciesOnCellCount.get(line + col + count)[i]);
                }
                System.out.print("│");
                System.out.println();
            }
            System.out.println("─".repeat(11 * Constants.ISLAND_COLUMNS));
            count++;
        }
    }

    public void showStatistic() {
        Map<String, Integer> result = new HashMap<>();
        List<Biosphere> listOfPrototypes = field.getField()[0][0].getListOfPrototypes();
        for (Biosphere listOfPrototype : listOfPrototypes) {
            result.put(listOfPrototype.getClass().getSimpleName(), 0);
        }
        Cell[][] cells = field.getField();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                Map<String, Set<Biosphere>> cellAnimalCollection = cell.getCellAnimalCollection();
                for (String s : cellAnimalCollection.keySet()) {
                    int size = cellAnimalCollection.get(s).size();
                    Integer integer = result.get(s);
                    result.put(s, integer + size);
                }
            }
        }
        showSubSpecies(result, Plant.class, listOfPrototypes);
        showSubSpecies(result, Herbivores.class, listOfPrototypes);
        showSubSpecies(result, Predators.class, listOfPrototypes);
    }

    private void showSubSpecies(Map<String, Integer> result, Class<? extends Biosphere> clazz, List<Biosphere> listOfPrototypes) {
        Set<Map.Entry<String, Integer>> entries = result.entrySet();
        System.out.println(clazz.getSimpleName());
        for (Map.Entry<String, Integer> stringIntegerEntry : entries) {
            if (clazz.isAssignableFrom(PrototypeEntityData.getClassByName(stringIntegerEntry.getKey(), listOfPrototypes))) {
                System.out.print(stringIntegerEntry.getKey() + " = " + stringIntegerEntry.getValue() + "│");
            } else if (clazz.getSimpleName().equals(stringIntegerEntry.getKey())) {
                System.out.print(stringIntegerEntry.getKey() + " = " + stringIntegerEntry.getValue() + "│");
            }
        }
        System.out.println();
    }

    public void showForTestStatistic() {
        Cell[][] cells = field.getField();
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                Map<String, Set<Biosphere>> cellAnimalCollection = cell.getCellAnimalCollection();
                for (Map.Entry<String, Set<Biosphere>> stringSetEntry : cellAnimalCollection.entrySet()) {
                    for (Biosphere biosphere : stringSetEntry.getValue()) {
                        System.out.println(biosphere);
                    }
                }
            }
        }
        System.out.println();
    }

}


