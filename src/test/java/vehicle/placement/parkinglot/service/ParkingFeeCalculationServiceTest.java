package vehicle.placement.parkinglot.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vehicle.placement.parkinglot.config.MallFeeStructure;
import vehicle.placement.parkinglot.model.FeePolicyType;
import vehicle.placement.parkinglot.model.ParkingPlaceType;
import vehicle.placement.parkinglot.model.ParkingRecord;
import vehicle.placement.parkinglot.model.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class ParkingFeeCalculationServiceTest {

    private final FeeStrategyFactory feeStrategyFactory = Mockito.mock(FeeStrategyFactory.class);
    private final ParkingFeeCalculationService parkingFeeCalculationService = new ParkingFeeCalculationService(
            feeStrategyFactory);

    @Test
    void calculateParkingFee() {
        ParkingRecord parkingRecord = ParkingRecord.builder()
        .parkingPlaceType(ParkingPlaceType.MALL)
        .vehicleType(VehicleType.TWO_WHEELER)
        .inTime(LocalDateTime.now().minusMinutes(100L))
        .outTime(LocalDateTime.now())
        .build();
        when(feeStrategyFactory.getParkingFeeStrategy(FeePolicyType.MALL)).thenReturn(
                getMallFeeStrategy());
        BigDecimal fee = parkingFeeCalculationService.calculateParkingFee(parkingRecord, FeePolicyType.MALL);
    assertThat(fee).isEqualTo(BigDecimal.valueOf(20L));
  }

  private MallParkingFee getMallFeeStrategy() {
    MallFeeStructure mallFeeStructure = new MallFeeStructure();
    mallFeeStructure.setBikeOrScooter(BigDecimal.TEN);
    mallFeeStructure.setCarOrSuv(BigDecimal.valueOf(20L));
    mallFeeStructure.setBusOrTrucks(BigDecimal.valueOf(50L));
    return new MallParkingFee(mallFeeStructure);
  }
}