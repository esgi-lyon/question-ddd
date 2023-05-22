package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.TagCreatedEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the TagCreatedEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TagCreatedEventRepository extends ReactiveCrudRepository<TagCreatedEvent, Long>, TagCreatedEventRepositoryInternal {
    @Query("SELECT * FROM tag_created_event entity WHERE entity.tag_id_id = :id")
    Flux<TagCreatedEvent> findByTagId(Long id);

    @Query("SELECT * FROM tag_created_event entity WHERE entity.tag_id_id IS NULL")
    Flux<TagCreatedEvent> findAllWhereTagIdIsNull();

    @Override
    <S extends TagCreatedEvent> Mono<S> save(S entity);

    @Override
    Flux<TagCreatedEvent> findAll();

    @Override
    Mono<TagCreatedEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface TagCreatedEventRepositoryInternal {
    <S extends TagCreatedEvent> Mono<S> save(S entity);

    Flux<TagCreatedEvent> findAllBy(Pageable pageable);

    Flux<TagCreatedEvent> findAll();

    Mono<TagCreatedEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<TagCreatedEvent> findAllBy(Pageable pageable, Criteria criteria);

}
