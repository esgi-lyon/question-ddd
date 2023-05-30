package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LoggedUserStatsViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoggedUserStatsViewedEvent.class);
        LoggedUserStatsViewedEvent loggedUserStatsViewedEvent1 = new LoggedUserStatsViewedEvent();
        loggedUserStatsViewedEvent1.setId(1L);
        LoggedUserStatsViewedEvent loggedUserStatsViewedEvent2 = new LoggedUserStatsViewedEvent();
        loggedUserStatsViewedEvent2.setId(loggedUserStatsViewedEvent1.getId());
        assertThat(loggedUserStatsViewedEvent1).isEqualTo(loggedUserStatsViewedEvent2);
        loggedUserStatsViewedEvent2.setId(2L);
        assertThat(loggedUserStatsViewedEvent1).isNotEqualTo(loggedUserStatsViewedEvent2);
        loggedUserStatsViewedEvent1.setId(null);
        assertThat(loggedUserStatsViewedEvent1).isNotEqualTo(loggedUserStatsViewedEvent2);
    }
}
