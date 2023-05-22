package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.AnsweringUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the AnsweringUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnsweringUserRepository extends ReactiveCrudRepository<AnsweringUser, Long>, AnsweringUserRepositoryInternal {
    @Override
    <S extends AnsweringUser> Mono<S> save(S entity);

    @Override
    Flux<AnsweringUser> findAll();

    @Override
    Mono<AnsweringUser> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AnsweringUserRepositoryInternal {
    <S extends AnsweringUser> Mono<S> save(S entity);

    Flux<AnsweringUser> findAllBy(Pageable pageable);

    Flux<AnsweringUser> findAll();

    Mono<AnsweringUser> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AnsweringUser> findAllBy(Pageable pageable, Criteria criteria);

}
