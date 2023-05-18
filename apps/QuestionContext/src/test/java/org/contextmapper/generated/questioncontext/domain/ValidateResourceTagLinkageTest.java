package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ValidateResourceTagLinkageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValidateResourceTagLinkage.class);
        ValidateResourceTagLinkage validateResourceTagLinkage1 = new ValidateResourceTagLinkage();
        validateResourceTagLinkage1.setId(1L);
        ValidateResourceTagLinkage validateResourceTagLinkage2 = new ValidateResourceTagLinkage();
        validateResourceTagLinkage2.setId(validateResourceTagLinkage1.getId());
        assertThat(validateResourceTagLinkage1).isEqualTo(validateResourceTagLinkage2);
        validateResourceTagLinkage2.setId(2L);
        assertThat(validateResourceTagLinkage1).isNotEqualTo(validateResourceTagLinkage2);
        validateResourceTagLinkage1.setId(null);
        assertThat(validateResourceTagLinkage1).isNotEqualTo(validateResourceTagLinkage2);
    }
}
