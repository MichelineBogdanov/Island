package ru.javarush.bogdanov.island.field;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.bogdanov.island.biosphere.Biosphere;
import ru.javarush.bogdanov.island.biosphere.animals.herbivores.Herbivores;
import ru.javarush.bogdanov.island.biosphere.animals.predators.Predators;
import ru.javarush.bogdanov.island.biosphere.plants.Plant;
import ru.javarush.bogdanov.island.constants.Constants;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static ru.javarush.bogdanov.island.util.PrototypeEntityData.*;

@Getter
@Setter
public class Cell {

    private List<Cell> neighbourCell = new ArrayList<>();
    @Getter
    private Map<String, Set<Biosphere>> cellAnimalCollection = new HashMap<>();
    @Getter
    private final Lock lock = new ReentrantLock(true);
    private final List<Biosphere> listOfPrototypes = getAnimalPrototypeList(Constants.BIOSPHERE_PACKAGE_NAME);

    public Cell() {
    }

    public Set<Biosphere> getCellSpeciesCollection(String type) {
        return this.cellAnimalCollection.get(type);
    }

    public Map<String, Integer> getSpeciesWithMaxCountMap() {
        return this.cellAnimalCollection.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        (entry) -> entry.getValue().size()));
    }

    public String[] getSpeciesWithMaxCount() {
        String[] result = new String[3];
        Map<String, Integer> collect = this.cellAnimalCollection.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        (entry) -> entry.getValue().size()));
        result[0] = getMaxCount(collect, Plant.class);
        result[1] = getMaxCount(collect, Predators.class);
        result[2] = getMaxCount(collect, Herbivores.class);
        return result;
    }

    private String getMaxCount(Map<String, Integer> collect, Class<? extends Biosphere> clazz) {
        String result = "";
        int max = 0;
        String name = "";
        for (Map.Entry<String, Integer> stringIntegerEntry : collect.entrySet()) {
            Class<? extends Biosphere> checkedClass = getClassByName(stringIntegerEntry.getKey());
            if (clazz.isAssignableFrom(checkedClass)) {
                if (stringIntegerEntry.getValue() > max) {
                    max = stringIntegerEntry.getValue();
                    result = getNameByClass(checkedClass) + " " + stringIntegerEntry.getValue();
                }
            } else if (checkedClass.getSimpleName().equals(clazz.getSimpleName())) {
                result = getNameByClass(checkedClass) + " " + max;
            } /*else {
                result = clazz.getSimpleName() + " " + 0;
            }*/
        }
        return result;
    }

    public Set<Biosphere> getCellSpeciesGenderCollection(String type, boolean gender) {
        return this.getCellSpeciesCollection(type)
                .stream()
                .filter(g -> g.isGender() == gender)
                .collect(Collectors.toSet());
    }

    public void findNeighbourCells(Field field, int row, int col) {
        if (row > 0) neighbourCell.add(field.getCellFromField(row - 1, col));
        if (col > 0) neighbourCell.add(field.getCellFromField(row, col - 1));
        if (row < field.getField().length - 1) neighbourCell.add(field.getCellFromField(row + 1, col));
        if (col < field.getField()[0].length - 1) neighbourCell.add(field.getCellFromField(row, col + 1));
    }

    @Override
    public String toString() {
        return "cellAnimalCollection=" + cellAnimalCollection +
                '}';
    }
}
