package vehicle.placement.parkinglot.integration;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import vehicle.placement.parkinglot.model.ParkingPlace;
import vehicle.placement.parkinglot.model.ParkingPlaceType;
import vehicle.placement.parkinglot.service.ParkingPlaceService;


@SpringBootTest
@ActiveProfiles("test")
public class AirportParkingTypeIT {

  RestTemplate restTemplate = new RestTemplate();
  @Autowired
  private ParkingPlaceService parkingPlaceService;
  private ParkingPlace parkingPlace = ParkingPlace.builder()
      .parkingPlaceType(ParkingPlaceType.AIRPORT)
      .twoWheelerCapacity(2L)
      .carOrSuvCapacity(2L)
      .build();

  @BeforeEach
  void setUp() {
    parkingPlace = parkingPlaceService.addNewParkingPlace(parkingPlace);
  }

  @Test
  void shouldParkVehicle() {
  }
}
