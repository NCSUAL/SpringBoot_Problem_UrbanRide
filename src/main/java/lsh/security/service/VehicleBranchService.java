package lsh.security.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lsh.security.domain.Vehicle;
import lsh.security.dto.request.vehicle.VehicleWithBranchRequest;

@Service
@RequiredArgsConstructor
public class VehicleBranchService {

    private final VehicleService vehicleService;
    private final BranchService branchService;

    public Vehicle associationVehicleAndBranch(final VehicleWithBranchRequest vehicleWithBranchRequest){
        Vehicle vehicle = vehicleService.isPresent(vehicleWithBranchRequest.vin());
        branchService.isPresent(vehicleWithBranchRequest.branchId(), present -> vehicle.addAssociation(present));
        return vehicleService.insertVehicle(vehicle);
    }


}
