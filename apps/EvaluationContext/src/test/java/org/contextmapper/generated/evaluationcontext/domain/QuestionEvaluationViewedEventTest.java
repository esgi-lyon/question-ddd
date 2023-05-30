package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionEvaluationViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionEvaluationViewedEvent.class);
        QuestionEvaluationViewedEvent questionEvaluationViewedEvent1 = new QuestionEvaluationViewedEvent();
        questionEvaluationViewedEvent1.setId(1L);
        QuestionEvaluationViewedEvent questionEvaluationViewedEvent2 = new QuestionEvaluationViewedEvent();
        questionEvaluationViewedEvent2.setId(questionEvaluationViewedEvent1.getId());
        assertThat(questionEvaluationViewedEvent1).isEqualTo(questionEvaluationViewedEvent2);
        questionEvaluationViewedEvent2.setId(2L);
        assertThat(questionEvaluationViewedEvent1).isNotEqualTo(questionEvaluationViewedEvent2);
        questionEvaluationViewedEvent1.setId(null);
        assertThat(questionEvaluationViewedEvent1).isNotEqualTo(questionEvaluationViewedEvent2);
    }
}
