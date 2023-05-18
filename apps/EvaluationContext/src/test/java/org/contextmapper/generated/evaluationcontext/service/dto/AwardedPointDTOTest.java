package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AwardedPointDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AwardedPointDTO.class);
        AwardedPointDTO awardedPointDTO1 = new AwardedPointDTO();
        awardedPointDTO1.setId(1L);
        AwardedPointDTO awardedPointDTO2 = new AwardedPointDTO();
        assertThat(awardedPointDTO1).isNotEqualTo(awardedPointDTO2);
        awardedPointDTO2.setId(awardedPointDTO1.getId());
        assertThat(awardedPointDTO1).isEqualTo(awardedPointDTO2);
        awardedPointDTO2.setId(2L);
        assertThat(awardedPointDTO1).isNotEqualTo(awardedPointDTO2);
        awardedPointDTO1.setId(null);
        assertThat(awardedPointDTO1).isNotEqualTo(awardedPointDTO2);
    }
}
