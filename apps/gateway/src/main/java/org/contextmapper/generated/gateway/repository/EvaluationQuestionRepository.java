package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.EvaluationQuestion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the EvaluationQuestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluationQuestionRepository
    extends ReactiveCrudRepository<EvaluationQuestion, Long>, EvaluationQuestionRepositoryInternal {
    @Override
    <S extends EvaluationQuestion> Mono<S> save(S entity);

    @Override
    Flux<EvaluationQuestion> findAll();

    @Override
    Mono<EvaluationQuestion> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface EvaluationQuestionRepositoryInternal {
    <S extends EvaluationQuestion> Mono<S> save(S entity);

    Flux<EvaluationQuestion> findAllBy(Pageable pageable);

    Flux<EvaluationQuestion> findAll();

    Mono<EvaluationQuestion> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<EvaluationQuestion> findAllBy(Pageable pageable, Criteria criteria);

}
