package lsh.security.domain.chargeItem;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PenaltyCharge")
public class PenaltyCharge extends ChargeItem{

    @Column(nullable = true)
    private double reason;
}
