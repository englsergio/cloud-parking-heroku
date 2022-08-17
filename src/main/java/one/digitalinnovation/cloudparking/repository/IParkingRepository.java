package one.digitalinnovation.cloudparking.repository;

import one.digitalinnovation.cloudparking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IParkingRepository extends CrudRepository<Parking, Long> {

//    List<Parking> findAll();
//    Optional<Parking> findById(String id);
//    Parking save(Parking parking);
//    void deleteById(String id);

}