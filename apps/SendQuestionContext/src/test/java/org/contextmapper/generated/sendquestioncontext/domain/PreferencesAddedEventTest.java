package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PreferencesAddedEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PreferencesAddedEvent.class);
        PreferencesAddedEvent preferencesAddedEvent1 = new PreferencesAddedEvent();
        preferencesAddedEvent1.setId(1L);
        PreferencesAddedEvent preferencesAddedEvent2 = new PreferencesAddedEvent();
        preferencesAddedEvent2.setId(preferencesAddedEvent1.getId());
        assertThat(preferencesAddedEvent1).isEqualTo(preferencesAddedEvent2);
        preferencesAddedEvent2.setId(2L);
        assertThat(preferencesAddedEvent1).isNotEqualTo(preferencesAddedEvent2);
        preferencesAddedEvent1.setId(null);
        assertThat(preferencesAddedEvent1).isNotEqualTo(preferencesAddedEvent2);
    }
}
