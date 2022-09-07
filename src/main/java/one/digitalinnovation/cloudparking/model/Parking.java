package one.digitalinnovation.cloudparking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.cloudparking.dto.ParkingCreateDTO;
import one.digitalinnovation.cloudparking.service.ParkingCheckout;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Component
@Entity
public class Parking {

    public Parking(String id, String license, String state, String model, String color) {
        this.id = id;
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
    }
    public Parking(ParkingCreateDTO dto) {
        this.license = dto.getLicense();
        this.state = dto.getState();
        this.model = dto.getModel();
        this.color = dto.getColor();
    }
    private static final Double PRICE_PER_HOUR = 12.5;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String license;
    private String state;
    private String model;
    private String color;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;

}
