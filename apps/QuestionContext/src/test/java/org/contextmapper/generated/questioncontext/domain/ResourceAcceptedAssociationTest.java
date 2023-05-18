package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceAcceptedAssociationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceAcceptedAssociation.class);
        ResourceAcceptedAssociation resourceAcceptedAssociation1 = new ResourceAcceptedAssociation();
        resourceAcceptedAssociation1.setId(1L);
        ResourceAcceptedAssociation resourceAcceptedAssociation2 = new ResourceAcceptedAssociation();
        resourceAcceptedAssociation2.setId(resourceAcceptedAssociation1.getId());
        assertThat(resourceAcceptedAssociation1).isEqualTo(resourceAcceptedAssociation2);
        resourceAcceptedAssociation2.setId(2L);
        assertThat(resourceAcceptedAssociation1).isNotEqualTo(resourceAcceptedAssociation2);
        resourceAcceptedAssociation1.setId(null);
        assertThat(resourceAcceptedAssociation1).isNotEqualTo(resourceAcceptedAssociation2);
    }
}
