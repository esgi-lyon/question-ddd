package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrepareQuestionCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrepareQuestionCommandDTO.class);
        PrepareQuestionCommandDTO prepareQuestionCommandDTO1 = new PrepareQuestionCommandDTO();
        prepareQuestionCommandDTO1.setId(1L);
        PrepareQuestionCommandDTO prepareQuestionCommandDTO2 = new PrepareQuestionCommandDTO();
        assertThat(prepareQuestionCommandDTO1).isNotEqualTo(prepareQuestionCommandDTO2);
        prepareQuestionCommandDTO2.setId(prepareQuestionCommandDTO1.getId());
        assertThat(prepareQuestionCommandDTO1).isEqualTo(prepareQuestionCommandDTO2);
        prepareQuestionCommandDTO2.setId(2L);
        assertThat(prepareQuestionCommandDTO1).isNotEqualTo(prepareQuestionCommandDTO2);
        prepareQuestionCommandDTO1.setId(null);
        assertThat(prepareQuestionCommandDTO1).isNotEqualTo(prepareQuestionCommandDTO2);
    }
}
