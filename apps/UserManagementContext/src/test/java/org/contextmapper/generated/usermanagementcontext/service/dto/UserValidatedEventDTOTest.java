package org.contextmapper.generated.usermanagementcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserValidatedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserValidatedEventDTO.class);
        UserValidatedEventDTO userValidatedEventDTO1 = new UserValidatedEventDTO();
        userValidatedEventDTO1.setId(1L);
        UserValidatedEventDTO userValidatedEventDTO2 = new UserValidatedEventDTO();
        assertThat(userValidatedEventDTO1).isNotEqualTo(userValidatedEventDTO2);
        userValidatedEventDTO2.setId(userValidatedEventDTO1.getId());
        assertThat(userValidatedEventDTO1).isEqualTo(userValidatedEventDTO2);
        userValidatedEventDTO2.setId(2L);
        assertThat(userValidatedEventDTO1).isNotEqualTo(userValidatedEventDTO2);
        userValidatedEventDTO1.setId(null);
        assertThat(userValidatedEventDTO1).isNotEqualTo(userValidatedEventDTO2);
    }
}
