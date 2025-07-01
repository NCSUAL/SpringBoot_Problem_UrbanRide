package lsh.security.swagger.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lsh.security.dto.ApiEntity;
import lsh.security.dto.request.vehicle.VehicleRequest;
import lsh.security.dto.response.VehicleResponse;

@Tag(description = "Vehicle(Entity) 관련한 CRUD 작업을 수행", name = "Vehicle")
public interface VehicleControllerSwagger {

    @Operation(description = "Vehicle 전부 조회하는 API 입니다.", summary = "Vehicle를 전부 조회")
    public ApiEntity<List<VehicleResponse>> requestInquiryAllVehicle();

    @Operation(description = "Vehicle를 생성하는 API 입니다.", summary = "Vehicle를 생성")
    public ApiEntity<VehicleResponse> requestInsertVehicle( VehicleRequest vehicleRequest);

    @Operation(description = "특정 Vehicle를 수정하는 API 입니다.", summary = "특정 Vehicle 수정")
    public ApiEntity<VehicleResponse> requestUpdateVehicle(VehicleRequest vehicleRequest);

    @Operation(description = "특정 Vehicle를 일부분 수정하는 API 입니다.", summary = "특정 Vehicle를 일부분 수정")
    public ApiEntity<VehicleResponse> requestPatchVehicle(VehicleRequest vehicleRequest);

    @Operation(description = "특정 Vehicle를 삭제하는 API 입니다.", summary = "특정 Vehicle를 삭제")
    public ApiEntity<String> requestDeleteVehicle(String vin);
}
