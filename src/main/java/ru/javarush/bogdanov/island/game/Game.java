package ru.javarush.bogdanov.island.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.javarush.bogdanov.island.field.Field;
import ru.javarush.bogdanov.island.viewer.Viewer;

@Getter
@AllArgsConstructor
public class Game {

    public Viewer viewer;
    public Field field;

}
