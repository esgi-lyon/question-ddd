package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreateResourceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreateResource.class);
        CreateResource createResource1 = new CreateResource();
        createResource1.setId(1L);
        CreateResource createResource2 = new CreateResource();
        createResource2.setId(createResource1.getId());
        assertThat(createResource1).isEqualTo(createResource2);
        createResource2.setId(2L);
        assertThat(createResource1).isNotEqualTo(createResource2);
        createResource1.setId(null);
        assertThat(createResource1).isNotEqualTo(createResource2);
    }
}
