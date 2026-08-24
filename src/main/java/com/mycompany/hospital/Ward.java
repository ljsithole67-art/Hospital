
package com.mycompany.hospital;
import java.util.ArrayList;

/**
 *
 * @author ljsit
 */
public class Ward {
   

    private String wardName;
    private ArrayList<Bed> beds;

    public Ward(String wardName) {

        this.wardName = wardName;
        beds = new ArrayList<>();
    }

    public String getWardName() {
        return wardName;
    }

    public void addBed(Bed bed) {

        beds.add(bed);
    }

    public ArrayList<Bed> getBeds() {

        return beds;
    }

    public Bed findAvailableBed() {

        for (Bed bed : beds) {

            if (!bed.isOccupied()) {
                return bed;
            }
        }

        return null;
    }

    public Bed findBed(String bedNumber) {

        for (Bed bed : beds) {

            if (bed.getBedNumber()
                    .equalsIgnoreCase(bedNumber)) {

                return bed;
            }
        }

        return null;
    }

    public int getAvailableBedCount() {

        int count = 0;

        for (Bed bed : beds) {

            if (!bed.isOccupied()) {
                count++;
            }
        }

        return count;
    }

    public int getOccupiedBedCount() {

        int count = 0;

        for (Bed bed : beds) {

            if (bed.isOccupied()) {
                count++;
            }
        }

        return count;
    }

    public void displayWardDetails() {

        System.out.println("\nWard: " + wardName);
        System.out.println("--------------------");

        for (Bed bed : beds) {
            System.out.println(bed);
        }
    }
}

