package vehicle.placement.parkinglot.dto;

import lombok.Data;
import vehicle.placement.parkinglot.model.FeePolicyType;
import vehicle.placement.parkinglot.model.ParkingPlaceType;

@Data
public class ParkingPlaceRequestDto {
    private ParkingPlaceType parkingPlaceType;
    private FeePolicyType feePolicyType;
    private Long twoWheelerCapacity;
    private Long CarOrSuvCapacity;
    private Long busTruckCapacity;
}
