package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.RejectResourceTagCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the RejectResourceTagCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RejectResourceTagCommandRepository
    extends ReactiveCrudRepository<RejectResourceTagCommand, Long>, RejectResourceTagCommandRepositoryInternal {
    @Query("SELECT * FROM reject_resource_tag_command entity WHERE entity.question_id_id = :id")
    Flux<RejectResourceTagCommand> findByQuestionId(Long id);

    @Query("SELECT * FROM reject_resource_tag_command entity WHERE entity.question_id_id IS NULL")
    Flux<RejectResourceTagCommand> findAllWhereQuestionIdIsNull();

    @Override
    <S extends RejectResourceTagCommand> Mono<S> save(S entity);

    @Override
    Flux<RejectResourceTagCommand> findAll();

    @Override
    Mono<RejectResourceTagCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface RejectResourceTagCommandRepositoryInternal {
    <S extends RejectResourceTagCommand> Mono<S> save(S entity);

    Flux<RejectResourceTagCommand> findAllBy(Pageable pageable);

    Flux<RejectResourceTagCommand> findAll();

    Mono<RejectResourceTagCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<RejectResourceTagCommand> findAllBy(Pageable pageable, Criteria criteria);

}
