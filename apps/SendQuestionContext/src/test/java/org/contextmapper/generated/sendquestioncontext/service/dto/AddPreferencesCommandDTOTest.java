package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AddPreferencesCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddPreferencesCommandDTO.class);
        AddPreferencesCommandDTO addPreferencesCommandDTO1 = new AddPreferencesCommandDTO();
        addPreferencesCommandDTO1.setId(1L);
        AddPreferencesCommandDTO addPreferencesCommandDTO2 = new AddPreferencesCommandDTO();
        assertThat(addPreferencesCommandDTO1).isNotEqualTo(addPreferencesCommandDTO2);
        addPreferencesCommandDTO2.setId(addPreferencesCommandDTO1.getId());
        assertThat(addPreferencesCommandDTO1).isEqualTo(addPreferencesCommandDTO2);
        addPreferencesCommandDTO2.setId(2L);
        assertThat(addPreferencesCommandDTO1).isNotEqualTo(addPreferencesCommandDTO2);
        addPreferencesCommandDTO1.setId(null);
        assertThat(addPreferencesCommandDTO1).isNotEqualTo(addPreferencesCommandDTO2);
    }
}
