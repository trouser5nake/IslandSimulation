package animals.herbivorous;

import animals.PresetData;

@PresetData(weight = 700, maxSatiety = 100, maxAreaPopulation = 10, speed = 3)
public class Buffalo extends Herbivorous {

    @Override
    public String toString() {
        return "Buffalo";
    }
}