package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LeaderBoardEntryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaderBoardEntryDTO.class);
        LeaderBoardEntryDTO leaderBoardEntryDTO1 = new LeaderBoardEntryDTO();
        leaderBoardEntryDTO1.setId(1L);
        LeaderBoardEntryDTO leaderBoardEntryDTO2 = new LeaderBoardEntryDTO();
        assertThat(leaderBoardEntryDTO1).isNotEqualTo(leaderBoardEntryDTO2);
        leaderBoardEntryDTO2.setId(leaderBoardEntryDTO1.getId());
        assertThat(leaderBoardEntryDTO1).isEqualTo(leaderBoardEntryDTO2);
        leaderBoardEntryDTO2.setId(2L);
        assertThat(leaderBoardEntryDTO1).isNotEqualTo(leaderBoardEntryDTO2);
        leaderBoardEntryDTO1.setId(null);
        assertThat(leaderBoardEntryDTO1).isNotEqualTo(leaderBoardEntryDTO2);
    }
}
