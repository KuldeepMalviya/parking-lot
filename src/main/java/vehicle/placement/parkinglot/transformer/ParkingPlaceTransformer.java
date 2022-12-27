package vehicle.placement.parkinglot.transformer;

import vehicle.placement.parkinglot.dto.ParkingPlaceRequestDto;
import vehicle.placement.parkinglot.dto.ParkingPlaceResponseDto;
import vehicle.placement.parkinglot.model.ParkingPlace;
import vehicle.placement.parkinglot.persistence.entity.ParkingPlaceEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class ParkingPlaceTransformer {

    public static ParkingPlace toParkingPlace(ParkingPlaceRequestDto parkingPlaceRequestDto) {
        return ParkingPlace.builder()
                .parkingPlaceType(parkingPlaceRequestDto.getParkingPlaceType())
                .feePolicyType(parkingPlaceRequestDto.getFeePolicyType())
                .twoWheelerCapacity(parkingPlaceRequestDto.getTwoWheelerCapacity())
                .busOrTruckCapacity(parkingPlaceRequestDto.getBusTruckCapacity())
                .carOrSuvCapacity(parkingPlaceRequestDto.getCarOrSuvCapacity())
                .build();

    }
    public static ParkingPlace toParkingPlace(ParkingPlaceEntity placeEntity) {
        return ParkingPlace.builder()
                .id(placeEntity.getId())
                .parkingPlaceType(placeEntity.getParkingPlaceType())
                .feePolicyType(placeEntity.getFeePolicyType())
                .twoWheelerCapacity(placeEntity.getTwoWheelerCapacity())
                .busOrTruckCapacity(placeEntity.getBusOrTrucksCapacity())
                .carOrSuvCapacity(placeEntity.getFourWheelerCapacity())
                .build();

    }

    public static ParkingPlaceResponseDto toParkingPlaceResponseDto(ParkingPlace parkingPlace) {
        var responseDto = new ParkingPlaceResponseDto();
        responseDto.setId(parkingPlace.getId());
        responseDto.setParkingPlaceType(parkingPlace.getParkingPlaceType());
        responseDto.setTwoWheelerCapacity(parkingPlace.getTwoWheelerCapacity());
        responseDto.setCarOrSuvCapacity(parkingPlace.getCarOrSuvCapacity());
        responseDto.setBusTruckCapacity(parkingPlace.getBusOrTruckCapacity());
        return responseDto;
    }

    public static List<ParkingPlaceResponseDto> toParkingPlaceResponseListDto(List<ParkingPlace> parkingPlaces) {
        return parkingPlaces.stream().map(ParkingPlaceTransformer::toParkingPlaceResponseDto).collect(Collectors.toList());

    }

    public static List<ParkingPlace> toParkingPlaceList(List<ParkingPlaceEntity> parkingPlaceEntities) {
       return parkingPlaceEntities.stream().map(ParkingPlaceTransformer::toParkingPlace).collect(Collectors.toList());
    }
}
