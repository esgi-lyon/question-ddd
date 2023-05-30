package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserEvaluationViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserEvaluationViewedEventDTO.class);
        UserEvaluationViewedEventDTO userEvaluationViewedEventDTO1 = new UserEvaluationViewedEventDTO();
        userEvaluationViewedEventDTO1.setId(1L);
        UserEvaluationViewedEventDTO userEvaluationViewedEventDTO2 = new UserEvaluationViewedEventDTO();
        assertThat(userEvaluationViewedEventDTO1).isNotEqualTo(userEvaluationViewedEventDTO2);
        userEvaluationViewedEventDTO2.setId(userEvaluationViewedEventDTO1.getId());
        assertThat(userEvaluationViewedEventDTO1).isEqualTo(userEvaluationViewedEventDTO2);
        userEvaluationViewedEventDTO2.setId(2L);
        assertThat(userEvaluationViewedEventDTO1).isNotEqualTo(userEvaluationViewedEventDTO2);
        userEvaluationViewedEventDTO1.setId(null);
        assertThat(userEvaluationViewedEventDTO1).isNotEqualTo(userEvaluationViewedEventDTO2);
    }
}
