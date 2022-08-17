package one.digitalinnovation.cloudparking.controller;

import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.BreakIterator;
import java.util.List;

@RestController
@RequestMapping("/api/parking/v1")
public class ParkingController {

    ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    public Iterable<Parking> findAll() {
        Iterable<Parking> parkings = parkingService.listAll();
        return parkings;
    }

    @GetMapping("/param")
    public Parking getById(@RequestParam Long id) {
        return parkingService.findById(id).get();
    }

    @PostMapping
    public Parking addParking(@RequestBody Parking parking) {
        return parkingService.create(parking);
    }

    @PutMapping
    public Parking updateParking(@RequestBody Parking parking, Long id) {
        return parkingService.updateById(parking, id);
    }

    @DeleteMapping
    public void deleteParkingById(Long id) {
        parkingService.deleteById(id);
    }
}
