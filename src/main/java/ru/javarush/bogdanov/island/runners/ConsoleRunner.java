package ru.javarush.bogdanov.island.runners;

import ru.javarush.bogdanov.island.creators.FieldCreator;
import ru.javarush.bogdanov.island.field.Field;
import ru.javarush.bogdanov.island.game.Game;
import ru.javarush.bogdanov.island.viewer.Viewer;
import ru.javarush.bogdanov.island.workers.GameWorker;

public class ConsoleRunner {
    public static void main(String[] args) {
        FieldCreator fieldCreator = new FieldCreator();
        Field field = fieldCreator.initField();
        Viewer viewer = new Viewer(field);
        Game game = new Game(viewer, field);
        GameWorker gameWorker = new GameWorker(game);
        new Thread(gameWorker).start();

    }
}
