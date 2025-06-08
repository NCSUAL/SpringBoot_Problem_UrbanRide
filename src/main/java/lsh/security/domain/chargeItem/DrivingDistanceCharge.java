package lsh.security.domain.chargeItem;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DrivingDistanceCharge")
public class DrivingDistanceCharge extends ChargeItem{

    @Column(nullable = true, name = "distance_km")
    private double distanceKm;
}
