package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.CreatedQuestionEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CreatedQuestionEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreatedQuestionEventRepository
    extends ReactiveCrudRepository<CreatedQuestionEvent, Long>, CreatedQuestionEventRepositoryInternal {
    @Query("SELECT * FROM created_question_event entity WHERE entity.question_and_tag_id = :id")
    Flux<CreatedQuestionEvent> findByQuestionAndTag(Long id);

    @Query("SELECT * FROM created_question_event entity WHERE entity.question_and_tag_id IS NULL")
    Flux<CreatedQuestionEvent> findAllWhereQuestionAndTagIsNull();

    @Override
    <S extends CreatedQuestionEvent> Mono<S> save(S entity);

    @Override
    Flux<CreatedQuestionEvent> findAll();

    @Override
    Mono<CreatedQuestionEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CreatedQuestionEventRepositoryInternal {
    <S extends CreatedQuestionEvent> Mono<S> save(S entity);

    Flux<CreatedQuestionEvent> findAllBy(Pageable pageable);

    Flux<CreatedQuestionEvent> findAll();

    Mono<CreatedQuestionEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CreatedQuestionEvent> findAllBy(Pageable pageable, Criteria criteria);

}
