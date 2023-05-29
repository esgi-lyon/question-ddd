package org.contextmapper.generated.sendquestioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewTagsForQuestionCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewTagsForQuestionCommand.class);
        ViewTagsForQuestionCommand viewTagsForQuestionCommand1 = new ViewTagsForQuestionCommand();
        viewTagsForQuestionCommand1.setId(1L);
        ViewTagsForQuestionCommand viewTagsForQuestionCommand2 = new ViewTagsForQuestionCommand();
        viewTagsForQuestionCommand2.setId(viewTagsForQuestionCommand1.getId());
        assertThat(viewTagsForQuestionCommand1).isEqualTo(viewTagsForQuestionCommand2);
        viewTagsForQuestionCommand2.setId(2L);
        assertThat(viewTagsForQuestionCommand1).isNotEqualTo(viewTagsForQuestionCommand2);
        viewTagsForQuestionCommand1.setId(null);
        assertThat(viewTagsForQuestionCommand1).isNotEqualTo(viewTagsForQuestionCommand2);
    }
}
