
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import lsh.security.constant.nested.CityType;
import lsh.security.domain.Branch;
import lsh.security.dto.request.BranchRequest;
import lsh.security.repository.BranchRepository;
import lsh.security.service.branch.BranchService;

@ExtendWith(MockitoExtension.class)
public class BranchControllerTest {

    @InjectMocks
    private BranchService branchService;

    @Mock
    private BranchRepository branchRepository;

    @Test
    public void 간단한_테스트(){
        //given
        BranchRequest branchRequest = new BranchRequest("test", CityType.SEOUL); 

        Branch branch = branchRequest.toEntity();
        //when
        Mockito.when(branchRepository.save(Mockito.any(Branch.class))).thenReturn(branch);

        //then
        Branch result = branchService.createBranch(branchRequest).orElseThrow(() -> new IllegalArgumentException("간단한_테스트 실패"));
        
        Assertions.assertEquals(result, branch);

        Mockito.verify(branchRepository).save(Mockito.any(Branch.class));
        
    }
}
