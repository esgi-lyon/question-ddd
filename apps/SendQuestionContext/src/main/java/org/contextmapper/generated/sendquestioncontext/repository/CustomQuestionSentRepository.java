package org.contextmapper.generated.sendquestioncontext.repository;

import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.domain.enumeration.QuestionNotificationStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the QuestionSent entity.
 */
@SuppressWarnings("unused")
@Repository
@Primary
public interface CustomQuestionSentRepository extends JpaRepository<QuestionSent, Long> {
    List<QuestionSent> findAllByStatus(QuestionNotificationStatus status);
}
