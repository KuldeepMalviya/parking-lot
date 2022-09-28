package vehicle.placement.parkinglot.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingSpot {
    private Long id;
    private Long parkingPlaceId;
    private ParkingPlaceType parkingPlaceType;
    private VehicleType vehicleType;
    private String parkingSpotName;
    private boolean isFree;
}
