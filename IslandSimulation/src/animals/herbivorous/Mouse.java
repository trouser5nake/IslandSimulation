package animals.herbivorous;

import animals.Animal;
import animals.PresetData;

import java.util.Map;

@PresetData(weight = 0.05, maxSatiety = 0.01, maxAreaPopulation = 500, speed = 1)
public class Mouse extends Herbivorous {
    private static final Map<Class<? extends Animal>, Integer> CHANCE_TO_EAT =
            Map.of(Larva.class, 90);

    @Override
    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return CHANCE_TO_EAT;
    }

    @Override
    public String toString() {
        return "Mouse";
    }


}