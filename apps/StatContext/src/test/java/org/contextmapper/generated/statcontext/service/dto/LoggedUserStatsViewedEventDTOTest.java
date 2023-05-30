package org.contextmapper.generated.statcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LoggedUserStatsViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoggedUserStatsViewedEventDTO.class);
        LoggedUserStatsViewedEventDTO loggedUserStatsViewedEventDTO1 = new LoggedUserStatsViewedEventDTO();
        loggedUserStatsViewedEventDTO1.setId(1L);
        LoggedUserStatsViewedEventDTO loggedUserStatsViewedEventDTO2 = new LoggedUserStatsViewedEventDTO();
        assertThat(loggedUserStatsViewedEventDTO1).isNotEqualTo(loggedUserStatsViewedEventDTO2);
        loggedUserStatsViewedEventDTO2.setId(loggedUserStatsViewedEventDTO1.getId());
        assertThat(loggedUserStatsViewedEventDTO1).isEqualTo(loggedUserStatsViewedEventDTO2);
        loggedUserStatsViewedEventDTO2.setId(2L);
        assertThat(loggedUserStatsViewedEventDTO1).isNotEqualTo(loggedUserStatsViewedEventDTO2);
        loggedUserStatsViewedEventDTO1.setId(null);
        assertThat(loggedUserStatsViewedEventDTO1).isNotEqualTo(loggedUserStatsViewedEventDTO2);
    }
}
