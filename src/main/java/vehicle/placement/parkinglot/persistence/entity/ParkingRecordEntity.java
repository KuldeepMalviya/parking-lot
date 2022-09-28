package vehicle.placement.parkinglot.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.placement.parkinglot.model.ParkingPlaceType;
import vehicle.placement.parkinglot.model.VehicleType;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PARKING_RECORD")
public class ParkingRecordEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PARKING_PLACE_ID")
    private Long parkingPlaceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "PARKING_PLACE_TYPE")
    private ParkingPlaceType parkingPlaceType;

    @Column(name = "PARKING_SPOT_NAME")
    private String parkingSpot;

    @Enumerated(EnumType.STRING)
    @Column(name = "VEHICLE_TYPE")
    private VehicleType vehicleType;

    @Column(name = "VEHICLE_REG_NUMBER")
    private String vehicleRegistration;

    @Column(name = "IN_TIME")
    private LocalDateTime inTime;

    @Column(name = "OUT_TIME")
    private LocalDateTime outTime;

}
