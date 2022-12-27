package vehicle.placement.parkinglot.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vehicle.placement.parkinglot.model.FeePolicyType;
import vehicle.placement.parkinglot.model.ParkingPlaceType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PARKING_PLACE")
public class ParkingPlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PARKING_PLACE_TYPE")
    private ParkingPlaceType parkingPlaceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "FEE_POLICY_TYPE")
    private FeePolicyType feePolicyType;

    @Column(name = "BIKE_CAPACITY")
    private Long TwoWheelerCapacity;

    @Column(name = "CAR_CAPACITY")
    private Long fourWheelerCapacity;

    @Column(name = "BUS_CAPACITY")
    private Long busOrTrucksCapacity;

}
