package org.contextmapper.generated.sendquestioncontext.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.contextmapper.generated.sendquestioncontext.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuestionSentTagInfosViewedEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuestionSentTagInfosViewedEventDTO.class);
        QuestionSentTagInfosViewedEventDTO questionSentTagInfosViewedEventDTO1 = new QuestionSentTagInfosViewedEventDTO();
        questionSentTagInfosViewedEventDTO1.setId(1L);
        QuestionSentTagInfosViewedEventDTO questionSentTagInfosViewedEventDTO2 = new QuestionSentTagInfosViewedEventDTO();
        assertThat(questionSentTagInfosViewedEventDTO1).isNotEqualTo(questionSentTagInfosViewedEventDTO2);
        questionSentTagInfosViewedEventDTO2.setId(questionSentTagInfosViewedEventDTO1.getId());
        assertThat(questionSentTagInfosViewedEventDTO1).isEqualTo(questionSentTagInfosViewedEventDTO2);
        questionSentTagInfosViewedEventDTO2.setId(2L);
        assertThat(questionSentTagInfosViewedEventDTO1).isNotEqualTo(questionSentTagInfosViewedEventDTO2);
        questionSentTagInfosViewedEventDTO1.setId(null);
        assertThat(questionSentTagInfosViewedEventDTO1).isNotEqualTo(questionSentTagInfosViewedEventDTO2);
    }
}
