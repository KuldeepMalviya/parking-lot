package vehicle.placement.parkinglot.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.model.ParkingPlaceType;

@Component
@AllArgsConstructor
public class FeeStrategyFactory {
    private final AirPortParkingFee airPortParkingFee;
    private final MallParkingFee mallParkingFee;
    private final StadiumParkingFee stadiumParkingFee;
    private final DefaultParkingFee defaultParkingFee;

    public ParkingFeeStrategy getParkingFeeStrategy(ParkingPlaceType parkingPlaceType) {
        if (parkingPlaceType == ParkingPlaceType.MALL) {
            return mallParkingFee;
        }
        if (parkingPlaceType == ParkingPlaceType.STADIUM) {
            return stadiumParkingFee;
        }
        if (parkingPlaceType == ParkingPlaceType.AIRPORT) {
            return airPortParkingFee;
        }
        return defaultParkingFee;
    }
}
