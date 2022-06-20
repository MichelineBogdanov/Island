package com.javarush.bogdanov.island.field;

import com.javarush.bogdanov.island.biosphere.Biosphere;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class Cell {

    private Map<String, Set<Biosphere>> cellAnimalCollection;
    private List<Cell> neighbourCell;

    public Cell() {
    }

}
