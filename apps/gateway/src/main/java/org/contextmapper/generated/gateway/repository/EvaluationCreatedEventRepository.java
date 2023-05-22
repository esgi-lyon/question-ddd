package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.EvaluationCreatedEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the EvaluationCreatedEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluationCreatedEventRepository
    extends ReactiveCrudRepository<EvaluationCreatedEvent, Long>, EvaluationCreatedEventRepositoryInternal {
    @Query("SELECT * FROM evaluation_created_event entity WHERE entity.evaluation_id = :id")
    Flux<EvaluationCreatedEvent> findByEvaluation(Long id);

    @Query("SELECT * FROM evaluation_created_event entity WHERE entity.evaluation_id IS NULL")
    Flux<EvaluationCreatedEvent> findAllWhereEvaluationIsNull();

    @Override
    <S extends EvaluationCreatedEvent> Mono<S> save(S entity);

    @Override
    Flux<EvaluationCreatedEvent> findAll();

    @Override
    Mono<EvaluationCreatedEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface EvaluationCreatedEventRepositoryInternal {
    <S extends EvaluationCreatedEvent> Mono<S> save(S entity);

    Flux<EvaluationCreatedEvent> findAllBy(Pageable pageable);

    Flux<EvaluationCreatedEvent> findAll();

    Mono<EvaluationCreatedEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<EvaluationCreatedEvent> findAllBy(Pageable pageable, Criteria criteria);

}
