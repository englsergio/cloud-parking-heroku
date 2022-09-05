package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.exception.ParkingNotFoundException;
import one.digitalinnovation.cloudparking.mapper.ParkingMapper;
import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    private final ParkingMapper parkingMapper;
    private final ParkingRepository parkingRepository;
    public ParkingService(ParkingMapper parkingMapper, ParkingRepository parkingRepository) {
        this.parkingMapper = parkingMapper;
        this.parkingRepository = parkingRepository;
    }
    //SIMULANDO UM BANCO DE DADOS
//    private static Map<String, Parking> parkingMap = new HashMap<>();
//
//    static {
//        String id = getUUID();
//        String id2 = getUUID();
//        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
//        Parking parking2 = new Parking(id2, "DMS-2111", "BA", "HB20", "BRANCO");
//        parkingMap.put(id, parking);
//        parkingMap.put(id2, parking2);
//        parking.setEntryDate(LocalDateTime.of(2022, 9, 02, 14, 0, 0));
//        parking2.setEntryDate(LocalDateTime.of(2022, 9, 02, 13, 0, 0));
//    }
//
//    private static String getUUID() {
//        return UUID.randomUUID().toString().replace("-","");
//    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking findById(String id) {
        return parkingRepository.findById(id)
                .orElseThrow(() -> new ParkingNotFoundException(id));
    }
    @Transactional
    public void delete(String id) {
        Optional<Parking> parking = parkingRepository.findById(id);
        if (!parking.isPresent()) throw new ParkingNotFoundException(id);
        parkingRepository.delete(parking.get());
    }
    @Transactional

    public Parking update(Parking update, String id) {
        Parking parking = findById(id);
        Parking parkingUpdated = parkingMapper.parkingUpdate(update, parking);
        parkingRepository.save(parkingUpdated);
        return parkingUpdated;
    }
    @Transactional

    public Parking create(Parking parking) {
//        String id = UUID.randomUUID().toString();
//        parkingCreate.setId(id);
        parking.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parking);
        return parkingRepository.getReferenceById(parking.getId());
    }
    @Transactional

    public Parking checkout(String id) {
        Parking parking = parkingRepository.getReferenceById(id);
        parking.setExitDate(LocalDateTime.now());
        ParkingCheckout.calculateBill(parking);
        return parking;
    }
}