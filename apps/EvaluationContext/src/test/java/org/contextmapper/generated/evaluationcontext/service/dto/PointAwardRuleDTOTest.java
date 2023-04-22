package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PointAwardRuleDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PointAwardRuleDTO.class);
        PointAwardRuleDTO pointAwardRuleDTO1 = new PointAwardRuleDTO();
        pointAwardRuleDTO1.setId(1L);
        PointAwardRuleDTO pointAwardRuleDTO2 = new PointAwardRuleDTO();
        assertThat(pointAwardRuleDTO1).isNotEqualTo(pointAwardRuleDTO2);
        pointAwardRuleDTO2.setId(pointAwardRuleDTO1.getId());
        assertThat(pointAwardRuleDTO1).isEqualTo(pointAwardRuleDTO2);
        pointAwardRuleDTO2.setId(2L);
        assertThat(pointAwardRuleDTO1).isNotEqualTo(pointAwardRuleDTO2);
        pointAwardRuleDTO1.setId(null);
        assertThat(pointAwardRuleDTO1).isNotEqualTo(pointAwardRuleDTO2);
    }
}
