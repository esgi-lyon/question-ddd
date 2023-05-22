package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.ResourceWaitingForAssociationEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ResourceWaitingForAssociationEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceWaitingForAssociationEventRepository
    extends ReactiveCrudRepository<ResourceWaitingForAssociationEvent, Long>, ResourceWaitingForAssociationEventRepositoryInternal {
    @Query("SELECT * FROM resource_waiting_for_association_event entity WHERE entity.question_id_id = :id")
    Flux<ResourceWaitingForAssociationEvent> findByQuestionId(Long id);

    @Query("SELECT * FROM resource_waiting_for_association_event entity WHERE entity.question_id_id IS NULL")
    Flux<ResourceWaitingForAssociationEvent> findAllWhereQuestionIdIsNull();

    @Query("SELECT * FROM resource_waiting_for_association_event entity WHERE entity.tag_id_id = :id")
    Flux<ResourceWaitingForAssociationEvent> findByTagId(Long id);

    @Query("SELECT * FROM resource_waiting_for_association_event entity WHERE entity.tag_id_id IS NULL")
    Flux<ResourceWaitingForAssociationEvent> findAllWhereTagIdIsNull();

    @Override
    <S extends ResourceWaitingForAssociationEvent> Mono<S> save(S entity);

    @Override
    Flux<ResourceWaitingForAssociationEvent> findAll();

    @Override
    Mono<ResourceWaitingForAssociationEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ResourceWaitingForAssociationEventRepositoryInternal {
    <S extends ResourceWaitingForAssociationEvent> Mono<S> save(S entity);

    Flux<ResourceWaitingForAssociationEvent> findAllBy(Pageable pageable);

    Flux<ResourceWaitingForAssociationEvent> findAll();

    Mono<ResourceWaitingForAssociationEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ResourceWaitingForAssociationEvent> findAllBy(Pageable pageable, Criteria criteria);

}
