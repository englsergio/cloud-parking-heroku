package one.digitalinnovation.cloudparking.controller;

import one.digitalinnovation.cloudparking.dto.ParkingCreateDTO;
import one.digitalinnovation.cloudparking.dto.ParkingDTO;
import one.digitalinnovation.cloudparking.mapper.ParkingMapper;
import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> parkingDTOList = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(parkingDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        Parking parking = parkingService.findById(id);
        ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(parkingDTO);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        Parking parking = parkingMapper.toParking(dto);
        parkingService.create(parking);
        ParkingDTO parkingDTO = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingDTO);
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
