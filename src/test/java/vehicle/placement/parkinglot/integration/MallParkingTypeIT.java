package vehicle.placement.parkinglot.integration;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import vehicle.placement.parkinglot.controller.ParkVehicleController;
import vehicle.placement.parkinglot.dto.ParkVehicleRequestDto;
import vehicle.placement.parkinglot.dto.ParkVehicleResponseDto;
import vehicle.placement.parkinglot.dto.ParkingReceiptDto;
import vehicle.placement.parkinglot.model.FeePolicyType;
import vehicle.placement.parkinglot.model.ParkingPlace;
import vehicle.placement.parkinglot.model.ParkingPlaceType;
import vehicle.placement.parkinglot.model.VehicleType;
import vehicle.placement.parkinglot.service.ParkingPlaceService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MallParkingTypeIT {

    @Autowired
    ParkingPlaceService parkingPlaceService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ParkVehicleController parkVehicleController;
    private ParkingPlace parkingPlace = ParkingPlace.builder()
            .parkingPlaceType(ParkingPlaceType.MALL)
            .feePolicyType(FeePolicyType.MALL)
            .twoWheelerCapacity(2L)
            .carOrSuvCapacity(2L)
            .build();

    @BeforeEach
    void setUp() {
        parkingPlace = parkingPlaceService.addNewParkingPlace(parkingPlace);
    }

    @Test
    void shouldParkVehicle() {
        ParkVehicleRequestDto parkVehicleRequestDto = new ParkVehicleRequestDto(parkingPlace.getId(), "KA 04 AB 3210", VehicleType.TWO_WHEELER);
        ResponseEntity<ParkVehicleResponseDto> responseEntity = parkVehicleController.parkVehicle(parkVehicleRequestDto);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    void shouldUnParkVehicle() {
        // park
        ParkVehicleRequestDto parkVehicleRequestDto = new ParkVehicleRequestDto(parkingPlace.getId(), "KA 04 AB 3211", VehicleType.CARS);
        ResponseEntity<ParkVehicleResponseDto> responseEntity = parkVehicleController.parkVehicle(parkVehicleRequestDto);
        assertThat(responseEntity.getBody()).isNotNull();
        long parkingRecordId = responseEntity.getBody().getTicketNumber();
        // update in time
        var localDateTime = LocalDateTime.now().minusMinutes(130L).toString();
        String update = "UPDATE PARKING_RECORD SET IN_TIME = '" + localDateTime + "' WHERE ID =" + parkingRecordId + ";";
        jdbcTemplate.execute(update);
        // un park
        ResponseEntity<ParkingReceiptDto> receiptDtoResponseEntity = parkVehicleController.unParkVehicle(parkingRecordId);

        assertThat(receiptDtoResponseEntity).isNotNull();
        assertThat(receiptDtoResponseEntity.getBody()).isNotNull();
        // CAR parking charge for 130 min is 20+20+20
        assertThat(receiptDtoResponseEntity.getBody().getFees()).isEqualTo(BigDecimal.valueOf(60L));
    }
}
