package lsh.security.domain;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lsh.security.domain.vo.BaseTime;
import lsh.security.domain.vo.Money;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@BatchSize(size = 10)
public class RentalContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rentalContract_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id")
    private Vehicle vehicle;

    @Embedded
    @AttributeOverrides(
    {
        @AttributeOverride(name = "startAt",column = @Column(name = "picked_up_at")),
        @AttributeOverride(name = "endAt",column = @Column(name = "returned_at"))
    }
    )
    private BaseTime rentalContractTime;

    @Embedded
    @AttributeOverrides(
        {
            @AttributeOverride(name = "feeAmount",column = @Column(name = "base_fee_amount")),
            @AttributeOverride(name = "currency",column = @Column(name = "current_currency"))
        }
     )
    private Money currentMoney;

    @Embedded
    @AttributeOverrides(
        {
            @AttributeOverride(name = "feeAmount",column = @Column(name = "total_fee_amount")),
            @AttributeOverride(name = "currency",column = @Column(name = "total_currency"))
        }
     )
    private Money totalMoney;

    @Version
    private int version;
}
