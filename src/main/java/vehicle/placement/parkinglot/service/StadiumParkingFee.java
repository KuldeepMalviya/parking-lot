package vehicle.placement.parkinglot.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.config.StadiumFeeStructure;
import vehicle.placement.parkinglot.model.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@AllArgsConstructor
public class StadiumParkingFee implements ParkingFeeStrategy {

    private final StadiumFeeStructure stadiumFeeStructure;

    @Override
    public BigDecimal getParkingFeeAmount(VehicleType vehicleType, LocalDateTime inTime, LocalDateTime outTime) {
        long minutes = ChronoUnit.MINUTES.between(inTime, outTime);
        long hours = minutes / 60;

        long remainder = minutes % 60;
        if (remainder != 0) {
            hours = hours + 1;
        }
        return switch (vehicleType) {
            case TWO_WHEELER -> twoWheelerFee(hours);
            case CARS -> carOrSuvFee(hours);
            case BUS -> BigDecimal.ZERO;
        };
    }

    private BigDecimal twoWheelerFee(long hours) {
        BigDecimal parkingFee = BigDecimal.ZERO;
        if (hours <= 4) {
            parkingFee = parkingFee.add(stadiumFeeStructure.getBike_range_00_04());
        }
        if (hours <= 12) {
            parkingFee = parkingFee.add(stadiumFeeStructure.getBike_range_04_12());
        }
        if (hours > 12) {
            long overTwelveHours = hours - 12;
            BigDecimal over12HrCharge = stadiumFeeStructure.getBike_range_12_IN()
                    .multiply(BigDecimal.valueOf(overTwelveHours));
            parkingFee = parkingFee.add(over12HrCharge);
        }
        return parkingFee;
    }

    private BigDecimal carOrSuvFee(long hours) {
        BigDecimal parkingFee = BigDecimal.ZERO;
        if (hours <= 4) {
            parkingFee = parkingFee.add(stadiumFeeStructure.getCar_range_00_04());
        }
        if (hours <= 12) {
            parkingFee = parkingFee.add(stadiumFeeStructure.getCar_range_04_12());
        }
        if (hours > 12) {
            long overTwelveHours = hours - 12;
            BigDecimal over12HrCharge = stadiumFeeStructure.getCar_range_12_IN()
                    .multiply(BigDecimal.valueOf(overTwelveHours));
            parkingFee = parkingFee.add(over12HrCharge);
        }
        return parkingFee;
    }
}