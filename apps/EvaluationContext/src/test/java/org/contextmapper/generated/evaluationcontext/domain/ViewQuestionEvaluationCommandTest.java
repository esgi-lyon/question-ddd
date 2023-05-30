package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ViewQuestionEvaluationCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViewQuestionEvaluationCommand.class);
        ViewQuestionEvaluationCommand viewQuestionEvaluationCommand1 = new ViewQuestionEvaluationCommand();
        viewQuestionEvaluationCommand1.setId(1L);
        ViewQuestionEvaluationCommand viewQuestionEvaluationCommand2 = new ViewQuestionEvaluationCommand();
        viewQuestionEvaluationCommand2.setId(viewQuestionEvaluationCommand1.getId());
        assertThat(viewQuestionEvaluationCommand1).isEqualTo(viewQuestionEvaluationCommand2);
        viewQuestionEvaluationCommand2.setId(2L);
        assertThat(viewQuestionEvaluationCommand1).isNotEqualTo(viewQuestionEvaluationCommand2);
        viewQuestionEvaluationCommand1.setId(null);
        assertThat(viewQuestionEvaluationCommand1).isNotEqualTo(viewQuestionEvaluationCommand2);
    }
}
