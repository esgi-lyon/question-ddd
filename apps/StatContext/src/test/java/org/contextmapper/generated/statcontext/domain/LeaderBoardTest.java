package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LeaderBoardTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaderBoard.class);
        LeaderBoard leaderBoard1 = new LeaderBoard();
        leaderBoard1.setId(1L);
        LeaderBoard leaderBoard2 = new LeaderBoard();
        leaderBoard2.setId(leaderBoard1.getId());
        assertThat(leaderBoard1).isEqualTo(leaderBoard2);
        leaderBoard2.setId(2L);
        assertThat(leaderBoard1).isNotEqualTo(leaderBoard2);
        leaderBoard1.setId(null);
        assertThat(leaderBoard1).isNotEqualTo(leaderBoard2);
    }
}
