package vehicle.placement.parkinglot.config;


import java.math.BigDecimal;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@ConfigurationPropertiesScan
@Configuration
@ConfigurationProperties(prefix = "fee.airport")
@Data
public class AirPortFeeStructure {
    private BigDecimal bike_range_00_01;
    private BigDecimal bike_range_01_08;
    private BigDecimal bike_range_08_24;
    private BigDecimal bike_range_24_EachDay;
    private BigDecimal car_range_00_12;
    private BigDecimal car_range_12_24;
    private BigDecimal car_range_24_EachDay;
}
