package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserStatsViewedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserStatsViewed.class);
        UserStatsViewed userStatsViewed1 = new UserStatsViewed();
        userStatsViewed1.setId(1L);
        UserStatsViewed userStatsViewed2 = new UserStatsViewed();
        userStatsViewed2.setId(userStatsViewed1.getId());
        assertThat(userStatsViewed1).isEqualTo(userStatsViewed2);
        userStatsViewed2.setId(2L);
        assertThat(userStatsViewed1).isNotEqualTo(userStatsViewed2);
        userStatsViewed1.setId(null);
        assertThat(userStatsViewed1).isNotEqualTo(userStatsViewed2);
    }
}
