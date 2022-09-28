package vehicle.placement.parkinglot.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ParkingReceiptDto {
    private String receiptNumber;
    private LocalDateTime entryDateTime;
    private LocalDateTime exitDateTime;
    private BigDecimal fees;
}
