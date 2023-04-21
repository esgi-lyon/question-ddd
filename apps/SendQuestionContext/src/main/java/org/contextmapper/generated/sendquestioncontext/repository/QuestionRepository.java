package org.contextmapper.generated.sendquestioncontext.repository;

import org.contextmapper.generated.sendquestioncontext.domain.Question;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Question entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {}
