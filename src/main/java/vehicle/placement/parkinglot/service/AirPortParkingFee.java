package vehicle.placement.parkinglot.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.config.AirPortFeeStructure;
import vehicle.placement.parkinglot.model.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@AllArgsConstructor
public class AirPortParkingFee implements ParkingFeeStrategy {

    private final AirPortFeeStructure airPortFeeStructure;

    @Override
    public BigDecimal getParkingFeeAmount(VehicleType vehicleType, LocalDateTime inTime, LocalDateTime outTime) {
        long minutes = ChronoUnit.MINUTES.between(inTime, outTime);
        long hours = minutes / 60;
        if (minutes % 60 != 0) {
            hours = hours + 1;
        }
        return switch (vehicleType) {
            case TWO_WHEELER -> twoWheelerFee(hours);
            case CARS -> carOrSuv(hours);
            case BUS -> busOrTrucks(hours);
        };
    }

    private BigDecimal twoWheelerFee(long hours) {
        if (hours <= 1) {
            return airPortFeeStructure.getBike_range_00_01();
        }
        if (hours <= 8) {
            return airPortFeeStructure.getBike_range_01_08();
        }
        if (hours <= 24) {
            return airPortFeeStructure.getBike_range_08_24();
        }
        long days = (hours / 24) + 1;
        return airPortFeeStructure.getBike_range_24_EachDay().multiply(BigDecimal.valueOf(days));
    }

    private BigDecimal carOrSuv(long hours) {
        if (hours <= 12) {
            return airPortFeeStructure.getCar_range_00_12();
        }
        if (hours <= 24) {
            return airPortFeeStructure.getCar_range_12_24();
        }
        long days = (hours / 24) + 1;
        return airPortFeeStructure.getCar_range_24_EachDay().multiply(BigDecimal.valueOf(days));
    }

    private BigDecimal busOrTrucks(long hours) {
        return BigDecimal.ZERO;
    }
}
