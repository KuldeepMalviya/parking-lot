package vehicle.placement.parkinglot.persistence.repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.exception.ParkingPlaceDoesNotExistException;
import vehicle.placement.parkinglot.model.ParkingPlace;
import vehicle.placement.parkinglot.persistence.dao.ParkingPlaceDao;
import vehicle.placement.parkinglot.persistence.entity.ParkingPlaceEntity;

import java.util.List;
import java.util.Optional;

import static vehicle.placement.parkinglot.transformer.ParkingPlaceTransformer.toParkingPlace;
import static vehicle.placement.parkinglot.transformer.ParkingPlaceTransformer.toParkingPlaceList;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParkingPlaceRepository {

    private final ParkingPlaceDao parkingPlaceDao;

    public ParkingPlace createNewParkingPlace(ParkingPlace parkingPlace) {
        ParkingPlaceEntity savedEntity = parkingPlaceDao.save(ParkingPlaceEntity.builder()
                .parkingPlaceType(parkingPlace.getParkingPlaceType())
                .feePolicyType(parkingPlace.getFeePolicyType())
                .busOrTrucksCapacity(parkingPlace.getBusOrTruckCapacity())
                .fourWheelerCapacity(parkingPlace.getCarOrSuvCapacity())
                .TwoWheelerCapacity(parkingPlace.getTwoWheelerCapacity())
                .build());
        log.info("Parking place created, parkPlace={}", savedEntity);
        return parkingPlace.toBuilder().id(savedEntity.getId()).build();
    }

    public ParkingPlace findParkingPlaceById(Long parkingPlaceId) {
        Optional<ParkingPlaceEntity> parkingPlaceEntity = parkingPlaceDao.findById(parkingPlaceId);
        if (parkingPlaceEntity.isEmpty()) {
            throw new ParkingPlaceDoesNotExistException("Parking place does not exits for given id=" + parkingPlaceId);
        }
        return toParkingPlace(parkingPlaceEntity.get());
    }

    public List<ParkingPlace> getAllParkingPlaces() {
        var parkingPlaceEntities = parkingPlaceDao.findAll();
        if (parkingPlaceEntities.isEmpty()) {
            throw new ParkingPlaceDoesNotExistException("Parking place does not exits");
        }
        return toParkingPlaceList(parkingPlaceEntities);
    }
}
