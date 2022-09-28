package vehicle.placement.parkinglot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.placement.parkinglot.dto.ParkingPlaceRequestDto;
import vehicle.placement.parkinglot.dto.ParkingPlaceResponseDto;
import vehicle.placement.parkinglot.model.ParkingPlace;
import vehicle.placement.parkinglot.service.ParkingPlaceService;


import java.util.List;

import static vehicle.placement.parkinglot.transformer.ParkingPlaceTransformer.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/parkingplace")

public class ParkingAdminController {

    private final ParkingPlaceService parkingPlaceService;

    @PostMapping("/")
    public ResponseEntity<ParkingPlaceResponseDto> addParkingPlace(@RequestBody ParkingPlaceRequestDto parkingPlaceRequestDto) {
        ParkingPlace savedParkingPlace = parkingPlaceService.addNewParkingPlace(toParkingPlace(parkingPlaceRequestDto));
        return new ResponseEntity<>(toParkingPlaceResponseDto(savedParkingPlace), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingPlaceResponseDto> getParkingPlace(@PathVariable("id") Long parkingPlaceId) {
        ParkingPlace parkingPlaceById = parkingPlaceService.getParkingPlaceById(parkingPlaceId);
        return new ResponseEntity<>(toParkingPlaceResponseDto(parkingPlaceById), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ParkingPlaceResponseDto>> getAllParkingPlaces() {
        List<ParkingPlace> parkingPlaces = parkingPlaceService.fetAllParkingPlaces();
        return new ResponseEntity<>(toParkingPlaceResponseListDto(parkingPlaces), HttpStatus.OK);
    }

}
