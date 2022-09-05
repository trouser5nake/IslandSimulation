package animals.herbivorous;

import animals.AnimalFactory;
import animals.AnimalSpecies;

public class HerbivorousFactory implements AnimalFactory {
    @Override
    public Herbivorous createAnimal(AnimalSpecies species) {
        Herbivorous herbivorous;
        switch (species) {
            case BOAR -> herbivorous = new Boar();
            case BUFFALO -> herbivorous = new Buffalo();
            case LARVA -> herbivorous = new Larva();
            case DEER -> herbivorous = new Deer();
            case DUCK -> herbivorous = new Duck();
            case GOAT -> herbivorous = new Goat();
            case HORSE -> herbivorous = new Horse();
            case MOUSE -> herbivorous = new Mouse();
            case RABBIT -> herbivorous = new Rabbit();
            case SHEEP -> herbivorous = new Sheep();
            default -> herbivorous = null;
        }
        return herbivorous;
    }
}

