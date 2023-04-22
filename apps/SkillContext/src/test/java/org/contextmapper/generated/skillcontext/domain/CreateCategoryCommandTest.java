package org.contextmapper.generated.skillcontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.skillcontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateCategoryCommandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateCategoryCommand.class);
        CreateCategoryCommand createCategoryCommand1 = new CreateCategoryCommand();
        createCategoryCommand1.setId(1L);
        CreateCategoryCommand createCategoryCommand2 = new CreateCategoryCommand();
        createCategoryCommand2.setId(createCategoryCommand1.getId());
        assertThat(createCategoryCommand1).isEqualTo(createCategoryCommand2);
        createCategoryCommand2.setId(2L);
        assertThat(createCategoryCommand1).isNotEqualTo(createCategoryCommand2);
        createCategoryCommand1.setId(null);
        assertThat(createCategoryCommand1).isNotEqualTo(createCategoryCommand2);
    }
}
