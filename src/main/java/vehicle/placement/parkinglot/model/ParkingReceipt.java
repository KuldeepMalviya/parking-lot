package vehicle.placement.parkinglot.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ParkingReceipt {
    private Long id;
    private Long parkingRecordId;
    private String ReceiptNumber;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private BigDecimal parkingFee;
}
