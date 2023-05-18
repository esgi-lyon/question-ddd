package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserPreferencesTagInfosDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserPreferencesTagInfosDTO.class);
        UserPreferencesTagInfosDTO userPreferencesTagInfosDTO1 = new UserPreferencesTagInfosDTO();
        userPreferencesTagInfosDTO1.setId(1L);
        UserPreferencesTagInfosDTO userPreferencesTagInfosDTO2 = new UserPreferencesTagInfosDTO();
        assertThat(userPreferencesTagInfosDTO1).isNotEqualTo(userPreferencesTagInfosDTO2);
        userPreferencesTagInfosDTO2.setId(userPreferencesTagInfosDTO1.getId());
        assertThat(userPreferencesTagInfosDTO1).isEqualTo(userPreferencesTagInfosDTO2);
        userPreferencesTagInfosDTO2.setId(2L);
        assertThat(userPreferencesTagInfosDTO1).isNotEqualTo(userPreferencesTagInfosDTO2);
        userPreferencesTagInfosDTO1.setId(null);
        assertThat(userPreferencesTagInfosDTO1).isNotEqualTo(userPreferencesTagInfosDTO2);
    }
}
