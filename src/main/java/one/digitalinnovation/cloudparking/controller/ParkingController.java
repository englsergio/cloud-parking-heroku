package one.digitalinnovation.cloudparking.controller;

import one.digitalinnovation.cloudparking.dto.ParkingDTO;
import one.digitalinnovation.cloudparking.mapper.ParkingMapper;
import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.service.ParkingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking/v1")
public class ParkingController {

    ParkingService parkingService;
    ParkingMapper parkingMapper;
    public ParkingController(
            ParkingService parkingService,
            ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    public List<ParkingDTO> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> parkingDTOList = parkingMapper.toParkingDTOList(parkingList);
        return parkingDTOList;
    }

//    @GetMapping("/param")
//    public Parking getById(@RequestParam Long id) {
//        return parkingService.findById(id).get();
//    }
//
//    @PostMapping
//    public Parking addParking(@RequestBody Parking parking) {
//        return parkingService.create(parking);
//    }
//
//    @PutMapping
//    public Parking updateParking(@RequestBody Parking parking, Long id) {
//        return parkingService.updateById(parking, id);
//    }
//
//    @DeleteMapping
//    public void deleteParkingById(Long id) {
//        parkingService.deleteById(id);
//    }
}
