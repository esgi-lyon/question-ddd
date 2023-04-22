package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PointAwardRuleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PointAwardRule.class);
        PointAwardRule pointAwardRule1 = new PointAwardRule();
        pointAwardRule1.setId(1L);
        PointAwardRule pointAwardRule2 = new PointAwardRule();
        pointAwardRule2.setId(pointAwardRule1.getId());
        assertThat(pointAwardRule1).isEqualTo(pointAwardRule2);
        pointAwardRule2.setId(2L);
        assertThat(pointAwardRule1).isNotEqualTo(pointAwardRule2);
        pointAwardRule1.setId(null);
        assertThat(pointAwardRule1).isNotEqualTo(pointAwardRule2);
    }
}
