package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnsweringUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnsweringUserDTO.class);
        AnsweringUserDTO answeringUserDTO1 = new AnsweringUserDTO();
        answeringUserDTO1.setId(1L);
        AnsweringUserDTO answeringUserDTO2 = new AnsweringUserDTO();
        assertThat(answeringUserDTO1).isNotEqualTo(answeringUserDTO2);
        answeringUserDTO2.setId(answeringUserDTO1.getId());
        assertThat(answeringUserDTO1).isEqualTo(answeringUserDTO2);
        answeringUserDTO2.setId(2L);
        assertThat(answeringUserDTO1).isNotEqualTo(answeringUserDTO2);
        answeringUserDTO1.setId(null);
        assertThat(answeringUserDTO1).isNotEqualTo(answeringUserDTO2);
    }
}
