package org.contextmapper.generated.usermanagementcontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewUserByEmailCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewUserByEmailCommandDTO.class);
        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO1 = new ViewUserByEmailCommandDTO();
        viewUserByEmailCommandDTO1.setId(1L);
        ViewUserByEmailCommandDTO viewUserByEmailCommandDTO2 = new ViewUserByEmailCommandDTO();
        assertThat(viewUserByEmailCommandDTO1).isNotEqualTo(viewUserByEmailCommandDTO2);
        viewUserByEmailCommandDTO2.setId(viewUserByEmailCommandDTO1.getId());
        assertThat(viewUserByEmailCommandDTO1).isEqualTo(viewUserByEmailCommandDTO2);
        viewUserByEmailCommandDTO2.setId(2L);
        assertThat(viewUserByEmailCommandDTO1).isNotEqualTo(viewUserByEmailCommandDTO2);
        viewUserByEmailCommandDTO1.setId(null);
        assertThat(viewUserByEmailCommandDTO1).isNotEqualTo(viewUserByEmailCommandDTO2);
    }
}
