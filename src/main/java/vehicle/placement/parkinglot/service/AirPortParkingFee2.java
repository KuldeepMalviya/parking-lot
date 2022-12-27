package vehicle.placement.parkinglot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.config.AirPortFeeStructure2;
import vehicle.placement.parkinglot.model.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Component
@AllArgsConstructor
public class AirPortParkingFee2 implements ParkingFeeStrategy {

    private final AirPortFeeStructure2 airPortFeeStructure2;

    @Override
    public BigDecimal getParkingFeeAmount(VehicleType vehicleType, LocalDateTime inTime, LocalDateTime outTime) {


        return null;
    }
}
