package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SendByPreferencesCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SendByPreferencesCommandDTO.class);
        SendByPreferencesCommandDTO sendByPreferencesCommandDTO1 = new SendByPreferencesCommandDTO();
        sendByPreferencesCommandDTO1.setId(1L);
        SendByPreferencesCommandDTO sendByPreferencesCommandDTO2 = new SendByPreferencesCommandDTO();
        assertThat(sendByPreferencesCommandDTO1).isNotEqualTo(sendByPreferencesCommandDTO2);
        sendByPreferencesCommandDTO2.setId(sendByPreferencesCommandDTO1.getId());
        assertThat(sendByPreferencesCommandDTO1).isEqualTo(sendByPreferencesCommandDTO2);
        sendByPreferencesCommandDTO2.setId(2L);
        assertThat(sendByPreferencesCommandDTO1).isNotEqualTo(sendByPreferencesCommandDTO2);
        sendByPreferencesCommandDTO1.setId(null);
        assertThat(sendByPreferencesCommandDTO1).isNotEqualTo(sendByPreferencesCommandDTO2);
    }
}
