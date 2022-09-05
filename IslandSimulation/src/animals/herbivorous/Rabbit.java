package animals.herbivorous;

import animals.PresetData;

@PresetData(weight = 2, maxSatiety = 0.45, maxAreaPopulation = 150, speed = 2)
public class Rabbit extends Herbivorous {

    @Override
    public String toString() {
        return "Rabbit";
    }
}