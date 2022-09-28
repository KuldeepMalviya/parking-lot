package vehicle.placement.parkinglot.controller;

import static vehicle.placement.parkinglot.transformer.ParkingPlaceTransformer.toParkingPlace;
import static vehicle.placement.parkinglot.transformer.ParkingPlaceTransformer.toParkingPlaceResponseDto;
import static vehicle.placement.parkinglot.transformer.ParkingPlaceTransformer.toParkingPlaceResponseListDto;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vehicle.placement.parkinglot.dto.ParkingPlaceRequestDto;
import vehicle.placement.parkinglot.dto.ParkingPlaceResponseDto;
import vehicle.placement.parkinglot.model.ParkingPlace;
import vehicle.placement.parkinglot.service.ParkingPlaceService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/parkingplace")
public class ParkingAdminController {

    private final ParkingPlaceService parkingPlaceService;

    @PostMapping("/")
    public ResponseEntity<ParkingPlaceResponseDto> addParkingPlace(@RequestBody ParkingPlaceRequestDto parkingPlaceRequestDto) {
        log.info("Received request to add new parkingLot, details={}", parkingPlaceRequestDto);
        ParkingPlace savedParkingPlace = parkingPlaceService.addNewParkingPlace(toParkingPlace(parkingPlaceRequestDto));
        return new ResponseEntity<>(toParkingPlaceResponseDto(savedParkingPlace), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingPlaceResponseDto> getParkingPlace(@PathVariable("id") Long parkingPlaceId) {
        log.info("Received request to fetch parkingLot detail, parkingPlaceId={}", parkingPlaceId);
        ParkingPlace parkingPlaceById = parkingPlaceService.getParkingPlaceById(parkingPlaceId);
        return new ResponseEntity<>(toParkingPlaceResponseDto(parkingPlaceById), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ParkingPlaceResponseDto>> getAllParkingPlaces() {
        log.info("Received request to fetch all parking lots");
        List<ParkingPlace> parkingPlaces = parkingPlaceService.fetAllParkingPlaces();
        return new ResponseEntity<>(toParkingPlaceResponseListDto(parkingPlaces), HttpStatus.OK);
    }

}
