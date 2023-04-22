package org.contextmapper.generated.evaluationcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.evaluationcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateEvaluationCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateEvaluationCommand.class);
        CreateEvaluationCommand createEvaluationCommand1 = new CreateEvaluationCommand();
        createEvaluationCommand1.setId(1L);
        CreateEvaluationCommand createEvaluationCommand2 = new CreateEvaluationCommand();
        createEvaluationCommand2.setId(createEvaluationCommand1.getId());
        assertThat(createEvaluationCommand1).isEqualTo(createEvaluationCommand2);
        createEvaluationCommand2.setId(2L);
        assertThat(createEvaluationCommand1).isNotEqualTo(createEvaluationCommand2);
        createEvaluationCommand1.setId(null);
        assertThat(createEvaluationCommand1).isNotEqualTo(createEvaluationCommand2);
    }
}
