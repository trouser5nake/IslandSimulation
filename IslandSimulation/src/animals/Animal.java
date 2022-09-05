package animals;

import animals.carnivorous.Carnivorous;
import animals.herbivorous.Herbivorous;
import exceptions.NoSuchAnnotationException;
import island.Direction;
import island.Area;
import plants.Plant;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static island.Direction.*;

@SuppressWarnings("unchecked")
public abstract class Animal {
    private double satiety;
    private Gender gender;
    private boolean isReproduce;

    protected Animal() {
        this.satiety = getMaxSatiety() / 2;
        this.gender = getRandomGender();
        this.isReproduce = false;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isReproduce() {
        return isReproduce;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }

    public void setReproduce(boolean breed) {
        isReproduce = breed;
    }

    public double getSatiety() {
        return this.satiety;
    }

    public abstract boolean reproduce(Area area);

    public Map<Class<? extends Animal>, Integer> getChanceToEat() {
        return Collections.emptyMap();
    }

    public void eat(List<?> objects, Area area) {
        area.getLock().lock();
        try {
            if (!objects.isEmpty()) {
                if (objects.get(0) instanceof Animal) {
                    eatAnimal((List<? extends Animal>) objects);
                } else if (objects.get(0) instanceof Plant) {
                    eatPlant((List<Plant>) objects);
                }
            }
        } finally {
            area.getLock().unlock();
        }
    }

    public boolean move(Area currentArea, Area[][] areas) {
        currentArea.getLock().lock();
        try {
            Area newArea = null;
            int tries = 0;
            while (tries < 4) {
                newArea = changeArea(currentArea, areas);
                if (newArea != null) {
                    break;
                }
                tries++;
            }
            if (newArea != null && newArea != currentArea) {
                newArea.addAnimal(this);
                return true;

            } else {
                return false;
            }
        } finally {
            currentArea.getLock().unlock();
        }
    }

    public boolean die(Area area) {
        area.getLock().lock();
        try {
            this.setSatiety(Math.max(0, this.getSatiety() - this.getMaxSatiety() / 10));
            return this.getSatiety() == 0;
        } finally {
            area.getLock().unlock();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSatiety());
    }

    protected Gender getRandomGender() {
        return Gender.values()[ThreadLocalRandom.current().nextInt(Gender.values().length)];
    }

    protected double getMaxSatiety() {
        return this.getPresetData().maxSatiety();
    }

    protected void eatPlant(List<Plant> plants) {
        Iterator<Plant> iterator = plants.iterator();
        while (iterator.hasNext()) {
            Plant plant = iterator.next();
            if (this.getSatiety() < this.getMaxSatiety()) {
                this.setSatiety(Math.min(this.getSatiety() + plant.getWeight(), this.getMaxSatiety()));
                iterator.remove();
                return;
            }
        }
    }

    private double getWeight() {
        return this.getPresetData().weight();
    }

    private int getMaxAreaPopulation() {
        return this.getPresetData().maxAreaPopulation();
    }

    private int getSpeed() {
        return this.getPresetData().speed();
    }

    private PresetData getPresetData() {
        if (!this.getClass().isAnnotationPresent(PresetData.class)) {
            throw new NoSuchAnnotationException("No such annotation " + PresetData.class + " for " + this.getClass().getName());
        }
        return this.getClass().getAnnotation(PresetData.class);
    }

    private Area changeArea(Area currentArea, Area[][] areas) {
        int previousPositionX = currentArea.getPosition().getX();
        int previousPositionY = currentArea.getPosition().getY();
        int islandLength = areas.length;
        int islandWidth = areas[0].length;
        Direction direction = getDirectionToMove();
        int steps = getDistance(direction, islandLength, islandWidth);
        int newPositionX = getNewPositionX(direction, previousPositionX, steps);
        int newPositionY = getNewPositionY(direction, previousPositionY, steps);
        if (!isAreaExist(newPositionX, newPositionY, islandLength, islandWidth)) {
            return null;
        }
        Area newArea = areas[newPositionX][newPositionY];
        if (!isAreaFree(newArea)) {
            return null;
        }
        return newArea;
    }

    private int getNewPositionX(Direction direction, int previousPositionX, int steps) {
        int nextPositionX = previousPositionX;
        if (direction == UP) {
            nextPositionX = previousPositionX - steps;
        } else if (direction == DOWN) {
            nextPositionX = previousPositionX + steps;
        }
        return nextPositionX;
    }

    private int getNewPositionY(Direction direction, int previousPositionY, int steps) {
        int nextPositionY = previousPositionY;
        if (direction == LEFT) {
            nextPositionY = previousPositionY - steps;
        } else if (direction == RIGHT) {
            nextPositionY = previousPositionY + steps;
        }
        return nextPositionY;
    }

    private boolean isAreaFree(Area area) {
        if (this instanceof Herbivorous) {
            List<Herbivorous> herbivorous = area.getHerbivorous();
            int nextAreaHerbivorousPopulation = (int) herbivorous.stream().filter(this::equals).count();
            return nextAreaHerbivorousPopulation < getMaxAreaPopulation();
        } else if (this instanceof Carnivorous) {
            List<Carnivorous> carnivorous = area.getCarnivorous();
            int nextAreaCarnivorousPopulation = (int) carnivorous.stream().filter(this::equals).count();
            return nextAreaCarnivorousPopulation < getMaxAreaPopulation();
        } else {
            return false;
        }
    }

    private boolean isAreaExist(int nextPositionX, int nextPositionY, int islandLength, int islandWidth) {
        return nextPositionX <= islandLength - 1 && nextPositionY <= islandWidth - 1 && nextPositionX >= 0 && nextPositionY >= 0;
    }

    private Direction getDirectionToMove() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }

    private int getDistance(Direction direction, int islandLength, int islandWidth) {
        int steps = 0;
        int maxDistance = getSpeed();
        if (direction == LEFT || direction == RIGHT) {
            steps = Math.min(islandLength - 1, ThreadLocalRandom.current().nextInt(0, maxDistance));
        } else if (direction == UP || direction == DOWN) {
            steps = Math.min(islandWidth - 1, ThreadLocalRandom.current().nextInt(0, maxDistance));
        }
        return steps;
    }

    private void eatAnimal(List<? extends Animal> animals) {
        Iterator<? extends Animal> iterator = animals.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            Integer chanceToEat = this.getChanceToEat().get(animal.getClass());
            if (chanceToEat != null) {
                int random = ThreadLocalRandom.current().nextInt(1, 100);
                if (chanceToEat >= random && this.getSatiety() < this.getMaxSatiety()) {
                    this.setSatiety(Math.min(this.getSatiety() + animal.getWeight(), this.getMaxSatiety()));
                    iterator.remove();
                    return;
                }
            }
        }
    }
}

