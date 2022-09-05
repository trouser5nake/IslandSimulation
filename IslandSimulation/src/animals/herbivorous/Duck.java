package animals.herbivorous;

import animals.Animal;
import animals.PresetData;

import java.util.Map;

@PresetData(weight = 1, maxSatiety = 0.015, maxAreaPopulation = 200, speed = 4)
public class Duck extends Herbivorous {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Larva.class, 90);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Duck";
    }
}