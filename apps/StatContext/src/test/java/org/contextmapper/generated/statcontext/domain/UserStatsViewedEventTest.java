package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserStatsViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserStatsViewedEvent.class);
        UserStatsViewedEvent userStatsViewedEvent1 = new UserStatsViewedEvent();
        userStatsViewedEvent1.setId(1L);
        UserStatsViewedEvent userStatsViewedEvent2 = new UserStatsViewedEvent();
        userStatsViewedEvent2.setId(userStatsViewedEvent1.getId());
        assertThat(userStatsViewedEvent1).isEqualTo(userStatsViewedEvent2);
        userStatsViewedEvent2.setId(2L);
        assertThat(userStatsViewedEvent1).isNotEqualTo(userStatsViewedEvent2);
        userStatsViewedEvent1.setId(null);
        assertThat(userStatsViewedEvent1).isNotEqualTo(userStatsViewedEvent2);
    }
}
