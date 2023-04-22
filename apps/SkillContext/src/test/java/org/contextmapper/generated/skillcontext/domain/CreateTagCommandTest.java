package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateTagCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateTagCommand.class);
        CreateTagCommand createTagCommand1 = new CreateTagCommand();
        createTagCommand1.setId(1L);
        CreateTagCommand createTagCommand2 = new CreateTagCommand();
        createTagCommand2.setId(createTagCommand1.getId());
        assertThat(createTagCommand1).isEqualTo(createTagCommand2);
        createTagCommand2.setId(2L);
        assertThat(createTagCommand1).isNotEqualTo(createTagCommand2);
        createTagCommand1.setId(null);
        assertThat(createTagCommand1).isNotEqualTo(createTagCommand2);
    }
}
