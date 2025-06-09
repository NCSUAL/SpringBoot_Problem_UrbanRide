package lsh.security.domain.chargeItem;

import java.time.LocalDateTime;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lsh.security.domain.RentalContract;
import lsh.security.domain.vo.Money;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "charge_type", columnDefinition = "VARCHAR(31)")
@BatchSize(size = 10)
@Getter
public abstract class ChargeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chargeItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", referencedColumnName = "rentalContract_id")
    private RentalContract rentalContract;

    @Embedded
    @AttributeOverrides(
    {
        @AttributeOverride(name = "feeAmount",column = @Column(name = "chargeItem_amount")),
        @AttributeOverride(name = "currency",column = @Column(name = "chargeItem_currency"))
    }
    )
    private Money money;

    @Column(nullable = false, name = "occurred_at")
    private LocalDateTime occurredAt;
}
