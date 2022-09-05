package animals.carnivorous;

import animals.Animal;
import animals.PresetData;
import animals.herbivorous.*;

import java.util.Map;

@PresetData(weight = 50, maxSatiety = 8, maxAreaPopulation = 30, speed = 3)
public class Wolf extends Carnivorous {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Deer.class, 15, Rabbit.class, 60, Mouse.class, 80, Goat.class, 60,
                    Sheep.class, 70, Boar.class, 15, Buffalo.class, 10, Duck.class, 40);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Wolf";
    }
}