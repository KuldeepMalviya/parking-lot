package vehicle.placement.parkinglot.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehicle.placement.parkinglot.dto.ParkVehicleRequestDto;
import vehicle.placement.parkinglot.dto.ParkVehicleResponseDto;
import vehicle.placement.parkinglot.dto.ParkingReceiptDto;
import vehicle.placement.parkinglot.model.ParkingReceipt;
import vehicle.placement.parkinglot.model.ParkingRecord;
import vehicle.placement.parkinglot.service.ParkVehicleService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-manager")
public class ParkVehicleController {
    private final ParkVehicleService parkVehicleService;

    @PostMapping("/park")
    public ResponseEntity<ParkVehicleResponseDto> parkVehicle(@RequestBody ParkVehicleRequestDto parkVehicleRequestDto) {
        log.info("Request request to park vehicle, vehicleDetails={}", parkVehicleRequestDto);
        ParkingRecord parkingRecord = parkVehicleService.parkVehicle(parkVehicleRequestDto);
        var response = ParkVehicleResponseDto.builder()
                .parkingSpotName(parkingRecord.getParkingSpot())
                .ticketNumber(parkingRecord.getId())
                .inTime(parkingRecord.getInTime())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/unPark/{parkingTicketNumber}")
    public ResponseEntity<ParkingReceiptDto> unParkVehicle(@PathVariable("parkingTicketNumber") Long parkingTicketNumber) {
        log.info("Request received to un-park vehicle, parkingTicketNumber={}", parkingTicketNumber);
        ParkingReceipt parkingReceipt = parkVehicleService.unParkVehicle(parkingTicketNumber);
        var response = ParkingReceiptDto.builder()
                .receiptNumber(parkingReceipt.getReceiptNumber())
                .entryDateTime(parkingReceipt.getEntryTime())
                .exitDateTime(parkingReceipt.getExitTime())
                .fees(parkingReceipt.getParkingFee())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
