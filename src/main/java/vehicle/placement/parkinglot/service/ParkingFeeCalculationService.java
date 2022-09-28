package vehicle.placement.parkinglot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.model.ParkingRecord;

import java.math.BigDecimal;


@Component
@AllArgsConstructor
public class ParkingFeeCalculationService {

    private final FeeStrategyFactory feeStrategyFactory;

    public BigDecimal calculateParkingFee(ParkingRecord parkingRecord) {
        ParkingFeeStrategy parkingFeeStrategy = feeStrategyFactory.getParkingFeeStrategy(parkingRecord.getParkingPlaceType());
        return parkingFeeStrategy
                .getParkingFeeAmount(parkingRecord.getVehicleType(), parkingRecord.getInTime(), parkingRecord.getOutTime());
    }

}
