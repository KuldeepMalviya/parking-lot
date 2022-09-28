package vehicle.placement.parkinglot.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.math.BigDecimal;

@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "fee.mall")
@Data
public class MallFeeStructure {
    private BigDecimal bikeOrScooter;
    private BigDecimal carOrSuv;
    private BigDecimal busOrTrucks;
}
