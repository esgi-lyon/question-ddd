package org.contextmapper.generated.usermanagementcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RegisterCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegisterCommandDTO.class);
        RegisterCommandDTO registerCommandDTO1 = new RegisterCommandDTO();
        registerCommandDTO1.setId(1L);
        RegisterCommandDTO registerCommandDTO2 = new RegisterCommandDTO();
        assertThat(registerCommandDTO1).isNotEqualTo(registerCommandDTO2);
        registerCommandDTO2.setId(registerCommandDTO1.getId());
        assertThat(registerCommandDTO1).isEqualTo(registerCommandDTO2);
        registerCommandDTO2.setId(2L);
        assertThat(registerCommandDTO1).isNotEqualTo(registerCommandDTO2);
        registerCommandDTO1.setId(null);
        assertThat(registerCommandDTO1).isNotEqualTo(registerCommandDTO2);
    }
}
