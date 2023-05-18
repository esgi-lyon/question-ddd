package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceWaitingForAssociationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceWaitingForAssociation.class);
        ResourceWaitingForAssociation resourceWaitingForAssociation1 = new ResourceWaitingForAssociation();
        resourceWaitingForAssociation1.setId(1L);
        ResourceWaitingForAssociation resourceWaitingForAssociation2 = new ResourceWaitingForAssociation();
        resourceWaitingForAssociation2.setId(resourceWaitingForAssociation1.getId());
        assertThat(resourceWaitingForAssociation1).isEqualTo(resourceWaitingForAssociation2);
        resourceWaitingForAssociation2.setId(2L);
        assertThat(resourceWaitingForAssociation1).isNotEqualTo(resourceWaitingForAssociation2);
        resourceWaitingForAssociation1.setId(null);
        assertThat(resourceWaitingForAssociation1).isNotEqualTo(resourceWaitingForAssociation2);
    }
}
