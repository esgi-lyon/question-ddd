package org.contextmapper.generated.questioncontext.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceAcceptedAssociationEventTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceAcceptedAssociationEvent.class);
        ResourceAcceptedAssociationEvent resourceAcceptedAssociationEvent1 = new ResourceAcceptedAssociationEvent();
        resourceAcceptedAssociationEvent1.setId(1L);
        ResourceAcceptedAssociationEvent resourceAcceptedAssociationEvent2 = new ResourceAcceptedAssociationEvent();
        resourceAcceptedAssociationEvent2.setId(resourceAcceptedAssociationEvent1.getId());
        assertThat(resourceAcceptedAssociationEvent1).isEqualTo(resourceAcceptedAssociationEvent2);
        resourceAcceptedAssociationEvent2.setId(2L);
        assertThat(resourceAcceptedAssociationEvent1).isNotEqualTo(resourceAcceptedAssociationEvent2);
        resourceAcceptedAssociationEvent1.setId(null);
        assertThat(resourceAcceptedAssociationEvent1).isNotEqualTo(resourceAcceptedAssociationEvent2);
    }
}
