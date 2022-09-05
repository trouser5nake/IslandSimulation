package animals.carnivorous;

import animals.Animal;
import animals.PresetData;
import animals.herbivorous.*;

import java.util.Map;

@PresetData(weight = 8, maxSatiety = 2, maxAreaPopulation = 30, speed = 2)
public class Fox extends Carnivorous {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Rabbit.class, 70, Mouse.class, 90, Duck.class, 60, Larva.class, 40);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Fox";
    }
}