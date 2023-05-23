package org.contextmapper.generated.usermanagementcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserRejectedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserRejectedEvent.class);
        UserRejectedEvent userRejectedEvent1 = new UserRejectedEvent();
        userRejectedEvent1.setId(1L);
        UserRejectedEvent userRejectedEvent2 = new UserRejectedEvent();
        userRejectedEvent2.setId(userRejectedEvent1.getId());
        assertThat(userRejectedEvent1).isEqualTo(userRejectedEvent2);
        userRejectedEvent2.setId(2L);
        assertThat(userRejectedEvent1).isNotEqualTo(userRejectedEvent2);
        userRejectedEvent1.setId(null);
        assertThat(userRejectedEvent1).isNotEqualTo(userRejectedEvent2);
    }
}
