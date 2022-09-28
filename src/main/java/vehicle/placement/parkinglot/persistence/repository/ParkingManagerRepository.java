package vehicle.placement.parkinglot.persistence.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.persistence.dao.ParkingPlaceDao;
import vehicle.placement.parkinglot.persistence.dao.ParkingRecordDao;
import vehicle.placement.parkinglot.persistence.dao.ParkingSpotDao;

@Component
@AllArgsConstructor
public class ParkingManagerRepository {

    private final ParkingPlaceDao parkingPlaceDao;
    private final ParkingRecordDao parkingRecordDao;
    private final ParkingSpotDao parkingSpotDao;




}
