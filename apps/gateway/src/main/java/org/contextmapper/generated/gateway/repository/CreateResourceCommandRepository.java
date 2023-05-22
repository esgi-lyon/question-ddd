package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.CreateResourceCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CreateResourceCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreateResourceCommandRepository
    extends ReactiveCrudRepository<CreateResourceCommand, Long>, CreateResourceCommandRepositoryInternal {
    @Query("SELECT * FROM create_resource_command entity WHERE entity.question_id_id = :id")
    Flux<CreateResourceCommand> findByQuestionId(Long id);

    @Query("SELECT * FROM create_resource_command entity WHERE entity.question_id_id IS NULL")
    Flux<CreateResourceCommand> findAllWhereQuestionIdIsNull();

    @Override
    <S extends CreateResourceCommand> Mono<S> save(S entity);

    @Override
    Flux<CreateResourceCommand> findAll();

    @Override
    Mono<CreateResourceCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CreateResourceCommandRepositoryInternal {
    <S extends CreateResourceCommand> Mono<S> save(S entity);

    Flux<CreateResourceCommand> findAllBy(Pageable pageable);

    Flux<CreateResourceCommand> findAll();

    Mono<CreateResourceCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CreateResourceCommand> findAllBy(Pageable pageable, Criteria criteria);

}
