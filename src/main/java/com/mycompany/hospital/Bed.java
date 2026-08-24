
package com.mycompany.hospital;

/**
 *
 * @author ljsit
 * Represents the hospital ward containing 20 beds arranged in a 4 x 5
 */
public class Bed {
  
    private String bedNumber;
    private boolean occupied;
    private Patient patient;

    public Bed(String bedNumber) {
        this.bedNumber = bedNumber;
        this.occupied = false;
        this.patient = null;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Patient getPatient() {
        return patient;
    }

    public boolean assignPatient(Patient patient) {

        if (occupied) {
            return false;
        }

        this.patient = patient;
        this.occupied = true;

        return true;
    }

    public void removePatient() {

        patient = null;
        occupied = false;
    }

    public void displayBedDetails() {

        System.out.println("Bed Number: " + bedNumber);

        if (occupied) {
            System.out.println("Status: Occupied");
            System.out.println("Patient: "
                    + patient.getFirstName()
                    + " "
                    + patient.getLastName());
        } else {
            System.out.println("Status: Available");
        }
    }

    @Override
    public String toString() {

        if (occupied) {
            return bedNumber + " - Occupied by "
                    + patient.getFirstName()
                    + " "
                    + patient.getLastName();
        }

        return bedNumber + " - Available";
    }
}

