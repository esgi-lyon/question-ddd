package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.NotifiedQuestionEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the NotifiedQuestionEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotifiedQuestionEventRepository
    extends ReactiveCrudRepository<NotifiedQuestionEvent, Long>, NotifiedQuestionEventRepositoryInternal {
    @Query("SELECT * FROM notified_question_event entity WHERE entity.question_resource_id = :id")
    Flux<NotifiedQuestionEvent> findByQuestionResource(Long id);

    @Query("SELECT * FROM notified_question_event entity WHERE entity.question_resource_id IS NULL")
    Flux<NotifiedQuestionEvent> findAllWhereQuestionResourceIsNull();

    @Override
    <S extends NotifiedQuestionEvent> Mono<S> save(S entity);

    @Override
    Flux<NotifiedQuestionEvent> findAll();

    @Override
    Mono<NotifiedQuestionEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface NotifiedQuestionEventRepositoryInternal {
    <S extends NotifiedQuestionEvent> Mono<S> save(S entity);

    Flux<NotifiedQuestionEvent> findAllBy(Pageable pageable);

    Flux<NotifiedQuestionEvent> findAll();

    Mono<NotifiedQuestionEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<NotifiedQuestionEvent> findAllBy(Pageable pageable, Criteria criteria);

}
