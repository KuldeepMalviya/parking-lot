package vehicle.placement.parkinglot.dto;


import lombok.Data;
import vehicle.placement.parkinglot.model.VehicleType;

@Data
public class ParkVehicleRequestDto {
    private Long parkingPlaceId;
    private String vehicleNumber;
    private VehicleType vehicleType;
}
