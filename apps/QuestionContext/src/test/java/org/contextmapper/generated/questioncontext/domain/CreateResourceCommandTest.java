package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateResourceCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateResourceCommand.class);
        CreateResourceCommand createResourceCommand1 = new CreateResourceCommand();
        createResourceCommand1.setId(1L);
        CreateResourceCommand createResourceCommand2 = new CreateResourceCommand();
        createResourceCommand2.setId(createResourceCommand1.getId());
        assertThat(createResourceCommand1).isEqualTo(createResourceCommand2);
        createResourceCommand2.setId(2L);
        assertThat(createResourceCommand1).isNotEqualTo(createResourceCommand2);
        createResourceCommand1.setId(null);
        assertThat(createResourceCommand1).isNotEqualTo(createResourceCommand2);
    }
}
