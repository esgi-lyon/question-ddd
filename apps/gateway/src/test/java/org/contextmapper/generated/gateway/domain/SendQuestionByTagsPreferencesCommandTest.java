package org.contextmapper.generated.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SendQuestionByTagsPreferencesCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SendQuestionByTagsPreferencesCommand.class);
        SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand1 = new SendQuestionByTagsPreferencesCommand();
        sendQuestionByTagsPreferencesCommand1.setId(1L);
        SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand2 = new SendQuestionByTagsPreferencesCommand();
        sendQuestionByTagsPreferencesCommand2.setId(sendQuestionByTagsPreferencesCommand1.getId());
        assertThat(sendQuestionByTagsPreferencesCommand1).isEqualTo(sendQuestionByTagsPreferencesCommand2);
        sendQuestionByTagsPreferencesCommand2.setId(2L);
        assertThat(sendQuestionByTagsPreferencesCommand1).isNotEqualTo(sendQuestionByTagsPreferencesCommand2);
        sendQuestionByTagsPreferencesCommand1.setId(null);
        assertThat(sendQuestionByTagsPreferencesCommand1).isNotEqualTo(sendQuestionByTagsPreferencesCommand2);
    }
}
