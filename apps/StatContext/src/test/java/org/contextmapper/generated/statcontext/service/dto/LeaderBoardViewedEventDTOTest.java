package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LeaderBoardViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaderBoardViewedEventDTO.class);
        LeaderBoardViewedEventDTO leaderBoardViewedEventDTO1 = new LeaderBoardViewedEventDTO();
        leaderBoardViewedEventDTO1.setId(1L);
        LeaderBoardViewedEventDTO leaderBoardViewedEventDTO2 = new LeaderBoardViewedEventDTO();
        assertThat(leaderBoardViewedEventDTO1).isNotEqualTo(leaderBoardViewedEventDTO2);
        leaderBoardViewedEventDTO2.setId(leaderBoardViewedEventDTO1.getId());
        assertThat(leaderBoardViewedEventDTO1).isEqualTo(leaderBoardViewedEventDTO2);
        leaderBoardViewedEventDTO2.setId(2L);
        assertThat(leaderBoardViewedEventDTO1).isNotEqualTo(leaderBoardViewedEventDTO2);
        leaderBoardViewedEventDTO1.setId(null);
        assertThat(leaderBoardViewedEventDTO1).isNotEqualTo(leaderBoardViewedEventDTO2);
    }
}
