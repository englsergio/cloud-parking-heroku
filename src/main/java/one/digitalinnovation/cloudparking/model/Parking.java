package one.digitalinnovation.cloudparking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Component
@Entity
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String license;
    @Column
    private String state;
    @Column
    private String model;
    @Column
    private String color;
    @Column
    private LocalDateTime entryDate;
    @Column
    private LocalDateTime exitDate;
    @Column
    private Double bill;

}
