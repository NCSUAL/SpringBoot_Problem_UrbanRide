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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lsh.security.constant.RequestedClass;
import lsh.security.domain.vo.BaseTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@BatchSize(size = 10)
public class Reservation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    private Branch branch;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestedClass requestedClass;

    @Embedded
    @AttributeOverrides(
    {
        @AttributeOverride(name = "startAt",column = @Column(name = "start_at")),
        @AttributeOverride(name = "endAt",column = @Column(name = "end_at"))
    }
    )
    private BaseTime reservationTime;
    
    @Version
    private int version;
    
}
