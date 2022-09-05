package simulation;

import island.Island;
import island.Area;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StartSimulation {
    public static void main(String[] args) {

        Island island = new Island(10, 20);
        island.init();

        ScheduledExecutorService executorInfo = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executorAreaThread = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executorPlantThread = Executors.newScheduledThreadPool(1);

        executorInfo.scheduleWithFixedDelay(new InfoOutput(island), 1, 2, TimeUnit.SECONDS);

        for (int i = 0; i < island.getAreas().length; i++) {
            for (int j = 0; j < island.getAreas()[i].length; j++) {
                Area area = island.getAreas()[i][j];
                executorAreaThread.scheduleWithFixedDelay(new AreaThread(area, island), 2, 1, TimeUnit.SECONDS);
                executorPlantThread.scheduleWithFixedDelay(new PlantThread(area), 3, 3, TimeUnit.SECONDS);
            }
        }

        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            if (island.getAnimalsPopulation() == 0) {
                break;
            }
        }
        executorAreaThread.shutdown();
        executorInfo.shutdown();
        executorPlantThread.shutdown();
        System.out.println("***********************************************");
        System.err.println("No animals. End of Simulation");
    }
}

