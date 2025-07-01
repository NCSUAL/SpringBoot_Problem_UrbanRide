package lsh.security.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lsh.security.constant.nested.CityType;
import lsh.security.dto.request.BranchRequest;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {@Index(name = "idx_name",columnList = "name")})
@EqualsAndHashCode
public class Branch {

    @Id
    @Column(name = "branch_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CityType cityType;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<Vehicle> vehicles = new ArrayList<>();

    @Builder
    public Branch(final Long id, final String name, final CityType cityType){
        this.id = id;
        this.name = name;
        this.cityType = cityType;
    }

    public Branch update(final BranchRequest branchRequest){
        this.name = branchRequest.name();
        this.cityType = branchRequest.cityName();
        
        return this;
    }

    public Branch patch(final BranchRequest branchRequest){
        if(Objects.nonNull(branchRequest.name())){
            this.name = branchRequest.name();
        }

        if(Objects.nonNull(branchRequest.cityName())){
            this.cityType = branchRequest.cityName();
        }
        
        System.out.println(branchRequest);
        return this;
    }

    public void addVehicle(final Vehicle vehicle){
        this.vehicles.add(vehicle);
    }

    public void removeVehicle(final Vehicle vehicle){
        this.vehicles.remove(vehicle);
    }
    
    @Override
    public String toString(){
        return "name:".concat(" ").concat(name).concat("\n").concat("cityType: ").concat(cityType.toString());
    }

}
