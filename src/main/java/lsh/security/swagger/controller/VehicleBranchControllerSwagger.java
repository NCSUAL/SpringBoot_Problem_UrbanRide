package lsh.security.swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lsh.security.dto.ApiEntity;
import lsh.security.dto.request.vehicle.VehicleWithBranchRequest;
import lsh.security.dto.response.VehicleResponse;

@Tag(description = "Vehicle와 Branch 연관관계 관련한 작업을 수행", name = "Vehicle-Branch")
public interface VehicleBranchControllerSwagger {

    @Operation(description = "Vehicle와 Branch 연관관계를 설정해주는 API 입니다.", summary = "Vehicle와 Branch 연관관계를 설정")
    public ApiEntity<VehicleResponse> requestUpdateAssociationVehicleAndBranch(VehicleWithBranchRequest vehicleWithBranchRequest);

    @Operation(description = "Vehicle와 Branch 연관관계를 해제해주는 API 입니다.", summary = "Vehicle와 Branch 연관관계를 해제")
    public ApiEntity<VehicleResponse> requestDeleteAssociationVehicleAndBranch(VehicleWithBranchRequest vehicleWithBranchRequest);
} 