package medcloud.challenge.patient;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping(path = "api/patients")
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping(path = "api/patient/{email}")
    public Patient getPatientByEmail(@PathVariable("email") String email){
        Patient patient = patientService.getPatientByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException(email));
        return  patient;
    }

    @PostMapping(path = "api/patient")
    public void registerPatient(@RequestBody Patient patient) {
        patientService.getPatientByEmail(patient.getEmail()).ifPresent(user -> {
            throw new DuplicateEmailException(patient.getEmail());
        });
        patientService.addPatient(patient);
    }

}
