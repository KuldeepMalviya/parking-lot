package vehicle.placement.parkinglot.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ParkingLotExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({FreeSpotNotAvailableException.class, InvalidParkingSpotDetailsException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public final ResponseEntity<ExceptionResponse> handleSpotRelatedException(
      final Exception exception,
      WebRequest webRequest) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ExceptionResponse.builder()
            .message(exception.getMessage())
            .path(webRequest.getContextPath())
            .build());

  }

  @ExceptionHandler({ParkingPlaceDoesNotExistException.class,
      ParkingRecordDoesNotExistsException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public final ResponseEntity<ExceptionResponse> handleParkingPlaceExceptions(
      final Exception exception,
      WebRequest webRequest) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ExceptionResponse.builder()
            .message(exception.getMessage())
            .path(webRequest.getContextPath())
            .build());

  }

  @ExceptionHandler({VehicleAlreadyUnparkedException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public final ResponseEntity<ExceptionResponse> handleVehicleAlreadyUnparkedException(
      final Exception exception,
      WebRequest webRequest) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ExceptionResponse.builder()
            .message(exception.getMessage())
            .path(webRequest.getContextPath())
            .build());

  }
}
