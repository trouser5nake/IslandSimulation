package island;

public class Island {
    private final int length;
    private final int width;
    private final Area[][] areas;

    public Island(int length, int width) {
        this.length = length;
        this.width = width;
        this.areas = new Area[length][width];
        System.err.println("Island created with length = " + length + " and width = " + width);
        System.out.println("***********************************************");
    }

    public Area[][] getAreas() {
        return areas;
    }

    public void init() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                areas[i][j] = new Area(new Position(i, j));
            }
        }
    }

    public int getCarnivorousPopulation() {
        int number = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                number += areas[i][j].getCarnivorous().size();
            }
        }
        return number;
    }

    public int getHerbivorousPopulation() {
        int number = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                number += areas[i][j].getHerbivorous().size();
            }
        }
        return number;
    }

    public int getAnimalsPopulation() {
        return getCarnivorousPopulation() + getHerbivorousPopulation();
    }
}

