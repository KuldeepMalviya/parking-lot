package vehicle.placement.parkinglot.persistence.repository;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.model.ParkingReceipt;
import vehicle.placement.parkinglot.model.ParkingRecord;
import vehicle.placement.parkinglot.persistence.dao.ParkingReceiptDao;
import vehicle.placement.parkinglot.persistence.entity.ParkingReceiptEntity;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class ParkingReceiptRepository {

    private static final String RECEIPT_NUMBER_FORMAT = "R_%s";
    private final ParkingReceiptDao parkingReceiptDao;
    public ParkingReceipt createParkingReceipt(ParkingRecord parkingRecord, BigDecimal parkingFee) {
       var receiptEntity = ParkingReceiptEntity.builder()
                .parkingRecordId(parkingRecord.getId())
                .parkingReceiptNumber(String.format(RECEIPT_NUMBER_FORMAT, parkingRecord.getId()))
                .EntryTime(parkingRecord.getInTime())
                .exitTime(parkingRecord.getOutTime())
                .parkingFee(parkingFee)
                .build();
       return toParkingReceipt(parkingReceiptDao.save(receiptEntity));
    }

    private ParkingReceipt toParkingReceipt(ParkingReceiptEntity entity) {
        return ParkingReceipt.builder()
                .id(entity.getId())
                .ReceiptNumber(entity.getParkingReceiptNumber())
                .parkingRecordId(entity.getParkingRecordId())
                .entryTime(entity.getEntryTime())
                .exitTime(entity.getExitTime())
                .parkingFee(entity.getParkingFee())
                .build();
    }
}
