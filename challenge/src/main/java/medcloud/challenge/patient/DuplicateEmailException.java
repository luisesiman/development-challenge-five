package medcloud.challenge.patient;

public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException(String email) {
        super("Email already in use: " + email);
    }
}
