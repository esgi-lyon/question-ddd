package org.contextmapper.generated.evaluationcontext.repository;

import org.contextmapper.generated.evaluationcontext.domain.Evaluation;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Evaluation entity.
 */
@SuppressWarnings("unused")
@Repository
@Primary
public interface CustomEvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findAllByUserMail(String mail);
}
