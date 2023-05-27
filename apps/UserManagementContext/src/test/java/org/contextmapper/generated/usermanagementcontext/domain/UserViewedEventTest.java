package org.contextmapper.generated.usermanagementcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserViewedEvent.class);
        UserViewedEvent userViewedEvent1 = new UserViewedEvent();
        userViewedEvent1.setId(1L);
        UserViewedEvent userViewedEvent2 = new UserViewedEvent();
        userViewedEvent2.setId(userViewedEvent1.getId());
        assertThat(userViewedEvent1).isEqualTo(userViewedEvent2);
        userViewedEvent2.setId(2L);
        assertThat(userViewedEvent1).isNotEqualTo(userViewedEvent2);
        userViewedEvent1.setId(null);
        assertThat(userViewedEvent1).isNotEqualTo(userViewedEvent2);
    }
}
