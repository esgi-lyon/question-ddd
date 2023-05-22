package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.ResourceRefusedAssociationEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the ResourceRefusedAssociationEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceRefusedAssociationEventRepository
    extends ReactiveCrudRepository<ResourceRefusedAssociationEvent, Long>, ResourceRefusedAssociationEventRepositoryInternal {
    @Query("SELECT * FROM resource_refused_association_event entity WHERE entity.question_id_id = :id")
    Flux<ResourceRefusedAssociationEvent> findByQuestionId(Long id);

    @Query("SELECT * FROM resource_refused_association_event entity WHERE entity.question_id_id IS NULL")
    Flux<ResourceRefusedAssociationEvent> findAllWhereQuestionIdIsNull();

    @Query("SELECT * FROM resource_refused_association_event entity WHERE entity.tag_id_id = :id")
    Flux<ResourceRefusedAssociationEvent> findByTagId(Long id);

    @Query("SELECT * FROM resource_refused_association_event entity WHERE entity.tag_id_id IS NULL")
    Flux<ResourceRefusedAssociationEvent> findAllWhereTagIdIsNull();

    @Override
    <S extends ResourceRefusedAssociationEvent> Mono<S> save(S entity);

    @Override
    Flux<ResourceRefusedAssociationEvent> findAll();

    @Override
    Mono<ResourceRefusedAssociationEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ResourceRefusedAssociationEventRepositoryInternal {
    <S extends ResourceRefusedAssociationEvent> Mono<S> save(S entity);

    Flux<ResourceRefusedAssociationEvent> findAllBy(Pageable pageable);

    Flux<ResourceRefusedAssociationEvent> findAll();

    Mono<ResourceRefusedAssociationEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ResourceRefusedAssociationEvent> findAllBy(Pageable pageable, Criteria criteria);

}
