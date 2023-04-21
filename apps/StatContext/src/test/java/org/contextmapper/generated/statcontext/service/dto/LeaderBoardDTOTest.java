package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LeaderBoardDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaderBoardDTO.class);
        LeaderBoardDTO leaderBoardDTO1 = new LeaderBoardDTO();
        leaderBoardDTO1.setId(1L);
        LeaderBoardDTO leaderBoardDTO2 = new LeaderBoardDTO();
        assertThat(leaderBoardDTO1).isNotEqualTo(leaderBoardDTO2);
        leaderBoardDTO2.setId(leaderBoardDTO1.getId());
        assertThat(leaderBoardDTO1).isEqualTo(leaderBoardDTO2);
        leaderBoardDTO2.setId(2L);
        assertThat(leaderBoardDTO1).isNotEqualTo(leaderBoardDTO2);
        leaderBoardDTO1.setId(null);
        assertThat(leaderBoardDTO1).isNotEqualTo(leaderBoardDTO2);
    }
}
