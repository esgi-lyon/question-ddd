package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserStatsViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserStatsViewedEventDTO.class);
        UserStatsViewedEventDTO userStatsViewedEventDTO1 = new UserStatsViewedEventDTO();
        userStatsViewedEventDTO1.setId(1L);
        UserStatsViewedEventDTO userStatsViewedEventDTO2 = new UserStatsViewedEventDTO();
        assertThat(userStatsViewedEventDTO1).isNotEqualTo(userStatsViewedEventDTO2);
        userStatsViewedEventDTO2.setId(userStatsViewedEventDTO1.getId());
        assertThat(userStatsViewedEventDTO1).isEqualTo(userStatsViewedEventDTO2);
        userStatsViewedEventDTO2.setId(2L);
        assertThat(userStatsViewedEventDTO1).isNotEqualTo(userStatsViewedEventDTO2);
        userStatsViewedEventDTO1.setId(null);
        assertThat(userStatsViewedEventDTO1).isNotEqualTo(userStatsViewedEventDTO2);
    }
}
