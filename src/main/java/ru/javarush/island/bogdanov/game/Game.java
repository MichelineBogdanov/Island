package ru.javarush.island.bogdanov.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.javarush.island.bogdanov.field.Field;
import ru.javarush.island.bogdanov.viewer.Viewer;

@Getter
@AllArgsConstructor
public class Game {

    public Viewer viewer;
    public Field field;

}
