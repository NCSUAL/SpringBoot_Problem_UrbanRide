package branch;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.hamcrest.Matchers.hasSize;

import jakarta.transaction.Transactional;
import lsh.security.SecurityApplication;
import lsh.security.constant.nested.CityType;
import lsh.security.domain.Branch;
import lsh.security.dto.request.BranchRequest;
import lsh.security.repository.BranchRepository;

@SpringBootTest(classes = SecurityApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class BranchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BranchRepository branchRepository;

    @Test
    @DisplayName("Branch 엔티티 저장 API 테스트")
    @WithMockUser(roles = "USER", username = "user", password = "1111")
    public void requestInsertBranch() throws Exception{
        //given
        final BranchRequest branchRequest = new BranchRequest(null,"POST_테스트", CityType.BUSAN);

        //when
        mockMvc.perform(post("/api/branch")
        .with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(branchRequest))
        )

        //then
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.data.name").value(branchRequest.name()))
        .andExpect(jsonPath("$.data.cityType").value(branchRequest.cityType().toString()));
    }

    @Test
    @DisplayName("Branch 엔티티 전체 조회 테스트")
    @WithMockUser(roles = "USER", username = "user", password = "1111")
    public void requestInquiryAllBranch() throws Exception{
        //given
        int number = 1000;
        final List<Branch> branchRequests = IntStream.rangeClosed(1, number)
            .mapToObj(value -> new BranchRequest(null, "POST"+Integer.toString(value), CityType.SEOUL))
            .map(branchRequest -> branchRequest.toEntity())
            .toList();
        
        branchRepository.saveAll(branchRequests);
        //when
        
        mockMvc.perform(get("/api/branch")
        .with(csrf()))

        //then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data",hasSize(1000)));
    }


}
