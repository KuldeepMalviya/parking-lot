package vehicle.placement.parkinglot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.config.MallFeeStructure;
import vehicle.placement.parkinglot.model.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Component
@AllArgsConstructor
public class MallParkingFee implements ParkingFeeStrategy {

    private final MallFeeStructure mallFeeStructure;

    public BigDecimal getParkingFeeAmount(VehicleType vehicleType, LocalDateTime inTime, LocalDateTime outTime) {
        long minutes = ChronoUnit.MINUTES.between(inTime, outTime);
        long hours = minutes / 60;
        long remainder = minutes % 60;
        if (remainder != 0) {
            hours = hours + 1;
        }
        return switch (vehicleType) {
            case TWO_WHEELER -> mallFeeStructure.getBikeOrScooter().multiply(BigDecimal.valueOf(hours));
            case CARS -> mallFeeStructure.getCarOrSuv().multiply(BigDecimal.valueOf(hours));
            case BUS -> mallFeeStructure.getBusOrTrucks().multiply(BigDecimal.valueOf(hours));
        };
    }
}
