package animals.herbivorous;

import animals.PresetData;

@PresetData(weight = 70, maxSatiety = 15, maxAreaPopulation = 140, speed = 3)
public class Sheep extends Herbivorous {

    @Override
    public String toString() {
        return "Sheep";
    }
}