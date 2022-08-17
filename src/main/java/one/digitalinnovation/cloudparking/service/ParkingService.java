package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.repository.IParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingService {

    @Autowired
    IParkingRepository iParkingRepository;

    public Iterable<Parking> listAll() {
        Iterable<Parking> allParkings = iParkingRepository.findAll();
        return allParkings;
    }

    public Optional<Parking> findById(Long id) {
        return iParkingRepository.findById(id);
    }

    public Parking create(Parking parking) {
        return iParkingRepository.save(parking);
    }

    public Parking updateById(Parking parking, Long id) {
        return iParkingRepository.save(parking);
    }

    public void deleteById(Long id) {
        iParkingRepository.deleteById(id);
    }


}
