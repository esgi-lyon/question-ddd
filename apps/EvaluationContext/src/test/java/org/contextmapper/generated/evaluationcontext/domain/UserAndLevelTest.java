package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserAndLevelTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserAndLevel.class);
        UserAndLevel userAndLevel1 = new UserAndLevel();
        userAndLevel1.setId(1L);
        UserAndLevel userAndLevel2 = new UserAndLevel();
        userAndLevel2.setId(userAndLevel1.getId());
        assertThat(userAndLevel1).isEqualTo(userAndLevel2);
        userAndLevel2.setId(2L);
        assertThat(userAndLevel1).isNotEqualTo(userAndLevel2);
        userAndLevel1.setId(null);
        assertThat(userAndLevel1).isNotEqualTo(userAndLevel2);
    }
}
