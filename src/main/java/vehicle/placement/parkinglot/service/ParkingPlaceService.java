package vehicle.placement.parkinglot.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.persistence.repository.ParkingPlaceRepository;
import vehicle.placement.parkinglot.persistence.repository.ParkingSpotRepository;
import vehicle.placement.parkinglot.model.ParkingPlace;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ParkingPlaceService {
    private final ParkingPlaceRepository parkingPlaceRepository;

    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingPlace addNewParkingPlace(ParkingPlace parkingPlace) {
        log.info("Received request to create parking place, parkingPlace={}", parkingPlace);

        ParkingPlace newParkingPlace = parkingPlaceRepository.createNewParkingPlace(parkingPlace);

        parkingSpotRepository.initializeParkingSpotsForParkingPlace(newParkingPlace);

        return newParkingPlace;
    }

    public ParkingPlace getParkingPlaceById(Long parkingPlaceId) {
        return parkingPlaceRepository.findParkingPlaceById(parkingPlaceId);
    }

    public List<ParkingPlace> fetAllParkingPlaces() {
        return parkingPlaceRepository.getAllParkingPlaces();
    }

}