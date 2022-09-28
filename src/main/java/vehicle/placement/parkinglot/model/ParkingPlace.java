package vehicle.placement.parkinglot.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ParkingPlace {
    private Long id;
    private ParkingPlaceType parkingPlaceType;
    private Long twoWheelerCapacity;
    private Long carOrSuvCapacity;
    private Long busOrTruckCapacity;
}
