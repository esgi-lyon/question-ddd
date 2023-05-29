package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewLeaderBoardCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewLeaderBoardCommandDTO.class);
        ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO1 = new ViewLeaderBoardCommandDTO();
        viewLeaderBoardCommandDTO1.setId(1L);
        ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO2 = new ViewLeaderBoardCommandDTO();
        assertThat(viewLeaderBoardCommandDTO1).isNotEqualTo(viewLeaderBoardCommandDTO2);
        viewLeaderBoardCommandDTO2.setId(viewLeaderBoardCommandDTO1.getId());
        assertThat(viewLeaderBoardCommandDTO1).isEqualTo(viewLeaderBoardCommandDTO2);
        viewLeaderBoardCommandDTO2.setId(2L);
        assertThat(viewLeaderBoardCommandDTO1).isNotEqualTo(viewLeaderBoardCommandDTO2);
        viewLeaderBoardCommandDTO1.setId(null);
        assertThat(viewLeaderBoardCommandDTO1).isNotEqualTo(viewLeaderBoardCommandDTO2);
    }
}
