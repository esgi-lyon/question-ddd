package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SendQuestionByTagsPreferencesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SendQuestionByTagsPreferences.class);
        SendQuestionByTagsPreferences sendQuestionByTagsPreferences1 = new SendQuestionByTagsPreferences();
        sendQuestionByTagsPreferences1.setId(1L);
        SendQuestionByTagsPreferences sendQuestionByTagsPreferences2 = new SendQuestionByTagsPreferences();
        sendQuestionByTagsPreferences2.setId(sendQuestionByTagsPreferences1.getId());
        assertThat(sendQuestionByTagsPreferences1).isEqualTo(sendQuestionByTagsPreferences2);
        sendQuestionByTagsPreferences2.setId(2L);
        assertThat(sendQuestionByTagsPreferences1).isNotEqualTo(sendQuestionByTagsPreferences2);
        sendQuestionByTagsPreferences1.setId(null);
        assertThat(sendQuestionByTagsPreferences1).isNotEqualTo(sendQuestionByTagsPreferences2);
    }
}
