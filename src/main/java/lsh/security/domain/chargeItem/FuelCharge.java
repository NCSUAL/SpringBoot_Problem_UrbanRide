package lsh.security.domain.chargeItem;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FuelCharge")
public class FuelCharge extends ChargeItem{
    @Column(nullable = true, name = "liters_missing")
    private double litersMissing;
}
