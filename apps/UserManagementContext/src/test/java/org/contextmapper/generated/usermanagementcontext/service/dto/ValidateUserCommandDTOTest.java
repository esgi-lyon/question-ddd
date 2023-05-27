package org.contextmapper.generated.usermanagementcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ValidateUserCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValidateUserCommandDTO.class);
        ValidateUserCommandDTO validateUserCommandDTO1 = new ValidateUserCommandDTO();
        validateUserCommandDTO1.setId(1L);
        ValidateUserCommandDTO validateUserCommandDTO2 = new ValidateUserCommandDTO();
        assertThat(validateUserCommandDTO1).isNotEqualTo(validateUserCommandDTO2);
        validateUserCommandDTO2.setId(validateUserCommandDTO1.getId());
        assertThat(validateUserCommandDTO1).isEqualTo(validateUserCommandDTO2);
        validateUserCommandDTO2.setId(2L);
        assertThat(validateUserCommandDTO1).isNotEqualTo(validateUserCommandDTO2);
        validateUserCommandDTO1.setId(null);
        assertThat(validateUserCommandDTO1).isNotEqualTo(validateUserCommandDTO2);
    }
}
