package simulation;

import island.Island;
import island.Area;

public class AreaThread implements Runnable {
    Area area;
    Island island;

    public AreaThread(Area area, Island island) {
        this.area = area;
        this.island = island;
    }

    @Override
    public void run() {
        area.getLock().lock();
        try {
            area.eat();
            area.reproduce();
            area.move(island.getAreas());
            area.die();
        } finally {
            area.getLock().unlock();
        }
    }
}
