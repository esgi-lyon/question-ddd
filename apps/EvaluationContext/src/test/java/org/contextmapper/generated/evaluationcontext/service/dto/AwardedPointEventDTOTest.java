package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AwardedPointEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AwardedPointEventDTO.class);
        AwardedPointEventDTO awardedPointEventDTO1 = new AwardedPointEventDTO();
        awardedPointEventDTO1.setId(1L);
        AwardedPointEventDTO awardedPointEventDTO2 = new AwardedPointEventDTO();
        assertThat(awardedPointEventDTO1).isNotEqualTo(awardedPointEventDTO2);
        awardedPointEventDTO2.setId(awardedPointEventDTO1.getId());
        assertThat(awardedPointEventDTO1).isEqualTo(awardedPointEventDTO2);
        awardedPointEventDTO2.setId(2L);
        assertThat(awardedPointEventDTO1).isNotEqualTo(awardedPointEventDTO2);
        awardedPointEventDTO1.setId(null);
        assertThat(awardedPointEventDTO1).isNotEqualTo(awardedPointEventDTO2);
    }
}
