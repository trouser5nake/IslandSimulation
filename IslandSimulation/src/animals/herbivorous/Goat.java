package animals.herbivorous;

import animals.PresetData;

@PresetData(weight = 60, maxSatiety = 10, maxAreaPopulation = 140, speed = 3)
public class Goat extends Herbivorous {

    @Override
    public String toString() {
        return "Goat";
    }
}
