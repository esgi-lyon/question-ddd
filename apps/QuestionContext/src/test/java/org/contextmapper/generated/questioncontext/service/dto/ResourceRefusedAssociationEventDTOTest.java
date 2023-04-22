package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceRefusedAssociationEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceRefusedAssociationEventDTO.class);
        ResourceRefusedAssociationEventDTO resourceRefusedAssociationEventDTO1 = new ResourceRefusedAssociationEventDTO();
        resourceRefusedAssociationEventDTO1.setId(1L);
        ResourceRefusedAssociationEventDTO resourceRefusedAssociationEventDTO2 = new ResourceRefusedAssociationEventDTO();
        assertThat(resourceRefusedAssociationEventDTO1).isNotEqualTo(resourceRefusedAssociationEventDTO2);
        resourceRefusedAssociationEventDTO2.setId(resourceRefusedAssociationEventDTO1.getId());
        assertThat(resourceRefusedAssociationEventDTO1).isEqualTo(resourceRefusedAssociationEventDTO2);
        resourceRefusedAssociationEventDTO2.setId(2L);
        assertThat(resourceRefusedAssociationEventDTO1).isNotEqualTo(resourceRefusedAssociationEventDTO2);
        resourceRefusedAssociationEventDTO1.setId(null);
        assertThat(resourceRefusedAssociationEventDTO1).isNotEqualTo(resourceRefusedAssociationEventDTO2);
    }
}
