package simulation;

import animals.AnimalSpecies;
import animals.herbivorous.Herbivorous;
import animals.carnivorous.Carnivorous;
import island.Island;
import island.Area;

import java.util.ArrayList;
import java.util.List;

public class InfoOutput implements Runnable {
    private final Island island;
    private final Area[][] areas;

    public InfoOutput(Island island) {
        this.island = island;
        this.areas = island.getAreas();
    }

    @Override
    public void run() {
        printInfo();
    }

    public void printInfo() {

        System.out.println("Carnivorous population: " + island.getCarnivorousPopulation());
        System.out.println("Herbivorous population: " + island.getHerbivorousPopulation());
        System.out.println("Animals in total: " + island.getAnimalsPopulation());
        System.out.println("***********************************************");
        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas[i].length; j++) {
                Area area = areas[i][j];
                System.out.println("Area [" + area.getPosition().getX() + ", " + area.getPosition().getY() + "]");
                System.out.println(" - Carnivorous:");
                for (AnimalSpecies species : AnimalSpecies.values()) {
                    List<Carnivorous> carnivorous = new ArrayList<>(area.getCarnivorous());
                    int carnivorousNumber = (int) carnivorous.stream().filter(c -> c.toString().equalsIgnoreCase(species.toString())).count();
                    if (carnivorousNumber > 0) {
                        System.out.println("   - " + species + " : " + carnivorousNumber);
                    }
                }
                System.out.println(" - Herbivorous:");
                for (AnimalSpecies species : AnimalSpecies.values()) {
                    List<Herbivorous> herbivorous = new ArrayList<>(area.getHerbivorous());
                    int herbivorousNumber = (int) herbivorous.stream().filter(h -> h.toString().equalsIgnoreCase(species.toString())).count();
                    if (herbivorousNumber > 0) {
                        System.out.println("   - " + species + " : " + herbivorousNumber);
                    }
                }
                System.out.println(" - Plants : " + area.getPlants().size());
            }
        }
    }
}
