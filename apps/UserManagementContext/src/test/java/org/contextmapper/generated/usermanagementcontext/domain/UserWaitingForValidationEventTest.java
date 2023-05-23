package org.contextmapper.generated.usermanagementcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserWaitingForValidationEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserWaitingForValidationEvent.class);
        UserWaitingForValidationEvent userWaitingForValidationEvent1 = new UserWaitingForValidationEvent();
        userWaitingForValidationEvent1.setId(1L);
        UserWaitingForValidationEvent userWaitingForValidationEvent2 = new UserWaitingForValidationEvent();
        userWaitingForValidationEvent2.setId(userWaitingForValidationEvent1.getId());
        assertThat(userWaitingForValidationEvent1).isEqualTo(userWaitingForValidationEvent2);
        userWaitingForValidationEvent2.setId(2L);
        assertThat(userWaitingForValidationEvent1).isNotEqualTo(userWaitingForValidationEvent2);
        userWaitingForValidationEvent1.setId(null);
        assertThat(userWaitingForValidationEvent1).isNotEqualTo(userWaitingForValidationEvent2);
    }
}
