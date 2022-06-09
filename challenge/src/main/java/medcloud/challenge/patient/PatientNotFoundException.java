package medcloud.challenge.patient;

public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException(String email) {
        super("Patient with email " + email + " not found");
    }
}
