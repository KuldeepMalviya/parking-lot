package vehicle.placement.parkinglot.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.config.StadiumFeeStructure;
import vehicle.placement.parkinglot.model.VehicleType;

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
        if (hours <= 4) {
            return stadiumFeeStructure.getBike_range_00_04();
        }
        if (hours <= 12) {
            return stadiumFeeStructure.getBike_range_00_04()
                .add(stadiumFeeStructure.getBike_range_04_12());
        }

        long overTwelveHours = hours - 12;
        BigDecimal over12HrCharge = stadiumFeeStructure.getBike_range_12_IN()
            .multiply(BigDecimal.valueOf(overTwelveHours));

        return over12HrCharge.add(stadiumFeeStructure.getBike_range_00_04())
            .add(stadiumFeeStructure.getBike_range_04_12());
    }

    private BigDecimal carOrSuvFee(long hours) {
        if (hours <= 4) {
            return (stadiumFeeStructure.getCar_range_00_04());
        }
        if (hours <= 12) {
            return stadiumFeeStructure.getCar_range_00_04()
                .add(stadiumFeeStructure.getCar_range_04_12());
        }

            long overTwelveHours = hours - 12;
            BigDecimal over12HrCharge = stadiumFeeStructure.getCar_range_12_IN()
                    .multiply(BigDecimal.valueOf(overTwelveHours));
        return over12HrCharge.add(stadiumFeeStructure.getCar_range_04_12())
            .add(stadiumFeeStructure.getCar_range_00_04());
    }
}