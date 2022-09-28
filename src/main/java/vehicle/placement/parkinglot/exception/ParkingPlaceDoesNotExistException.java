package vehicle.placement.parkinglot.exception;

public class ParkingPlaceDoesNotExistException extends RuntimeException {

    public ParkingPlaceDoesNotExistException(String message) {
        super(message);
    }
}
