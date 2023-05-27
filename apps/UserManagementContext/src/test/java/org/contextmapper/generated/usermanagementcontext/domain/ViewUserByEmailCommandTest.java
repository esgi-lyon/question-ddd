package org.contextmapper.generated.usermanagementcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.usermanagementcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewUserByEmailCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewUserByEmailCommand.class);
        ViewUserByEmailCommand viewUserByEmailCommand1 = new ViewUserByEmailCommand();
        viewUserByEmailCommand1.setId(1L);
        ViewUserByEmailCommand viewUserByEmailCommand2 = new ViewUserByEmailCommand();
        viewUserByEmailCommand2.setId(viewUserByEmailCommand1.getId());
        assertThat(viewUserByEmailCommand1).isEqualTo(viewUserByEmailCommand2);
        viewUserByEmailCommand2.setId(2L);
        assertThat(viewUserByEmailCommand1).isNotEqualTo(viewUserByEmailCommand2);
        viewUserByEmailCommand1.setId(null);
        assertThat(viewUserByEmailCommand1).isNotEqualTo(viewUserByEmailCommand2);
    }
}
