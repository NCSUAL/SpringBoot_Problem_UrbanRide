package lsh.security.domain;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lsh.security.constant.PaymentStatus;
import lsh.security.domain.vo.Money;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@BatchSize(size = 10)
public class Payment {

    @Id
    @Column(name = "payment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", referencedColumnName = "rentalContract_id")
    private RentalContract rentalContract;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Embedded
    @AttributeOverrides(
        {
            @AttributeOverride(name = "feeAmount",column = @Column(name = "payment_amount")),
            @AttributeOverride(name = "currency",column = @Column(name = "payment_currency"))
        }
     )
    private Money paymentMoney;
}
