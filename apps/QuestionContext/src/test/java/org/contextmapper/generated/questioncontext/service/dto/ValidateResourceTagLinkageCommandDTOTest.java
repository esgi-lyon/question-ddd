package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ValidateResourceTagLinkageCommandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValidateResourceTagLinkageCommandDTO.class);
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO1 = new ValidateResourceTagLinkageCommandDTO();
        validateResourceTagLinkageCommandDTO1.setId(1L);
        ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO2 = new ValidateResourceTagLinkageCommandDTO();
        assertThat(validateResourceTagLinkageCommandDTO1).isNotEqualTo(validateResourceTagLinkageCommandDTO2);
        validateResourceTagLinkageCommandDTO2.setId(validateResourceTagLinkageCommandDTO1.getId());
        assertThat(validateResourceTagLinkageCommandDTO1).isEqualTo(validateResourceTagLinkageCommandDTO2);
        validateResourceTagLinkageCommandDTO2.setId(2L);
        assertThat(validateResourceTagLinkageCommandDTO1).isNotEqualTo(validateResourceTagLinkageCommandDTO2);
        validateResourceTagLinkageCommandDTO1.setId(null);
        assertThat(validateResourceTagLinkageCommandDTO1).isNotEqualTo(validateResourceTagLinkageCommandDTO2);
    }
}
