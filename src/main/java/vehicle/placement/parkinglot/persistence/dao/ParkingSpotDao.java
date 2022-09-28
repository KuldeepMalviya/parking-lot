package vehicle.placement.parkinglot.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.placement.parkinglot.model.VehicleType;
import vehicle.placement.parkinglot.persistence.entity.ParkingSpotEntity;

import java.util.Optional;

public interface ParkingSpotDao extends JpaRepository<ParkingSpotEntity, Long> {

    Optional<ParkingSpotEntity> findFirstByParkingPlaceIdAndVehicleTypeAndIsFree(Long placeId, VehicleType vehicleType, Boolean isFree);

    Optional<ParkingSpotEntity> findByParkingSpotName(String parkingSpotName);

}
