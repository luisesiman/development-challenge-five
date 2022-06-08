package medcloud.challenge.patient;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @PostMapping
    public void registerPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
    }

}
