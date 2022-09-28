package vehicle.placement.parkinglot.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import vehicle.placement.parkinglot.config.MallFeeStructure;
import vehicle.placement.parkinglot.model.VehicleType;

class MallParkingFeeTest {

  private final MallParkingFee mallParkingFee = new MallParkingFee(getMallFeeStructure());

  @Test
  void shouldReturnExpectedFeeAmount() {
    assertThat(mallParkingFee
        .getParkingFeeAmount(VehicleType.TWO_WHEELER, LocalDateTime.now().minusHours(2L),
            LocalDateTime.now()))
        .isEqualTo(BigDecimal.valueOf(20L));

    assertThat(mallParkingFee
        .getParkingFeeAmount(VehicleType.TWO_WHEELER, LocalDateTime.now().minusMinutes(70L),
            LocalDateTime.now()))
        .isEqualTo(BigDecimal.valueOf(20L));

    assertThat(mallParkingFee
        .getParkingFeeAmount(VehicleType.CARS, LocalDateTime.now().minusHours(3L),
            LocalDateTime.now()))
        .isEqualTo(BigDecimal.valueOf(60L));

    assertThat(mallParkingFee
        .getParkingFeeAmount(VehicleType.BUS, LocalDateTime.now().minusHours(4L),
            LocalDateTime.now()))
        .isEqualTo(BigDecimal.valueOf(200L));
  }

  private MallFeeStructure getMallFeeStructure() {
    MallFeeStructure mallFeeStructure = new MallFeeStructure();
    mallFeeStructure.setBikeOrScooter(BigDecimal.TEN);
    mallFeeStructure.setCarOrSuv(BigDecimal.valueOf(20L));
    mallFeeStructure.setBusOrTrucks(BigDecimal.valueOf(50L));
    return mallFeeStructure;
  }
}