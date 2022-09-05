package simulation;

import island.Area;
import plants.Plant;

public class PlantThread implements Runnable {
    private final Area area;

    public PlantThread(Area area) {
        this.area = area;
    }

    @Override
    public void run() {
        grow();
    }

    public void grow() {
        int currentPlantsPopulation = area.getPlants().size();
        int maxPopulation = area.getMaxAreaPopulation(Plant.class);
        int localPlantsPopulation = area.getRandomPopulation(maxPopulation);
        if (currentPlantsPopulation + localPlantsPopulation > maxPopulation) {
            localPlantsPopulation = maxPopulation - currentPlantsPopulation;
        }
        for (int i = 0; i < localPlantsPopulation; i++) {
            area.getPlants().add(new Plant());
        }
    }
}
