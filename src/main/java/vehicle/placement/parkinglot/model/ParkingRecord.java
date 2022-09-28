package vehicle.placement.parkinglot.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ParkingRecord {

    private Long id;
    private Long parkingPlaceId;
    private ParkingPlaceType parkingPlaceType;
    private String parkingSpot;
    private VehicleType vehicleType;
    private String vehicleRegistration;
    private LocalDateTime inTime;
    private LocalDateTime outTime;

}
