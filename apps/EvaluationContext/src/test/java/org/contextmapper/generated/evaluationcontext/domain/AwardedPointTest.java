package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AwardedPointTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AwardedPoint.class);
        AwardedPoint awardedPoint1 = new AwardedPoint();
        awardedPoint1.setId(1L);
        AwardedPoint awardedPoint2 = new AwardedPoint();
        awardedPoint2.setId(awardedPoint1.getId());
        assertThat(awardedPoint1).isEqualTo(awardedPoint2);
        awardedPoint2.setId(2L);
        assertThat(awardedPoint1).isNotEqualTo(awardedPoint2);
        awardedPoint1.setId(null);
        assertThat(awardedPoint1).isNotEqualTo(awardedPoint2);
    }
}
