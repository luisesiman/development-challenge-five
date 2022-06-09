package medcloud.challenge.patient;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PatientModelAssembler implements RepresentationModelAssembler<Patient, EntityModel<Patient>> {

    @Override
    public EntityModel<Patient> toModel(Patient patient) {
        return EntityModel.of(patient,
                linkTo(methodOn(PatientController.class).getPatientByEmail(patient.getEmail())).withSelfRel(),
                linkTo(methodOn(PatientController.class).getPatients()).withRel("api/patients"));
    }
}
