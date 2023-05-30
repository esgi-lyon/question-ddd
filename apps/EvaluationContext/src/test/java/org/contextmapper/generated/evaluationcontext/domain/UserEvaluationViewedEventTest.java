package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserEvaluationViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserEvaluationViewedEvent.class);
        UserEvaluationViewedEvent userEvaluationViewedEvent1 = new UserEvaluationViewedEvent();
        userEvaluationViewedEvent1.setId(1L);
        UserEvaluationViewedEvent userEvaluationViewedEvent2 = new UserEvaluationViewedEvent();
        userEvaluationViewedEvent2.setId(userEvaluationViewedEvent1.getId());
        assertThat(userEvaluationViewedEvent1).isEqualTo(userEvaluationViewedEvent2);
        userEvaluationViewedEvent2.setId(2L);
        assertThat(userEvaluationViewedEvent1).isNotEqualTo(userEvaluationViewedEvent2);
        userEvaluationViewedEvent1.setId(null);
        assertThat(userEvaluationViewedEvent1).isNotEqualTo(userEvaluationViewedEvent2);
    }
}
