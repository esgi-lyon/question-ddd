package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionEvaluationViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionEvaluationViewedEventDTO.class);
        QuestionEvaluationViewedEventDTO questionEvaluationViewedEventDTO1 = new QuestionEvaluationViewedEventDTO();
        questionEvaluationViewedEventDTO1.setId(1L);
        QuestionEvaluationViewedEventDTO questionEvaluationViewedEventDTO2 = new QuestionEvaluationViewedEventDTO();
        assertThat(questionEvaluationViewedEventDTO1).isNotEqualTo(questionEvaluationViewedEventDTO2);
        questionEvaluationViewedEventDTO2.setId(questionEvaluationViewedEventDTO1.getId());
        assertThat(questionEvaluationViewedEventDTO1).isEqualTo(questionEvaluationViewedEventDTO2);
        questionEvaluationViewedEventDTO2.setId(2L);
        assertThat(questionEvaluationViewedEventDTO1).isNotEqualTo(questionEvaluationViewedEventDTO2);
        questionEvaluationViewedEventDTO1.setId(null);
        assertThat(questionEvaluationViewedEventDTO1).isNotEqualTo(questionEvaluationViewedEventDTO2);
    }
}
