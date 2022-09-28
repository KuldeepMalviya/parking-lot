package vehicle.placement.parkinglot.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import vehicle.placement.parkinglot.config.AirPortFeeStructure;
import vehicle.placement.parkinglot.model.VehicleType;

class AirPortParkingFeeTest {

  private final AirPortParkingFee airPortParkingFee = new AirPortParkingFee(
      getAirPortFeeStructure());

  @Test
  void shouldReturnExpectedParkingFee() {
    // Less 1 hour
/*    assertThat(airPortParkingFee.getParkingFeeAmount(VehicleType.TWO_WHEELER,
        LocalDateTime.now().minusMinutes(30L), LocalDateTime.now())).isEqualTo(BigDecimal.ZERO);
    // Less than 8 hours
    assertThat(airPortParkingFee.getParkingFeeAmount(VehicleType.TWO_WHEELER,
        LocalDateTime.now().minusMinutes(300L), LocalDateTime.now())).isEqualTo(
        BigDecimal.valueOf(40L));
    // Less than 24 hours
    assertThat(airPortParkingFee.getParkingFeeAmount(VehicleType.TWO_WHEELER,
        LocalDateTime.now().minusHours(10L), LocalDateTime.now())).isEqualTo(
        BigDecimal.valueOf(60L));
    // More than 24 hours
    assertThat(airPortParkingFee.getParkingFeeAmount(VehicleType.TWO_WHEELER,
        LocalDateTime.now().minusHours(30L), LocalDateTime.now())).isEqualTo(
        BigDecimal.valueOf(160L));

    // Less than 12 hours
    assertThat(airPortParkingFee.getParkingFeeAmount(VehicleType.CARS,
        LocalDateTime.now().minusMinutes(300L), LocalDateTime.now())).isEqualTo(
        BigDecimal.valueOf(60L));
    // Less than 24 hours
    assertThat(
        airPortParkingFee.getParkingFeeAmount(VehicleType.CARS, LocalDateTime.now().minusHours(13L),
            LocalDateTime.now())).isEqualTo(BigDecimal.valueOf(80L));*/
    // More than 24 hours
    assertThat(
        airPortParkingFee.getParkingFeeAmount(VehicleType.CARS, LocalDateTime.now().minusHours(30L),
            LocalDateTime.now())).isEqualTo(BigDecimal.valueOf(200L));
  }

  private AirPortFeeStructure getAirPortFeeStructure() {
    AirPortFeeStructure structure = new AirPortFeeStructure();
    structure.setBike_range_00_01(BigDecimal.ZERO);
    structure.setBike_range_01_08(BigDecimal.valueOf(40L));
    structure.setBike_range_08_24(BigDecimal.valueOf(60L));
    structure.setBike_range_24_EachDay(BigDecimal.valueOf(80L));

    structure.setCar_range_00_12(BigDecimal.valueOf(60L));
    structure.setCar_range_12_24(BigDecimal.valueOf(80L));
    structure.setCar_range_24_EachDay(BigDecimal.valueOf(100L));
    return structure;
  }
}