package vehicle.placement.parkinglot.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import vehicle.placement.parkinglot.config.StadiumFeeStructure;
import vehicle.placement.parkinglot.model.VehicleType;

class StadiumParkingFeeTest {

  private final StadiumParkingFee stadiumParkingFee = new StadiumParkingFee(
      getStadiumFeeStructure());

  @Test
  void shouldReturnExpectedParkingFee() {
    assertThat(stadiumParkingFee.getParkingFeeAmount(VehicleType.TWO_WHEELER,
        LocalDateTime.now().minusMinutes(220L), LocalDateTime.now())).isEqualTo(
        BigDecimal.valueOf(30L));
    assertThat(stadiumParkingFee.getParkingFeeAmount(VehicleType.TWO_WHEELER,
        LocalDateTime.now().minusMinutes(899L), LocalDateTime.now())).isEqualTo(
        BigDecimal.valueOf(390L));
    assertThat(stadiumParkingFee.getParkingFeeAmount(VehicleType.CARS,
        LocalDateTime.now().minusMinutes(690L), LocalDateTime.now())).isEqualTo(
        BigDecimal.valueOf(180L));
    assertThat(stadiumParkingFee.getParkingFeeAmount(VehicleType.CARS,
        LocalDateTime.now().minusMinutes(785L), LocalDateTime.now())).isEqualTo(
        BigDecimal.valueOf(580L));
  }

  private StadiumFeeStructure getStadiumFeeStructure() {
    StadiumFeeStructure structure = new StadiumFeeStructure();
    structure.setBike_range_00_04(BigDecimal.valueOf(30L));
    structure.setBike_range_04_12(BigDecimal.valueOf(60L));
    structure.setBike_range_12_IN(BigDecimal.valueOf(100L));

    structure.setCar_range_00_04(BigDecimal.valueOf(60L));
    structure.setCar_range_04_12(BigDecimal.valueOf(120L));
    structure.setCar_range_12_IN(BigDecimal.valueOf(200L));
    return structure;
  }
}