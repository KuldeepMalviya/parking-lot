package vehicle.placement.parkinglot.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.placement.parkinglot.model.ParkingPlaceType;
import vehicle.placement.parkinglot.model.VehicleType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "PARKING_SPOT")
public class ParkingSpotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PARKING_PLACE_ID")
    private Long parkingPlaceId;

    @Column(name = "PARKING_PLACE_TYPE")
    private ParkingPlaceType parkingPlaceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "VEHICLE_TYPE")
    private VehicleType vehicleType;

    @Column(name = "PARKING_SPOT_NAME", unique = true)
    private String parkingSpotName;

    @Column(name = "IS_FREE")
    private boolean isFree;

}
