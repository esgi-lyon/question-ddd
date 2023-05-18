package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserStatsViewedDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserStatsViewedDTO.class);
        UserStatsViewedDTO userStatsViewedDTO1 = new UserStatsViewedDTO();
        userStatsViewedDTO1.setId(1L);
        UserStatsViewedDTO userStatsViewedDTO2 = new UserStatsViewedDTO();
        assertThat(userStatsViewedDTO1).isNotEqualTo(userStatsViewedDTO2);
        userStatsViewedDTO2.setId(userStatsViewedDTO1.getId());
        assertThat(userStatsViewedDTO1).isEqualTo(userStatsViewedDTO2);
        userStatsViewedDTO2.setId(2L);
        assertThat(userStatsViewedDTO1).isNotEqualTo(userStatsViewedDTO2);
        userStatsViewedDTO1.setId(null);
        assertThat(userStatsViewedDTO1).isNotEqualTo(userStatsViewedDTO2);
    }
}
