package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceWaitingForAssociationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceWaitingForAssociationDTO.class);
        ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO1 = new ResourceWaitingForAssociationDTO();
        resourceWaitingForAssociationDTO1.setId(1L);
        ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO2 = new ResourceWaitingForAssociationDTO();
        assertThat(resourceWaitingForAssociationDTO1).isNotEqualTo(resourceWaitingForAssociationDTO2);
        resourceWaitingForAssociationDTO2.setId(resourceWaitingForAssociationDTO1.getId());
        assertThat(resourceWaitingForAssociationDTO1).isEqualTo(resourceWaitingForAssociationDTO2);
        resourceWaitingForAssociationDTO2.setId(2L);
        assertThat(resourceWaitingForAssociationDTO1).isNotEqualTo(resourceWaitingForAssociationDTO2);
        resourceWaitingForAssociationDTO1.setId(null);
        assertThat(resourceWaitingForAssociationDTO1).isNotEqualTo(resourceWaitingForAssociationDTO2);
    }
}
