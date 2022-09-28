package vehicle.placement.parkinglot.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
