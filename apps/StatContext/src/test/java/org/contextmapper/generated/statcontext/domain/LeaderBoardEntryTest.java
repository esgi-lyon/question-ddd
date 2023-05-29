package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LeaderBoardEntryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaderBoardEntry.class);
        LeaderBoardEntry leaderBoardEntry1 = new LeaderBoardEntry();
        leaderBoardEntry1.setId(1L);
        LeaderBoardEntry leaderBoardEntry2 = new LeaderBoardEntry();
        leaderBoardEntry2.setId(leaderBoardEntry1.getId());
        assertThat(leaderBoardEntry1).isEqualTo(leaderBoardEntry2);
        leaderBoardEntry2.setId(2L);
        assertThat(leaderBoardEntry1).isNotEqualTo(leaderBoardEntry2);
        leaderBoardEntry1.setId(null);
        assertThat(leaderBoardEntry1).isNotEqualTo(leaderBoardEntry2);
    }
}
