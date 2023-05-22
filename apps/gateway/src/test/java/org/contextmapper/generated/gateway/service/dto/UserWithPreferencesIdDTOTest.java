package org.contextmapper.generated.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserWithPreferencesIdDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserWithPreferencesIdDTO.class);
        UserWithPreferencesIdDTO userWithPreferencesIdDTO1 = new UserWithPreferencesIdDTO();
        userWithPreferencesIdDTO1.setId(1L);
        UserWithPreferencesIdDTO userWithPreferencesIdDTO2 = new UserWithPreferencesIdDTO();
        assertThat(userWithPreferencesIdDTO1).isNotEqualTo(userWithPreferencesIdDTO2);
        userWithPreferencesIdDTO2.setId(userWithPreferencesIdDTO1.getId());
        assertThat(userWithPreferencesIdDTO1).isEqualTo(userWithPreferencesIdDTO2);
        userWithPreferencesIdDTO2.setId(2L);
        assertThat(userWithPreferencesIdDTO1).isNotEqualTo(userWithPreferencesIdDTO2);
        userWithPreferencesIdDTO1.setId(null);
        assertThat(userWithPreferencesIdDTO1).isNotEqualTo(userWithPreferencesIdDTO2);
    }
}
