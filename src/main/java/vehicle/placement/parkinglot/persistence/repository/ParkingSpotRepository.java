package vehicle.placement.parkinglot.persistence.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vehicle.placement.parkinglot.exception.FreeSpotNotAvailableException;
import vehicle.placement.parkinglot.exception.InvalidParkingSpotDetailsException;
import vehicle.placement.parkinglot.model.ParkingPlace;
import vehicle.placement.parkinglot.model.ParkingPlaceType;
import vehicle.placement.parkinglot.model.ParkingSpot;
import vehicle.placement.parkinglot.model.VehicleType;
import vehicle.placement.parkinglot.persistence.dao.ParkingSpotDao;
import vehicle.placement.parkinglot.persistence.entity.ParkingSpotEntity;

@Component
@RequiredArgsConstructor
public class ParkingSpotRepository {

  private static final String SPOT_NAME_FORMAT = "%s_%s__%s";
  private final ParkingSpotDao parkingSpotDao;

  public ParkingSpot getFreeParkingSpot(Long parkingPlaceId, VehicleType vehicleType) {
    ParkingSpotEntity freeSpot = parkingSpotDao.findFirstByParkingPlaceIdAndVehicleTypeAndIsFree(
            parkingPlaceId, vehicleType, true)
        .orElseThrow(
            () -> new FreeSpotNotAvailableException("No Free spot available for vehicleType=" + vehicleType));
    return ParkingSpot.builder()
        .id(freeSpot.getId())
        .isFree(freeSpot.isFree())
        .parkingSpotName(freeSpot.getParkingSpotName())
        .parkingPlaceId(freeSpot.getParkingPlaceId())
        .parkingPlaceType(freeSpot.getParkingPlaceType())
        .vehicleType(freeSpot.getVehicleType())
        .build();
  }

  public void initializeParkingSpotsForParkingPlace(ParkingPlace parkingPlace) {
    var parkingPlaceId = parkingPlace.getId();
    var parkingPlaceType = parkingPlace.getParkingPlaceType();

    initialize(parkingPlaceId, parkingPlaceType, VehicleType.TWO_WHEELER,
        parkingPlace.getTwoWheelerCapacity());
    initialize(parkingPlaceId, parkingPlaceType, VehicleType.CARS,
        parkingPlace.getCarOrSuvCapacity());
    initialize(parkingPlaceId, parkingPlaceType, VehicleType.BUS,
        parkingPlace.getBusOrTruckCapacity());

  }

  private void initialize(Long placeId, ParkingPlaceType placeType, VehicleType vehicleType,
      Long capacity) {
    for (int i = 1; i <= capacity; i++) {
      String parkingSpotName = String.format(SPOT_NAME_FORMAT, placeId, vehicleType, i);
      var spotEntity = ParkingSpotEntity.builder()
          .parkingPlaceId(placeId)
          .parkingPlaceType(placeType)
          .vehicleType(vehicleType)
          .parkingSpotName(parkingSpotName)
          .isFree(true)
          .build();
      parkingSpotDao.save(spotEntity);
    }
  }

  public void reserveParkingSpot(Long parkingSpotId) {
    ParkingSpotEntity spotEntity = parkingSpotDao.findById(parkingSpotId).orElseThrow(() ->
        new InvalidParkingSpotDetailsException("Invalid parking spot id = " + parkingSpotId));
    parkingSpotDao.save(spotEntity.toBuilder().isFree(false).build());
  }


  public void freeParkingSpotByName(String parkingSpotName) {
    ParkingSpotEntity spotEntity = parkingSpotDao.findByParkingSpotName(parkingSpotName)
        .orElseThrow(() -> new InvalidParkingSpotDetailsException(
            "Invalid parking spot name, name = " + parkingSpotName));
    parkingSpotDao.save(spotEntity.toBuilder().isFree(true).build());
  }

}
