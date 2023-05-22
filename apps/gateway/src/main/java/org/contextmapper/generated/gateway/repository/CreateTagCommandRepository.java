package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.CreateTagCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CreateTagCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreateTagCommandRepository extends ReactiveCrudRepository<CreateTagCommand, Long>, CreateTagCommandRepositoryInternal {
    @Query("SELECT * FROM create_tag_command entity WHERE entity.tag_id = :id")
    Flux<CreateTagCommand> findByTag(Long id);

    @Query("SELECT * FROM create_tag_command entity WHERE entity.tag_id IS NULL")
    Flux<CreateTagCommand> findAllWhereTagIsNull();

    @Override
    <S extends CreateTagCommand> Mono<S> save(S entity);

    @Override
    Flux<CreateTagCommand> findAll();

    @Override
    Mono<CreateTagCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CreateTagCommandRepositoryInternal {
    <S extends CreateTagCommand> Mono<S> save(S entity);

    Flux<CreateTagCommand> findAllBy(Pageable pageable);

    Flux<CreateTagCommand> findAll();

    Mono<CreateTagCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CreateTagCommand> findAllBy(Pageable pageable, Criteria criteria);

}
