package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TagEvaluationViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagEvaluationViewedEventDTO.class);
        TagEvaluationViewedEventDTO tagEvaluationViewedEventDTO1 = new TagEvaluationViewedEventDTO();
        tagEvaluationViewedEventDTO1.setId(1L);
        TagEvaluationViewedEventDTO tagEvaluationViewedEventDTO2 = new TagEvaluationViewedEventDTO();
        assertThat(tagEvaluationViewedEventDTO1).isNotEqualTo(tagEvaluationViewedEventDTO2);
        tagEvaluationViewedEventDTO2.setId(tagEvaluationViewedEventDTO1.getId());
        assertThat(tagEvaluationViewedEventDTO1).isEqualTo(tagEvaluationViewedEventDTO2);
        tagEvaluationViewedEventDTO2.setId(2L);
        assertThat(tagEvaluationViewedEventDTO1).isNotEqualTo(tagEvaluationViewedEventDTO2);
        tagEvaluationViewedEventDTO1.setId(null);
        assertThat(tagEvaluationViewedEventDTO1).isNotEqualTo(tagEvaluationViewedEventDTO2);
    }
}
