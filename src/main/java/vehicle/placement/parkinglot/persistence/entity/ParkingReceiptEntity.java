package vehicle.placement.parkinglot.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PARKING_RECEIPT")
public class ParkingReceiptEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PARKING_RECORD_ID", unique = true)
    private Long parkingRecordId;

    @Column(name = "PARKING_RECEIPT_NUMBER", unique = true)
    private String parkingReceiptNumber;

    @Column(name = "ENTRY_TIME")
    private LocalDateTime EntryTime;

    @Column(name = "EXIT_TIME")
    private LocalDateTime exitTime;

    @Column(name = "PARKING_FEE")
    private BigDecimal parkingFee;
}
