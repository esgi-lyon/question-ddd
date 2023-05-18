package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceRefusedAssociationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceRefusedAssociation.class);
        ResourceRefusedAssociation resourceRefusedAssociation1 = new ResourceRefusedAssociation();
        resourceRefusedAssociation1.setId(1L);
        ResourceRefusedAssociation resourceRefusedAssociation2 = new ResourceRefusedAssociation();
        resourceRefusedAssociation2.setId(resourceRefusedAssociation1.getId());
        assertThat(resourceRefusedAssociation1).isEqualTo(resourceRefusedAssociation2);
        resourceRefusedAssociation2.setId(2L);
        assertThat(resourceRefusedAssociation1).isNotEqualTo(resourceRefusedAssociation2);
        resourceRefusedAssociation1.setId(null);
        assertThat(resourceRefusedAssociation1).isNotEqualTo(resourceRefusedAssociation2);
    }
}
