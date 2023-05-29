package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewTagsForQuestionCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewTagsForQuestionCommandDTO.class);
        ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO1 = new ViewTagsForQuestionCommandDTO();
        viewTagsForQuestionCommandDTO1.setId(1L);
        ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO2 = new ViewTagsForQuestionCommandDTO();
        assertThat(viewTagsForQuestionCommandDTO1).isNotEqualTo(viewTagsForQuestionCommandDTO2);
        viewTagsForQuestionCommandDTO2.setId(viewTagsForQuestionCommandDTO1.getId());
        assertThat(viewTagsForQuestionCommandDTO1).isEqualTo(viewTagsForQuestionCommandDTO2);
        viewTagsForQuestionCommandDTO2.setId(2L);
        assertThat(viewTagsForQuestionCommandDTO1).isNotEqualTo(viewTagsForQuestionCommandDTO2);
        viewTagsForQuestionCommandDTO1.setId(null);
        assertThat(viewTagsForQuestionCommandDTO1).isNotEqualTo(viewTagsForQuestionCommandDTO2);
    }
}
