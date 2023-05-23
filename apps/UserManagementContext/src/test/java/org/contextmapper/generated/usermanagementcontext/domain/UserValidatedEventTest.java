package org.contextmapper.generated.usermanagementcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserValidatedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserValidatedEvent.class);
        UserValidatedEvent userValidatedEvent1 = new UserValidatedEvent();
        userValidatedEvent1.setId(1L);
        UserValidatedEvent userValidatedEvent2 = new UserValidatedEvent();
        userValidatedEvent2.setId(userValidatedEvent1.getId());
        assertThat(userValidatedEvent1).isEqualTo(userValidatedEvent2);
        userValidatedEvent2.setId(2L);
        assertThat(userValidatedEvent1).isNotEqualTo(userValidatedEvent2);
        userValidatedEvent1.setId(null);
        assertThat(userValidatedEvent1).isNotEqualTo(userValidatedEvent2);
    }
}
