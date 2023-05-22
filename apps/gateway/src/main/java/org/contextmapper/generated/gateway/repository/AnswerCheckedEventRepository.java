package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.AnswerCheckedEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the AnswerCheckedEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswerCheckedEventRepository
    extends ReactiveCrudRepository<AnswerCheckedEvent, Long>, AnswerCheckedEventRepositoryInternal {
    @Query("SELECT * FROM answer_checked_event entity WHERE entity.answer_id = :id")
    Flux<AnswerCheckedEvent> findByAnswer(Long id);

    @Query("SELECT * FROM answer_checked_event entity WHERE entity.answer_id IS NULL")
    Flux<AnswerCheckedEvent> findAllWhereAnswerIsNull();

    @Override
    <S extends AnswerCheckedEvent> Mono<S> save(S entity);

    @Override
    Flux<AnswerCheckedEvent> findAll();

    @Override
    Mono<AnswerCheckedEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AnswerCheckedEventRepositoryInternal {
    <S extends AnswerCheckedEvent> Mono<S> save(S entity);

    Flux<AnswerCheckedEvent> findAllBy(Pageable pageable);

    Flux<AnswerCheckedEvent> findAll();

    Mono<AnswerCheckedEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AnswerCheckedEvent> findAllBy(Pageable pageable, Criteria criteria);

}
