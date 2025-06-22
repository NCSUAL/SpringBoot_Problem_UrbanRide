package lsh.security.dto.response;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lsh.security.constant.nested.CityType;
import lsh.security.domain.Branch;
import lsh.security.dto.response.nested.Vehicle;

@RequiredArgsConstructor
@Getter
@Builder
public class BranchResponse {
    private final Long id;
    private final String name;
    private final CityType cityType;
    private final List<Vehicle> vehicles;

    public static BranchResponse of(final Branch branch){
        return BranchResponse.builder()
        .id(branch.getId())
        .name(branch.getName())
        .cityType(branch.getCityType())
        .vehicles(branch.getVehicles().stream().map(arg -> Objects.isNull(arg) ? null : Vehicle.of(arg)).collect(Collectors.toList()))
        .build();
    }

    

}
