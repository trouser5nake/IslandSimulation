package animals.herbivorous;

import animals.Animal;
import animals.PresetData;

import java.util.Map;

@PresetData(weight = 400, maxSatiety = 50, maxAreaPopulation = 50, speed = 2)
public class Boar extends Herbivorous {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Mouse.class, 50, Larva.class, 80);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Boar";
    }
}