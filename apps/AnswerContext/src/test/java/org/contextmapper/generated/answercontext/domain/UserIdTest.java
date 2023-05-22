package org.contextmapper.generated.answercontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserId.class);
        UserId userId1 = new UserId();
        userId1.setId(1L);
        UserId userId2 = new UserId();
        userId2.setId(userId1.getId());
        assertThat(userId1).isEqualTo(userId2);
        userId2.setId(2L);
        assertThat(userId1).isNotEqualTo(userId2);
        userId1.setId(null);
        assertThat(userId1).isNotEqualTo(userId2);
    }
}
