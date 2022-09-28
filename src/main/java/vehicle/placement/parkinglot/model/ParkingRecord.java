package vehicle.placement.parkinglot.model;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

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
