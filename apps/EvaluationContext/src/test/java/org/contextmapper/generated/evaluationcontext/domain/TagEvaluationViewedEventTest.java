package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagEvaluationViewedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagEvaluationViewedEvent.class);
        TagEvaluationViewedEvent tagEvaluationViewedEvent1 = new TagEvaluationViewedEvent();
        tagEvaluationViewedEvent1.setId(1L);
        TagEvaluationViewedEvent tagEvaluationViewedEvent2 = new TagEvaluationViewedEvent();
        tagEvaluationViewedEvent2.setId(tagEvaluationViewedEvent1.getId());
        assertThat(tagEvaluationViewedEvent1).isEqualTo(tagEvaluationViewedEvent2);
        tagEvaluationViewedEvent2.setId(2L);
        assertThat(tagEvaluationViewedEvent1).isNotEqualTo(tagEvaluationViewedEvent2);
        tagEvaluationViewedEvent1.setId(null);
        assertThat(tagEvaluationViewedEvent1).isNotEqualTo(tagEvaluationViewedEvent2);
    }
}
