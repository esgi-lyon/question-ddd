package org.contextmapper.generated.statcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.statcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StatEvaluationTagTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatEvaluationTag.class);
        StatEvaluationTag statEvaluationTag1 = new StatEvaluationTag();
        statEvaluationTag1.setId(1L);
        StatEvaluationTag statEvaluationTag2 = new StatEvaluationTag();
        statEvaluationTag2.setId(statEvaluationTag1.getId());
        assertThat(statEvaluationTag1).isEqualTo(statEvaluationTag2);
        statEvaluationTag2.setId(2L);
        assertThat(statEvaluationTag1).isNotEqualTo(statEvaluationTag2);
        statEvaluationTag1.setId(null);
        assertThat(statEvaluationTag1).isNotEqualTo(statEvaluationTag2);
    }
}
