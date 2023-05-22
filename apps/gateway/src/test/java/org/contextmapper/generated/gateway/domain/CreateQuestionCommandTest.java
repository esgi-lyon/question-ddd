package org.contextmapper.generated.gateway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateQuestionCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateQuestionCommand.class);
        CreateQuestionCommand createQuestionCommand1 = new CreateQuestionCommand();
        createQuestionCommand1.setId(1L);
        CreateQuestionCommand createQuestionCommand2 = new CreateQuestionCommand();
        createQuestionCommand2.setId(createQuestionCommand1.getId());
        assertThat(createQuestionCommand1).isEqualTo(createQuestionCommand2);
        createQuestionCommand2.setId(2L);
        assertThat(createQuestionCommand1).isNotEqualTo(createQuestionCommand2);
        createQuestionCommand1.setId(null);
        assertThat(createQuestionCommand1).isNotEqualTo(createQuestionCommand2);
    }
}
