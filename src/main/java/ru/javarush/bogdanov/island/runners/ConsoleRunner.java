package ru.javarush.bogdanov.island.runners;

import ru.javarush.bogdanov.island.game.Game;
import ru.javarush.bogdanov.island.workers.GameWorker;

public class ConsoleRunner {
    public static void main(String[] args) {
        Game game = new Game();
        GameWorker gameWorker = new GameWorker(game);
        new Thread(gameWorker).start();

    }
}
