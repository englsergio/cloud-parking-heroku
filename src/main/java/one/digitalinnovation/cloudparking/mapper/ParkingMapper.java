package one.digitalinnovation.cloudparking.mapper;

import one.digitalinnovation.cloudparking.dto.ParkingDTO;
import one.digitalinnovation.cloudparking.model.Parking;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    //Caso pudesse importar o ModelMapper (problemas repositório maven brad e tal)
//    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
//    public ParkingDTO toParkingDTO(Parking parking) {
//        return MODEL_MAPPER.map(parking, ParkingDTO.class);
//    }
//    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
//        return parkingList.stream()
//                .map(this::toParkingDTO)
//                .collect(Collectors.toList());
//    }

    public ParkingDTO toParkingDTO(Parking parking) {
        ParkingDTO parkingDTO = new ParkingDTO();

        parkingDTO.setId(parking.getId());
        parkingDTO.setState(parking.getState());
        parkingDTO.setBill(parking.getBill());
        parkingDTO.setColor(parking.getColor());
        parkingDTO.setLicense(parking.getLicense());
        parkingDTO.setModel(parking.getModel());
        parkingDTO.setEntryDate(parking.getEntryDate());
        parkingDTO.setExitDate(parking.getExitDate());

        return parkingDTO;
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        List<ParkingDTO> parkingDTOList = new ArrayList<>();
        for (Parking parking : parkingList) {
            ParkingDTO parkingDTO = toParkingDTO(parking);
            parkingDTOList.add(parkingDTO);
        }
    return parkingDTOList;
    }
}
