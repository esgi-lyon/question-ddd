package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ValidateResourceTagLinkageCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValidateResourceTagLinkageCommand.class);
        ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand1 = new ValidateResourceTagLinkageCommand();
        validateResourceTagLinkageCommand1.setId(1L);
        ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand2 = new ValidateResourceTagLinkageCommand();
        validateResourceTagLinkageCommand2.setId(validateResourceTagLinkageCommand1.getId());
        assertThat(validateResourceTagLinkageCommand1).isEqualTo(validateResourceTagLinkageCommand2);
        validateResourceTagLinkageCommand2.setId(2L);
        assertThat(validateResourceTagLinkageCommand1).isNotEqualTo(validateResourceTagLinkageCommand2);
        validateResourceTagLinkageCommand1.setId(null);
        assertThat(validateResourceTagLinkageCommand1).isNotEqualTo(validateResourceTagLinkageCommand2);
    }
}
