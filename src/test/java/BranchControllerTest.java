
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.validation.constraints.AssertTrue;
import lsh.security.constant.nested.CityType;
import lsh.security.domain.Branch;
import lsh.security.dto.request.BranchRequest;
import lsh.security.repository.BranchRepository;
import lsh.security.service.BranchService;

@ExtendWith(MockitoExtension.class)
public class BranchControllerTest {

    @Mock
    private BranchService branchService;

    @InjectMocks
    private BranchRepository branchRepository;

    public void 간단한_테스트(){
        //given
        BranchRequest branchRequest = new BranchRequest("test", CityType.SEOUL); 

        Branch branch = branchRequest.toEntity();
        //when
        Mockito.when(branchRepository.save(branch)).thenReturn(branch);

        //then
        Assertions.assertEquals(Mockito.verify(branchService.createBranch(branchRequest)).get(), branch);

    }
}
