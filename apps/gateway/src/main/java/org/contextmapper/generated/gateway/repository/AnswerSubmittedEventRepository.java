package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.AnswerSubmittedEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the AnswerSubmittedEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswerSubmittedEventRepository
    extends ReactiveCrudRepository<AnswerSubmittedEvent, Long>, AnswerSubmittedEventRepositoryInternal {
    @Query("SELECT * FROM answer_submitted_event entity WHERE entity.answer_id = :id")
    Flux<AnswerSubmittedEvent> findByAnswer(Long id);

    @Query("SELECT * FROM answer_submitted_event entity WHERE entity.answer_id IS NULL")
    Flux<AnswerSubmittedEvent> findAllWhereAnswerIsNull();

    @Override
    <S extends AnswerSubmittedEvent> Mono<S> save(S entity);

    @Override
    Flux<AnswerSubmittedEvent> findAll();

    @Override
    Mono<AnswerSubmittedEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AnswerSubmittedEventRepositoryInternal {
    <S extends AnswerSubmittedEvent> Mono<S> save(S entity);

    Flux<AnswerSubmittedEvent> findAllBy(Pageable pageable);

    Flux<AnswerSubmittedEvent> findAll();

    Mono<AnswerSubmittedEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AnswerSubmittedEvent> findAllBy(Pageable pageable, Criteria criteria);

}
