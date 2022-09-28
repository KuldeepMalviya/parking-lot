package vehicle.placement.parkinglot.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.math.BigDecimal;

@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "fee.stadium")
@Data
public class StadiumFeeStructure {
    private BigDecimal bike_range_00_04;
    private BigDecimal bike_range_04_12;
    private BigDecimal bike_range_12_IN;
    private BigDecimal car_range_00_04;
    private BigDecimal car_range_04_12;
    private BigDecimal car_range_12_IN;
}
