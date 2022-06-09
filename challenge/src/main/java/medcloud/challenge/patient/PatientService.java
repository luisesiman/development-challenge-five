package medcloud.challenge.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }

    public Patient addPatient(Patient patient){
        Optional<Patient> patientOptional = patientRepository.findPatientByEmail(patient.getEmail());
        if(patientOptional.isPresent()){
            throw new IllegalStateException("Email already in use!");
        }
        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientByEmail(String email) {
        return patientRepository.findPatientByEmail(email);
    }
}
