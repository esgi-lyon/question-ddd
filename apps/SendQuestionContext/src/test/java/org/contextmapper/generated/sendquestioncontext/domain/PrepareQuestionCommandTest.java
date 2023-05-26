package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrepareQuestionCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrepareQuestionCommand.class);
        PrepareQuestionCommand prepareQuestionCommand1 = new PrepareQuestionCommand();
        prepareQuestionCommand1.setId(1L);
        PrepareQuestionCommand prepareQuestionCommand2 = new PrepareQuestionCommand();
        prepareQuestionCommand2.setId(prepareQuestionCommand1.getId());
        assertThat(prepareQuestionCommand1).isEqualTo(prepareQuestionCommand2);
        prepareQuestionCommand2.setId(2L);
        assertThat(prepareQuestionCommand1).isNotEqualTo(prepareQuestionCommand2);
        prepareQuestionCommand1.setId(null);
        assertThat(prepareQuestionCommand1).isNotEqualTo(prepareQuestionCommand2);
    }
}
