package org.contextmapper.generated.answercontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.answercontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserIdDTO.class);
        UserIdDTO userIdDTO1 = new UserIdDTO();
        userIdDTO1.setId(1L);
        UserIdDTO userIdDTO2 = new UserIdDTO();
        assertThat(userIdDTO1).isNotEqualTo(userIdDTO2);
        userIdDTO2.setId(userIdDTO1.getId());
        assertThat(userIdDTO1).isEqualTo(userIdDTO2);
        userIdDTO2.setId(2L);
        assertThat(userIdDTO1).isNotEqualTo(userIdDTO2);
        userIdDTO1.setId(null);
        assertThat(userIdDTO1).isNotEqualTo(userIdDTO2);
    }
}
