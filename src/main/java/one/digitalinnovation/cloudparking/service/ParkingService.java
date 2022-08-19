package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.dto.ParkingDTO;
import one.digitalinnovation.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        String id = getUUID();
        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
        parkingMap.put(id, parking);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public List<Parking> findAll() {
        List<Parking> parking = parkingMap.values().stream().collect(Collectors.toList());
        return parking;
    }

//    // Injeção de dependência
//    private final IParkingRepository iParkingRepository;
//    public ParkingService(IParkingRepository iParkingRepository) {
//        this.iParkingRepository = iParkingRepository;
//    }
//
//    public Iterable<Parking> findAll() {
//        Iterable<Parking> allParkings = iParkingRepository.findAll();
//        return allParkings;
//    }
//
//    public Optional<Parking> findById(Long id) {
//        return iParkingRepository.findById(id);
//    }
//
//    public Parking create(Parking parking) {
//        return iParkingRepository.save(parking);
//    }
//
//    public Parking updateById(Parking parking, Long id) {
//        return iParkingRepository.save(parking);
//    }
//
//    public void deleteById(Long id) {
//        iParkingRepository.deleteById(id);
//    }
//
//
}
