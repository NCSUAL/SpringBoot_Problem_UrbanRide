package branch;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lsh.security.constant.nested.CityType;
import lsh.security.domain.Branch;
import lsh.security.dto.request.BranchRequest;
import lsh.security.repository.BranchRepository;
import lsh.security.service.BranchService;

@ExtendWith(MockitoExtension.class)
public class BranchServiceTest {
    @InjectMocks
    private BranchService branchService;

    @Mock
    private BranchRepository branchRepository;

    @Test
    public void CRUD_CREATE_기능_테스트(){
        //given
        Long id = 100L;
        String name = "HyunDai";
        CityType cityType = CityType.SEOUL;

        BranchRequest branchRequest = new BranchRequest(id, name, cityType);
        
        //when
        when(branchRepository.save(any(Branch.class))).thenReturn(branchRequest.toEntity());

        branchService.insertBranch(branchRequest);

        //then
        verify(branchRepository).save(any(Branch.class));
    }

    @Test
    public void CRUD_UPDATE_기능_테스트(){
        //given

        //기존 엔티티
        Branch branch = Branch.builder()
            .id(100L)
            .name("NAVER")
            .cityType(CityType.SEOUL)
            .build();

        //요청 Path
        Long id = branch.getId();

        //요청 Json
        String name = "HyunDai";
        CityType cityType = CityType.SEOUL;

        BranchRequest branchRequest = new BranchRequest(id, name, cityType);

        //when
        when(branchRepository.findById(id)).thenReturn(Optional.of(branch));

        when(branchRepository.save(any(Branch.class))).thenReturn(branchRequest.toEntity());

        //then
        Branch newBranch = branchService.isPresent(id, present -> branchService.updateByBranchRequest(present, branchRequest));

        assertEquals(branchRequest.toEntity(), newBranch);
    }

    @Test
    public void CRUD_PATCH_기능_테스트(){
        //given

        //기존 DB에 있던 엔티티
        Branch branch = Branch.builder()
            .id(150L)
            .name("TestCar")
            .cityType(CityType.DAEGU)
            .build();

        //요청 id
        Long requestId = branch.getId();

        //요청 body
        BranchRequest branchRequest = new BranchRequest(null, "changeCar",null);
        
        //when

        Branch patchBranch = branch.patch(branchRequest);
        //id(PK)로 조회 가정
        when(branchRepository.findById(requestId)).thenReturn(Optional.of(branch));
        //특정 Branch 엔티티로 저장 가정
        when(branchRepository.save(any(Branch.class))).thenReturn(patchBranch);

        //then
        Branch handleBranch = branchService.isPresent(requestId, present -> branchService.updateByBranchRequest(branch, branchRequest));

        //저장 메소드가 호출되었는지 검증.
        verify(branchRepository).save(any(Branch.class));

        assertEquals(patchBranch, handleBranch);
    }
    
    @Test
    public void CRUD_DELETE_기능_테스트(){
        //given
        Branch branch = Branch.builder()
        .name("KakaoCar")
        .cityType(CityType.SEOUL)
        .build();

        //when
        doNothing().when(branchRepository).delete(branch);
        branchService.delete(branch);

        //then
        verify(branchRepository).delete(branch);
    }

}
