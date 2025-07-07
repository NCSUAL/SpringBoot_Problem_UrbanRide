package lsh.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lsh.security.domain.Vehicle;
import lsh.security.dto.request.vehicle.VehicleWithBranchRequest;

@Service
@RequiredArgsConstructor
public class VehicleBranchService {

    private final VehicleService vehicleService;
    private final BranchService branchService;

    //연관관계 설정
    public Vehicle associationVehicleAndBranch(final VehicleWithBranchRequest vehicleWithBranchRequest){
        Vehicle vehicle = vehicleService.isPresent(vehicleWithBranchRequest.vin());
        branchService.isPresent(vehicleWithBranchRequest.branchId(), present -> vehicle.addAssociation(present));
        return vehicleService.insertVehicle(vehicle);
    }

    //연관관계 헤제
    public Vehicle deleteAssociationVehicleAndBranch(final VehicleWithBranchRequest vehicleWithBranchRequest){
        Vehicle vehicle = vehicleService.isPresent(vehicleWithBranchRequest.vin());
        branchService.isPresent(vehicleWithBranchRequest.branchId(), present -> vehicle.deleteAssociation(present));
        return vehicle;
    }

    //연관관계 설정된 차량 조회
    public List<Vehicle> inquiryByAssociationBranch(){
        return vehicleService.inquiryAllByAssociationBranch();
    }

}
