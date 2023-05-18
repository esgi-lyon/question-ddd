package org.contextmapper.generated.questioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.questioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceRefusedAssociationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResourceRefusedAssociationDTO.class);
        ResourceRefusedAssociationDTO resourceRefusedAssociationDTO1 = new ResourceRefusedAssociationDTO();
        resourceRefusedAssociationDTO1.setId(1L);
        ResourceRefusedAssociationDTO resourceRefusedAssociationDTO2 = new ResourceRefusedAssociationDTO();
        assertThat(resourceRefusedAssociationDTO1).isNotEqualTo(resourceRefusedAssociationDTO2);
        resourceRefusedAssociationDTO2.setId(resourceRefusedAssociationDTO1.getId());
        assertThat(resourceRefusedAssociationDTO1).isEqualTo(resourceRefusedAssociationDTO2);
        resourceRefusedAssociationDTO2.setId(2L);
        assertThat(resourceRefusedAssociationDTO1).isNotEqualTo(resourceRefusedAssociationDTO2);
        resourceRefusedAssociationDTO1.setId(null);
        assertThat(resourceRefusedAssociationDTO1).isNotEqualTo(resourceRefusedAssociationDTO2);
    }
}
