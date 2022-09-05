package animals.herbivorous;

import animals.PresetData;
import island.Area;

@PresetData(weight = 0.01, maxSatiety = 0, maxAreaPopulation = 1000)
public class Larva extends Herbivorous {

    @Override
    public boolean move(Area currentArea, Area[][] areas) {
        return false;
    }

    @Override
    public String toString() {
        return "Larva";
    }
}

