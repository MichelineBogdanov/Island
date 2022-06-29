package ru.javarush.bogdanov.island.workers;

import ru.javarush.bogdanov.island.field.Field;
import ru.javarush.bogdanov.island.game.Game;
import ru.javarush.bogdanov.island.viewer.Viewer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameWorker implements Runnable {
    public static final int CORE_POOL_SIZE = 8;
    private final Game game;

    public GameWorker(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        Viewer viewer = game.viewer;
        Field field = game.getField();
        viewer.showField();
        System.out.println("init completed!");
        //viewer.showStatistic(field);

        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(CORE_POOL_SIZE);

        List<CellWorker> workers = Arrays.stream(field.getField())
                .flatMap(Arrays::stream)
                .map(CellWorker::new)
                .toList();
        int PERIOD = 500;
        mainPool.scheduleWithFixedDelay(() -> {
            try {
                runWorkers(viewer, workers, field);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, PERIOD, PERIOD, TimeUnit.MILLISECONDS);
    }

    private void runWorkers(Viewer view, List<CellWorker> workers, Field field) throws InterruptedException {
        ExecutorService servicePool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        workers.forEach(servicePool::submit);
        servicePool.shutdown();
        awaitPool(view, servicePool, field);
    }

    private void awaitPool(Viewer view, ExecutorService servicePool, Field field) throws InterruptedException {
        if (servicePool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS)) {
            view.showField();
            //view.showStatistic(field);
        }
    }

}
