package vehicle.placement.parkinglot.persistence.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.exception.ParkingRecordDoesNotExistsException;
import vehicle.placement.parkinglot.model.ParkingRecord;
import vehicle.placement.parkinglot.persistence.dao.ParkingRecordDao;
import vehicle.placement.parkinglot.persistence.entity.ParkingRecordEntity;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ParkingRecordRepository {

    private final ParkingRecordDao parkingRecordDao;

    public ParkingRecord AddParkingRecord(ParkingRecord parkingRecord) {
        ParkingRecordEntity recordEntity = ParkingRecordEntity.builder()
                .parkingPlaceId(parkingRecord.getParkingPlaceId())
                .parkingPlaceType(parkingRecord.getParkingPlaceType())
                .parkingSpot(parkingRecord.getParkingSpot())
                .inTime(parkingRecord.getInTime())
                .vehicleType(parkingRecord.getVehicleType())
                .vehicleRegistration(parkingRecord.getVehicleRegistration())
                .build();

        return toParkingRecord(parkingRecordDao.save(recordEntity));
    }

    public ParkingRecord updateOutTimeForParkingRecord(Long parkingRecordId) {
        ParkingRecordEntity parkingRecordEntity = parkingRecordDao.findById(parkingRecordId).orElseThrow(() ->
                new ParkingRecordDoesNotExistsException("Parking record/ticket-number does not exits"));
        parkingRecordEntity.setOutTime(LocalDateTime.now());
        ParkingRecordEntity updatedEntity = parkingRecordDao.save(parkingRecordEntity);
        return toParkingRecord(updatedEntity);
    }

    private ParkingRecord toParkingRecord(ParkingRecordEntity parkingRecordEntity) {
        return ParkingRecord.builder()
                .id(parkingRecordEntity.getId())
                .parkingPlaceId(parkingRecordEntity.getParkingPlaceId())
                .parkingPlaceType(parkingRecordEntity.getParkingPlaceType())
                .parkingSpot(parkingRecordEntity.getParkingSpot())
                .vehicleType(parkingRecordEntity.getVehicleType())
                .vehicleRegistration(parkingRecordEntity.getVehicleRegistration())
                .inTime(parkingRecordEntity.getInTime())
                .outTime(parkingRecordEntity.getOutTime())
                .build();
    }
}
