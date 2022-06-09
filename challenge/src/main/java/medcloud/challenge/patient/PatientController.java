package medcloud.challenge.patient;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class PatientController {

    private final PatientService patientService;
    private final PatientModelAssembler patientModelAssembler;

    public PatientController(PatientService patientService, PatientModelAssembler patientModelAssembler) {
        this.patientService = patientService;
        this.patientModelAssembler = patientModelAssembler;
    }
    @GetMapping(path = "api/patients")
    public CollectionModel<EntityModel<Patient>> getPatients() {
        List<EntityModel<Patient>> patients = patientService.getPatients().stream()
                .map(patientModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(patients,
                linkTo(methodOn(PatientController.class).getPatients()).withSelfRel());
    }

    @GetMapping(path = "api/patient/{email}")
    public EntityModel<Patient> getPatientByEmail(@PathVariable("email") String email){
        Patient patient = patientService.getPatientByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException(email));
        return patientModelAssembler.toModel(patient);
    }

    @PostMapping(path = "api/patient")
    public ResponseEntity<?> registerPatient(@RequestBody Patient patient) {
        patientService.getPatientByEmail(patient.getEmail()).ifPresent(user -> {
            throw new DuplicateEmailException(patient.getEmail());
        });

        EntityModel<Patient> entityModel = patientModelAssembler.toModel(patientService.addPatient(patient));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}
