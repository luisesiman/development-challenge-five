package medcloud.challenge.patient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class PatientConfig {

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            Patient p1 = new Patient("Luis",
                    "luis@gmail.com",
                    LocalDate.of(2000, 10, 20),
                    new Address(
                            "PR", "Irati", "Rua XV", 20
                    ));

            Patient p2 = new Patient("Joao",
                    "joao@gmail.com",
                    LocalDate.of(2003, 02, 10),
                    new Address(
                            "SP", "Campinas", "Rua XVI", 20
                    ));

            patientRepository.saveAll(List.of(p1, p2));
        };
    }
}
