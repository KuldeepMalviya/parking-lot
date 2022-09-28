package vehicle.placement.parkinglot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.model.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class DefaultParkingFee implements ParkingFeeStrategy {

    @Override
    public BigDecimal getParkingFeeAmount(VehicleType vehicleType, LocalDateTime inTime, LocalDateTime outTime) {
        return BigDecimal.ZERO;
    }
}
