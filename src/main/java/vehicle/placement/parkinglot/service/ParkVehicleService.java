package vehicle.placement.parkinglot.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.dto.ParkVehicleRequestDto;
import vehicle.placement.parkinglot.model.ParkingReceipt;
import vehicle.placement.parkinglot.model.ParkingRecord;
import vehicle.placement.parkinglot.model.ParkingSpot;
import vehicle.placement.parkinglot.persistence.repository.ParkingReceiptRepository;
import vehicle.placement.parkinglot.persistence.repository.ParkingRecordRepository;
import vehicle.placement.parkinglot.persistence.repository.ParkingSpotRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParkVehicleService {
    public final ParkingSpotRepository parkingSpotRepository;
    public final ParkingRecordRepository parkingRecordRepository;
    private final ParkingReceiptRepository parkingReceiptRepository;
    private final ParkingFeeCalculationService parkingFeeCalculationService;

    public ParkingRecord parkVehicle(ParkVehicleRequestDto parkVehicleRequestDto) {
        ParkingSpot freeParkingSpot = parkingSpotRepository
                .getFreeParkingSpot(parkVehicleRequestDto.getParkingPlaceId(), parkVehicleRequestDto.getVehicleType());

        var toSaveParkingRecord = ParkingRecord.builder()
                .parkingPlaceId(parkVehicleRequestDto.getParkingPlaceId())
                .parkingSpot(freeParkingSpot.getParkingSpotName())
                .vehicleType(parkVehicleRequestDto.getVehicleType())
                .vehicleRegistration(parkVehicleRequestDto.getVehicleNumber())
                .inTime(LocalDateTime.now())
                .build();

        return parkingRecordRepository.AddParkingRecord(toSaveParkingRecord);
    }

    public ParkingReceipt unParkVehicle(Long parkingRecordId) {
        var parkingRecord = parkingRecordRepository.updateOutTimeForParkingRecord(parkingRecordId);

        parkingSpotRepository.freeParkingSpotByName(parkingRecord.getParkingSpot());

        BigDecimal parkingFee = parkingFeeCalculationService.calculateParkingFee(parkingRecord);

       return parkingReceiptRepository.createParkingReceipt(parkingRecord, parkingFee);
    }

}
