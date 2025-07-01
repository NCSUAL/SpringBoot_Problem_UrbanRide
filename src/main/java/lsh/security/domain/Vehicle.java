package lsh.security.domain;

import java.util.Objects;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lsh.security.constant.nested.VehicleType;
import lsh.security.dto.request.vehicle.VehicleRequest;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@BatchSize(size = 10)
@Builder
@Getter
public class Vehicle {
    @Id
    @Column(name = "vehicle_id")
    private String id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id", nullable = true)
    private Branch branch;

    @Version
    private int version;

    public Vehicle patch(VehicleRequest vehicleRequest){

        if(Objects.nonNull(vehicleRequest.vehicleType())){
            this.vehicleType = vehicleRequest.vehicleType();
        }
        return this;
    }

    public Vehicle update(VehicleRequest vehicleRequest){
        this.vehicleType = vehicleRequest.vehicleType();
        return this;
    }

    public void addAssociation(final Branch branch){
        this.branch = branch;
        branch.addVehicle(this);
    }

    public void deleteAssociation(final Branch branch){
        this.branch = null;
        branch.removeVehicle(this);
    }
}
