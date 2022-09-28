package vehicle.placement.parkinglot.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.placement.parkinglot.persistence.entity.ParkingPlaceEntity;

public interface ParkingPlaceDao extends JpaRepository<ParkingPlaceEntity, Long> {
}
