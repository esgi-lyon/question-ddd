package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PreferencesAddedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PreferencesAddedEventDTO.class);
        PreferencesAddedEventDTO preferencesAddedEventDTO1 = new PreferencesAddedEventDTO();
        preferencesAddedEventDTO1.setId(1L);
        PreferencesAddedEventDTO preferencesAddedEventDTO2 = new PreferencesAddedEventDTO();
        assertThat(preferencesAddedEventDTO1).isNotEqualTo(preferencesAddedEventDTO2);
        preferencesAddedEventDTO2.setId(preferencesAddedEventDTO1.getId());
        assertThat(preferencesAddedEventDTO1).isEqualTo(preferencesAddedEventDTO2);
        preferencesAddedEventDTO2.setId(2L);
        assertThat(preferencesAddedEventDTO1).isNotEqualTo(preferencesAddedEventDTO2);
        preferencesAddedEventDTO1.setId(null);
        assertThat(preferencesAddedEventDTO1).isNotEqualTo(preferencesAddedEventDTO2);
    }
}
