package ru.javarush.island.bogdanov.workers;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.island.bogdanov.field.Field;
import ru.javarush.island.bogdanov.game.Game;
import ru.javarush.island.bogdanov.viewer.Viewer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class GameWorker implements Runnable {
    private static final int CORE_POOL_SIZE = 4;
    private final Game game;
    private final AtomicInteger DAYS_COUNT = new AtomicInteger(0);

    public GameWorker(Game game) {
        this.game = game;
    }

    //При выполнении задач (скорее всего в методе eat(), а конкретно в safeFindFood()) возникают
    //"сломанные" животные, которые не едят и не худеют, и у которых не устанавливается флаг мертв.
    //(непонятно выполняются ли у них остальные методы)
    //Они просто остаются в списке.
    @Override
    public void run() {
        System.out.println("Day - " + DAYS_COUNT.get());
        Viewer viewer = game.viewer;
        Field field = game.getField();
        viewer.showField();
        viewer.showStatistic();
        //viewer.showForTestStatistic();
        System.out.println("*--------------------------------------------*");
        ScheduledExecutorService mainPool = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
        List<CellWorker> workers = Arrays.stream(field.getField())
                .flatMap(Arrays::stream)
                .map(CellWorker::new)
                .toList();
        int PERIOD = 500;
        mainPool.scheduleWithFixedDelay(() -> {
            try {
                runWorkers(viewer, workers);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, PERIOD, PERIOD, TimeUnit.MILLISECONDS);
    }

    private void runWorkers(Viewer view, List<CellWorker> workers) throws InterruptedException {
        ExecutorService servicePool = Executors.newFixedThreadPool(CORE_POOL_SIZE);
        workers.forEach(servicePool::submit);
        servicePool.shutdown();
        awaitPool(view, servicePool);
    }

    private void awaitPool(Viewer view, ExecutorService servicePool) throws InterruptedException {
        if (servicePool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS)) {
            System.out.println("Day - " + DAYS_COUNT.incrementAndGet());
            view.showField();
            view.showStatistic();
            //view.showForTestStatistic();
            System.out.println("*--------------------------------------------*");
        }
    }

}
