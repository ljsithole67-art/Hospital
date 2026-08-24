
package com.mycompany.hospital;

/**
 *
 * @author ljsit
 */
public class Inpatient extends Patient{

    private String wardNumber;
    private String bedNumber;

    public Inpatient(String patientId, String firstName, String lastName,
                     int age, String gender, String medicalCondition,
                     String wardNumber, String bedNumber) {

        super(patientId, firstName, lastName, age, gender,
              medicalCondition, PatientCategory.INPATIENT);

        this.wardNumber = wardNumber;
        this.bedNumber = bedNumber;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }
    /**
     * Overridden to extend the behaviour of the superclass and display
     * 
     */
    @Override
    public void displayDetails() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nWard Number: " + wardNumber
                + "\nBed Number: " + bedNumber;
    }
}