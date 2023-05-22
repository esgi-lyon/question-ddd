package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.ResourceAcceptedAssociationEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ResourceAcceptedAssociationEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceAcceptedAssociationEventRepository
    extends ReactiveCrudRepository<ResourceAcceptedAssociationEvent, Long>, ResourceAcceptedAssociationEventRepositoryInternal {
    @Query("SELECT * FROM resource_accepted_association_event entity WHERE entity.question_id_id = :id")
    Flux<ResourceAcceptedAssociationEvent> findByQuestionId(Long id);

    @Query("SELECT * FROM resource_accepted_association_event entity WHERE entity.question_id_id IS NULL")
    Flux<ResourceAcceptedAssociationEvent> findAllWhereQuestionIdIsNull();

    @Query("SELECT * FROM resource_accepted_association_event entity WHERE entity.tag_id_id = :id")
    Flux<ResourceAcceptedAssociationEvent> findByTagId(Long id);

    @Query("SELECT * FROM resource_accepted_association_event entity WHERE entity.tag_id_id IS NULL")
    Flux<ResourceAcceptedAssociationEvent> findAllWhereTagIdIsNull();

    @Override
    <S extends ResourceAcceptedAssociationEvent> Mono<S> save(S entity);

    @Override
    Flux<ResourceAcceptedAssociationEvent> findAll();

    @Override
    Mono<ResourceAcceptedAssociationEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ResourceAcceptedAssociationEventRepositoryInternal {
    <S extends ResourceAcceptedAssociationEvent> Mono<S> save(S entity);

    Flux<ResourceAcceptedAssociationEvent> findAllBy(Pageable pageable);

    Flux<ResourceAcceptedAssociationEvent> findAll();

    Mono<ResourceAcceptedAssociationEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ResourceAcceptedAssociationEvent> findAllBy(Pageable pageable, Criteria criteria);

}
