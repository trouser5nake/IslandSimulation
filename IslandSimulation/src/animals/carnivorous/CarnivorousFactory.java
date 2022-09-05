package animals.carnivorous;

import animals.AnimalFactory;
import animals.AnimalSpecies;

public class CarnivorousFactory implements AnimalFactory {
    @Override
    public Carnivorous createAnimal(AnimalSpecies species) {
        Carnivorous carnivorous;
        switch (species) {
            case BEAR -> carnivorous = new Bear();
            case ANACONDA -> carnivorous = new Anaconda();
            case EAGLE -> carnivorous = new Eagle();
            case FOX -> carnivorous = new Fox();
            case WOLF -> carnivorous = new Wolf();
            default -> carnivorous = null;
        }
        return carnivorous;
    }
}
