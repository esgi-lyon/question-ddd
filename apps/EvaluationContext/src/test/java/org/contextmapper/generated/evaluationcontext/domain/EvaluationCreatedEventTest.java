package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationCreatedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationCreatedEvent.class);
        EvaluationCreatedEvent evaluationCreatedEvent1 = new EvaluationCreatedEvent();
        evaluationCreatedEvent1.setId(1L);
        EvaluationCreatedEvent evaluationCreatedEvent2 = new EvaluationCreatedEvent();
        evaluationCreatedEvent2.setId(evaluationCreatedEvent1.getId());
        assertThat(evaluationCreatedEvent1).isEqualTo(evaluationCreatedEvent2);
        evaluationCreatedEvent2.setId(2L);
        assertThat(evaluationCreatedEvent1).isNotEqualTo(evaluationCreatedEvent2);
        evaluationCreatedEvent1.setId(null);
        assertThat(evaluationCreatedEvent1).isNotEqualTo(evaluationCreatedEvent2);
    }
}
