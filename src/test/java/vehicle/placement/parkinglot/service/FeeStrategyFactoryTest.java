package vehicle.placement.parkinglot.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import vehicle.placement.parkinglot.model.FeePolicyType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FeeStrategyFactoryTest {

  @Mock
  private AirPortParkingFee airPortParkingFee;
  @Mock
  private MallParkingFee mallParkingFee;
  @Mock
  private StadiumParkingFee stadiumParkingFee;

  @Mock
  private AirPortParkingFee2 airPortParkingFee2;
  @Mock
  private DefaultParkingFee defaultParkingFee;

  FeeStrategyFactory parkingFeeStrategy = new FeeStrategyFactory(airPortParkingFee, airPortParkingFee2, mallParkingFee,
          stadiumParkingFee, defaultParkingFee);

  @Test
  void getParkingFeeStrategy() {
    assertThat(parkingFeeStrategy.getParkingFeeStrategy(FeePolicyType.MALL)).isEqualTo(
            mallParkingFee);
    assertThat(parkingFeeStrategy.getParkingFeeStrategy(FeePolicyType.STADIUM)).isEqualTo(
            stadiumParkingFee);
    assertThat(parkingFeeStrategy.getParkingFeeStrategy(FeePolicyType.AIRPORT_1)).isEqualTo(
            airPortParkingFee);
  }
}