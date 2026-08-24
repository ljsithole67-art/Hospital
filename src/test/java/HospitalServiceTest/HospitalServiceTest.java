
package HospitalServiceTest;


import com.mycompany.hospital.HospitalService;
import com.mycompany.hospital.Inpatient;
import com.mycompany.hospital.Patient;
import com.mycompany.hospital.PatientCategory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ljsit
 */
public class HospitalServiceTest {
    
    public HospitalServiceTest() {
    }
    @Test
    public void testRegisterPatient() {

        HospitalService service =
                new HospitalService();

        Patient patient = new Patient("P001",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Flu",
                        PatientCategory.OUTPATIENT
                );
                        
               

        assertTrue(
                service.registerPatient(patient)
        );
    }

    @Test
    public void testDuplicatePatient() {

        HospitalService service =
                new HospitalService();

        Patient patient1 =
                new Patient(
                        "P001",
                        "John",
                        "Smith",
                        25,
                        "Male",
                        "Flu",
                        PatientCategory.OUTPATIENT
                );

        Patient patient2 =
                new Patient(
                        "P001",
                        "Mary",
                        "Jones",
                        30,
                        "Female",
                        "Cold",
                        PatientCategory.EMERGENCY
                );

        service.registerPatient(patient1);

        assertFalse(
                service.registerPatient(patient2)
        );
    }

    @Test
    public void testAllocateBed() {

        HospitalService service =
                new HospitalService();

        Inpatient patient =
                new Inpatient(
                        "P002",
                        "Mary",
                        "Jones",
                        30,
                        "Female",
                        "Flu",
                        "Ward 1",
                        "Not allocated"
                );

        service.registerPatient(patient);

        assertTrue(
                service.allocateBed(
                        "P002",
                        0,
                        0
                )
        );
    }

    @Test
    public void testReleaseBed() {

        HospitalService service =
                new HospitalService();

        Inpatient patient =
                new Inpatient(
                        "P003",
                        "Peter",
                        "Brown",
                        40,
                        "Male",
                        "Test",
                        "Ward 1",
                        "Not allocated"
                );

        service.registerPatient(patient);

        service.allocateBed(
                "P003",
                0,
                0
        );

        assertTrue(
                service.releaseBed("P003")
        );
    }
}