import java.util.*;

class ParkingLot {

    String[] spots;
    int size;

    ParkingLot(int size) {
        this.size = size;
        spots = new String[size];
    }

    int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    public int parkVehicle(String plate) {

        int index = hash(plate);

        int probes = 0;

        while (spots[index] != null) {

            index = (index + 1) % size;
            probes++;
        }

        spots[index] = plate;

        System.out.println("Parked at " + index +
                " probes: " + probes);

        return index;
    }

    public void exitVehicle(String plate) {

        for (int i = 0; i < size; i++) {

            if (plate.equals(spots[i])) {
                spots[i] = null;
                System.out.println("Spot " + i + " freed");
            }
        }
    }
}
