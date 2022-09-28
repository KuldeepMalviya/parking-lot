package vehicle.placement.parkinglot.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ParkVehicleResponseDto {

    private Long ticketNumber;
    private String parkingSpotName;
    private LocalDateTime inTime;

}
