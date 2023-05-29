package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LeaderBoardViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaderBoardViewedEvent.class);
        LeaderBoardViewedEvent leaderBoardViewedEvent1 = new LeaderBoardViewedEvent();
        leaderBoardViewedEvent1.setId(1L);
        LeaderBoardViewedEvent leaderBoardViewedEvent2 = new LeaderBoardViewedEvent();
        leaderBoardViewedEvent2.setId(leaderBoardViewedEvent1.getId());
        assertThat(leaderBoardViewedEvent1).isEqualTo(leaderBoardViewedEvent2);
        leaderBoardViewedEvent2.setId(2L);
        assertThat(leaderBoardViewedEvent1).isNotEqualTo(leaderBoardViewedEvent2);
        leaderBoardViewedEvent1.setId(null);
        assertThat(leaderBoardViewedEvent1).isNotEqualTo(leaderBoardViewedEvent2);
    }
}
