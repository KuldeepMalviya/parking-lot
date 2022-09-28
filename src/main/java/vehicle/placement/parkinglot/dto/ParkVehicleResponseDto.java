package vehicle.placement.parkinglot.dto;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkVehicleResponseDto {
    private Long ticketNumber;
    private String parkingSpotName;
    private LocalDateTime inTime;
}
