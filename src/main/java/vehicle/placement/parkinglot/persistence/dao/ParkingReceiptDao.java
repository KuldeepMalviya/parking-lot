package vehicle.placement.parkinglot.persistence.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vehicle.placement.parkinglot.persistence.entity.ParkingReceiptEntity;

public interface ParkingReceiptDao extends JpaRepository<ParkingReceiptEntity, Long> {

  Optional<ParkingReceiptEntity> findByParkingRecordId(Long parkingRecordId);
}
