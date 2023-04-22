package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AwardedPointEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AwardedPointEvent.class);
        AwardedPointEvent awardedPointEvent1 = new AwardedPointEvent();
        awardedPointEvent1.setId(1L);
        AwardedPointEvent awardedPointEvent2 = new AwardedPointEvent();
        awardedPointEvent2.setId(awardedPointEvent1.getId());
        assertThat(awardedPointEvent1).isEqualTo(awardedPointEvent2);
        awardedPointEvent2.setId(2L);
        assertThat(awardedPointEvent1).isNotEqualTo(awardedPointEvent2);
        awardedPointEvent1.setId(null);
        assertThat(awardedPointEvent1).isNotEqualTo(awardedPointEvent2);
    }
}
