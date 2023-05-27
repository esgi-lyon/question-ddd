package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AddPreferencesCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddPreferencesCommand.class);
        AddPreferencesCommand addPreferencesCommand1 = new AddPreferencesCommand();
        addPreferencesCommand1.setId(1L);
        AddPreferencesCommand addPreferencesCommand2 = new AddPreferencesCommand();
        addPreferencesCommand2.setId(addPreferencesCommand1.getId());
        assertThat(addPreferencesCommand1).isEqualTo(addPreferencesCommand2);
        addPreferencesCommand2.setId(2L);
        assertThat(addPreferencesCommand1).isNotEqualTo(addPreferencesCommand2);
        addPreferencesCommand1.setId(null);
        assertThat(addPreferencesCommand1).isNotEqualTo(addPreferencesCommand2);
    }
}
