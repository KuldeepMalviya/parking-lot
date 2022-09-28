package vehicle.placement.parkinglot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.placement.parkinglot.model.VehicleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkVehicleRequestDto {
    private Long parkingPlaceId;
    private String vehicleNumber;
    private VehicleType vehicleType;
}
