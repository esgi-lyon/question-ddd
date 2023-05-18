package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SendQuestionByTagsPreferencesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SendQuestionByTagsPreferencesDTO.class);
        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO1 = new SendQuestionByTagsPreferencesDTO();
        sendQuestionByTagsPreferencesDTO1.setId(1L);
        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO2 = new SendQuestionByTagsPreferencesDTO();
        assertThat(sendQuestionByTagsPreferencesDTO1).isNotEqualTo(sendQuestionByTagsPreferencesDTO2);
        sendQuestionByTagsPreferencesDTO2.setId(sendQuestionByTagsPreferencesDTO1.getId());
        assertThat(sendQuestionByTagsPreferencesDTO1).isEqualTo(sendQuestionByTagsPreferencesDTO2);
        sendQuestionByTagsPreferencesDTO2.setId(2L);
        assertThat(sendQuestionByTagsPreferencesDTO1).isNotEqualTo(sendQuestionByTagsPreferencesDTO2);
        sendQuestionByTagsPreferencesDTO1.setId(null);
        assertThat(sendQuestionByTagsPreferencesDTO1).isNotEqualTo(sendQuestionByTagsPreferencesDTO2);
    }
}
