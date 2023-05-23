package org.contextmapper.generated.usermanagementcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserWaitingForValidationEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserWaitingForValidationEventDTO.class);
        UserWaitingForValidationEventDTO userWaitingForValidationEventDTO1 = new UserWaitingForValidationEventDTO();
        userWaitingForValidationEventDTO1.setId(1L);
        UserWaitingForValidationEventDTO userWaitingForValidationEventDTO2 = new UserWaitingForValidationEventDTO();
        assertThat(userWaitingForValidationEventDTO1).isNotEqualTo(userWaitingForValidationEventDTO2);
        userWaitingForValidationEventDTO2.setId(userWaitingForValidationEventDTO1.getId());
        assertThat(userWaitingForValidationEventDTO1).isEqualTo(userWaitingForValidationEventDTO2);
        userWaitingForValidationEventDTO2.setId(2L);
        assertThat(userWaitingForValidationEventDTO1).isNotEqualTo(userWaitingForValidationEventDTO2);
        userWaitingForValidationEventDTO1.setId(null);
        assertThat(userWaitingForValidationEventDTO1).isNotEqualTo(userWaitingForValidationEventDTO2);
    }
}
