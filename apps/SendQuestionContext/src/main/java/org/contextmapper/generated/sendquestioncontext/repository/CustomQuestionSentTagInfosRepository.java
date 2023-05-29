package org.contextmapper.generated.sendquestioncontext.repository;

import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the QuestionSentTagInfos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomQuestionSentTagInfosRepository extends JpaRepository<QuestionSentTagInfos, Long> {
    List<QuestionSentTagInfos> findAllByQuestionSentId(Long questionSent);
}
