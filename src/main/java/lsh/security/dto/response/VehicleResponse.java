package lsh.security.dto.response;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lsh.security.constant.nested.VehicleType;
import lsh.security.domain.Vehicle;
import lsh.security.dto.response.nested.Branch;

@RequiredArgsConstructor
@Builder
@Getter
public final class VehicleResponse {
    private final String vin;
    private final VehicleType vehicleType;
    private final Branch branch;

    public static VehicleResponse of(Vehicle vehicle){
        return VehicleResponse.builder()
            .vin(vehicle.getId())
            .vehicleType(vehicle.getVehicleType())
            .branch(Objects.isNull(vehicle.getBranch()) ? null : Branch.of(vehicle.getBranch()))
            .build();
    }

}
