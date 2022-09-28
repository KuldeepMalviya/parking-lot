package vehicle.placement.parkinglot.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import vehicle.placement.parkinglot.model.ParkingPlaceType;

class FeeStrategyFactoryTest {

  @Mock
  private AirPortParkingFee airPortParkingFee;
  @Mock
  private MallParkingFee mallParkingFee;
  @Mock
  private StadiumParkingFee stadiumParkingFee;
  @Mock
  private DefaultParkingFee defaultParkingFee;
  FeeStrategyFactory parkingFeeStrategy = new FeeStrategyFactory(airPortParkingFee, mallParkingFee,
      stadiumParkingFee, defaultParkingFee);

  @Test
  void getParkingFeeStrategy() {
    assertThat(parkingFeeStrategy.getParkingFeeStrategy(ParkingPlaceType.MALL)).isEqualTo(
        mallParkingFee);
    assertThat(parkingFeeStrategy.getParkingFeeStrategy(ParkingPlaceType.STADIUM)).isEqualTo(
        stadiumParkingFee);
    assertThat(parkingFeeStrategy.getParkingFeeStrategy(ParkingPlaceType.AIRPORT)).isEqualTo(
        airPortParkingFee);
  }
}