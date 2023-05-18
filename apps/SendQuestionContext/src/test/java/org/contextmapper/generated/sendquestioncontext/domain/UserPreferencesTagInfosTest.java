package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserPreferencesTagInfosTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserPreferencesTagInfos.class);
        UserPreferencesTagInfos userPreferencesTagInfos1 = new UserPreferencesTagInfos();
        userPreferencesTagInfos1.setId(1L);
        UserPreferencesTagInfos userPreferencesTagInfos2 = new UserPreferencesTagInfos();
        userPreferencesTagInfos2.setId(userPreferencesTagInfos1.getId());
        assertThat(userPreferencesTagInfos1).isEqualTo(userPreferencesTagInfos2);
        userPreferencesTagInfos2.setId(2L);
        assertThat(userPreferencesTagInfos1).isNotEqualTo(userPreferencesTagInfos2);
        userPreferencesTagInfos1.setId(null);
        assertThat(userPreferencesTagInfos1).isNotEqualTo(userPreferencesTagInfos2);
    }
}
