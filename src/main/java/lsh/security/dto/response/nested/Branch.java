package lsh.security.dto.response.nested;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lsh.security.constant.nested.CityType;

@RequiredArgsConstructor
@Getter
@Builder
public class Branch {
    private final Long id;
    private final String name;
    private final CityType cityType;

    public static Branch of(final lsh.security.domain.Branch branch){
        return Branch.builder()
        .id(branch.getId())
        .name(branch.getName())
        .cityType(branch.getCityType())
        .build();
    }

}
