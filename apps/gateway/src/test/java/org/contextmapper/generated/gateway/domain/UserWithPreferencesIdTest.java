package org.contextmapper.generated.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserWithPreferencesIdTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserWithPreferencesId.class);
        UserWithPreferencesId userWithPreferencesId1 = new UserWithPreferencesId();
        userWithPreferencesId1.setId(1L);
        UserWithPreferencesId userWithPreferencesId2 = new UserWithPreferencesId();
        userWithPreferencesId2.setId(userWithPreferencesId1.getId());
        assertThat(userWithPreferencesId1).isEqualTo(userWithPreferencesId2);
        userWithPreferencesId2.setId(2L);
        assertThat(userWithPreferencesId1).isNotEqualTo(userWithPreferencesId2);
        userWithPreferencesId1.setId(null);
        assertThat(userWithPreferencesId1).isNotEqualTo(userWithPreferencesId2);
    }
}
