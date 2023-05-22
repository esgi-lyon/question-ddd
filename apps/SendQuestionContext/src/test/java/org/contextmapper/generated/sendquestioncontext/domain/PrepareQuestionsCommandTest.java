package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PrepareQuestionsCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrepareQuestionsCommand.class);
        PrepareQuestionsCommand prepareQuestionsCommand1 = new PrepareQuestionsCommand();
        prepareQuestionsCommand1.setId(1L);
        PrepareQuestionsCommand prepareQuestionsCommand2 = new PrepareQuestionsCommand();
        prepareQuestionsCommand2.setId(prepareQuestionsCommand1.getId());
        assertThat(prepareQuestionsCommand1).isEqualTo(prepareQuestionsCommand2);
        prepareQuestionsCommand2.setId(2L);
        assertThat(prepareQuestionsCommand1).isNotEqualTo(prepareQuestionsCommand2);
        prepareQuestionsCommand1.setId(null);
        assertThat(prepareQuestionsCommand1).isNotEqualTo(prepareQuestionsCommand2);
    }
}
