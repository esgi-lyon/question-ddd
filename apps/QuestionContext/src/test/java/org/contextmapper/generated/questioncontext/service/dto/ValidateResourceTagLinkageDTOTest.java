package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ValidateResourceTagLinkageDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValidateResourceTagLinkageDTO.class);
        ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO1 = new ValidateResourceTagLinkageDTO();
        validateResourceTagLinkageDTO1.setId(1L);
        ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO2 = new ValidateResourceTagLinkageDTO();
        assertThat(validateResourceTagLinkageDTO1).isNotEqualTo(validateResourceTagLinkageDTO2);
        validateResourceTagLinkageDTO2.setId(validateResourceTagLinkageDTO1.getId());
        assertThat(validateResourceTagLinkageDTO1).isEqualTo(validateResourceTagLinkageDTO2);
        validateResourceTagLinkageDTO2.setId(2L);
        assertThat(validateResourceTagLinkageDTO1).isNotEqualTo(validateResourceTagLinkageDTO2);
        validateResourceTagLinkageDTO1.setId(null);
        assertThat(validateResourceTagLinkageDTO1).isNotEqualTo(validateResourceTagLinkageDTO2);
    }
}
