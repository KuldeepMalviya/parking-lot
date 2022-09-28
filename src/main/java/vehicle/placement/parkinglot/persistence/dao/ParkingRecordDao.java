package vehicle.placement.parkinglot.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.placement.parkinglot.persistence.entity.ParkingRecordEntity;

public interface ParkingRecordDao extends JpaRepository<ParkingRecordEntity, Long> {
}
