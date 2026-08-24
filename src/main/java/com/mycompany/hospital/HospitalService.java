
package com.mycompany.hospital;
   
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ljsit
 */
public class HospitalService {



    private final ArrayList<Patient> patients;
    private final Patient[][] ward;

    public HospitalService() {
        patients = new ArrayList<>();
        ward = new Patient[4][5];
    }

    public boolean registerPatient(Patient patient) {

        if (patient == null
                || findPatient(patient.getPatientId()) != null) {
            return false;
        }

        patients.add(patient);
        return true;
    }

    public Patient findPatient(String patientId) {

        for (Patient patient : patients) {
            if (patient.getPatientId()
                    .equalsIgnoreCase(patientId)) {
                return patient;
            }
        }

        return null;
    }

    public boolean deletePatient(String patientId) {

        Patient patient = findPatient(patientId);

        if (patient == null) {
            return false;
        }

        releaseBed(patientId);
        patients.remove(patient);

        return true;
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    public boolean allocateBed(String patientId,
                               int row,
                               int column) {

        Patient patient = findPatient(patientId);

        if (!(patient instanceof Inpatient)) {
            return false;
        }

        if (row < 0 || row >= 4|| column < 0 || column >= 5) {
                
            return false;
        }

        if (ward[row][column] != null) {
            return false;
        }

        if (findBedOfPatient(patientId) != null) {
            return false;
        }

        ward[row][column] = patient;

        Inpatient inpatient = (Inpatient) patient;

        inpatient.setWardNumber("Ward 1");
        inpatient.setBedNumber(getBedNumber(row, column));

        return true;
    }

    public boolean releaseBed(String patientId) {

        int[] position = findBedOfPatient(patientId);

        if (position == null) {
            return false;
        }

        Patient patient =  ward[position[0]][position[1]];
              

        ward[position[0]][position[1]] = null;

        if (patient instanceof Inpatient) {

            Inpatient inpatient =(Inpatient) patient;
                    

            inpatient.setBedNumber("Not allocated");
        }

        return true;
    }

    private int[] findBedOfPatient(String patientId) {

        for (int row = 0; row < ward.length; row++) {

            for (int column = 0;
                    column < ward[row].length;
                    column++) {

                if (ward[row][column] != null
                        && ward[row][column]
                        .getPatientId()
                        .equalsIgnoreCase(patientId)) {

                    return new int[]{row, column};
                }
            }
        }

        return null;
    }

    public boolean isBedOccupied(int row, int column) {

        if (row < 0 || row >= 4
                || column < 0 || column >= 5) {
            return false;
        }

        return ward[row][column] != null;
    }

    public String getBedNumber(int row, int column) {

        int number = row * 5 + column + 1;

        return String.format("B%02d", number);
    }

    public int getAvailableBedCount() {

        int count = 0;

        for (int row = 0; row < ward.length; row++) {

            for (int column = 0;
                    column < ward[row].length;
                    column++) {

                if (ward[row][column] == null) {
                    count++;
                }
            }
        }

        return count;
    }

    public int getOccupiedBedCount() {
        return 20 - getAvailableBedCount();
    }

    public double getOccupancyPercentage() {
        return (getOccupiedBedCount() / 20.0) * 100;
    }

    public List<Patient> getPatientsSortedBySurname() {

        ArrayList<Patient> sorted =  new ArrayList<>(patients);
              

        sorted.sort(
                Comparator.comparing(
                        Patient::getLastName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return sorted;
    }

    public List<Patient> getPatientsSortedById() {

        ArrayList<Patient> sorted =  new ArrayList<>(patients);
              

        sorted.sort(
                Comparator.comparing(
                        Patient::getPatientId,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return sorted;
    }
}

