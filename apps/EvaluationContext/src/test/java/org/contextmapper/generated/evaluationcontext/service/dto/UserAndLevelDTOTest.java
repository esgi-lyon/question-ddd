package org.contextmapper.generated.evaluationcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserAndLevelDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserAndLevelDTO.class);
        UserAndLevelDTO userAndLevelDTO1 = new UserAndLevelDTO();
        userAndLevelDTO1.setId(1L);
        UserAndLevelDTO userAndLevelDTO2 = new UserAndLevelDTO();
        assertThat(userAndLevelDTO1).isNotEqualTo(userAndLevelDTO2);
        userAndLevelDTO2.setId(userAndLevelDTO1.getId());
        assertThat(userAndLevelDTO1).isEqualTo(userAndLevelDTO2);
        userAndLevelDTO2.setId(2L);
        assertThat(userAndLevelDTO1).isNotEqualTo(userAndLevelDTO2);
        userAndLevelDTO1.setId(null);
        assertThat(userAndLevelDTO1).isNotEqualTo(userAndLevelDTO2);
    }
}
