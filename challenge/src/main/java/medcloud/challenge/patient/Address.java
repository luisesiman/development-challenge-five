package medcloud.challenge.patient;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="address")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    private long id;

    private String state;
    private String city;
    private String street;
    private int number;


    public Address(String state, String city, String street, int number) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
    }

}
