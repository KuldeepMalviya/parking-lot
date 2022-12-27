package vehicle.placement.parkinglot.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.model.FeePolicyType;

@Component
@AllArgsConstructor
public class FeeStrategyFactory {
    private final AirPortParkingFee airPortParkingFee;

    private final AirPortParkingFee2 airPortParkingFee2;
    private final MallParkingFee mallParkingFee;
    private final StadiumParkingFee stadiumParkingFee;
    private final DefaultParkingFee defaultParkingFee;

    public ParkingFeeStrategy getParkingFeeStrategy(FeePolicyType feePolicyType) {
        if (feePolicyType == FeePolicyType.MALL) {
            return mallParkingFee;
        }
        if (feePolicyType == FeePolicyType.STADIUM) {
            return stadiumParkingFee;
        }
        if (feePolicyType == FeePolicyType.AIRPORT_1) {
            return airPortParkingFee;
        }
        if (feePolicyType == FeePolicyType.AIRPORT_2) {
            return airPortParkingFee2;
        }
        return defaultParkingFee;
    }
}
