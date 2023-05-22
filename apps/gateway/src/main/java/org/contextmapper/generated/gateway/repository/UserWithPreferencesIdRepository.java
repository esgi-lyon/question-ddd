package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.UserWithPreferencesId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the UserWithPreferencesId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserWithPreferencesIdRepository
    extends ReactiveCrudRepository<UserWithPreferencesId, Long>, UserWithPreferencesIdRepositoryInternal {
    @Override
    <S extends UserWithPreferencesId> Mono<S> save(S entity);

    @Override
    Flux<UserWithPreferencesId> findAll();

    @Override
    Mono<UserWithPreferencesId> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface UserWithPreferencesIdRepositoryInternal {
    <S extends UserWithPreferencesId> Mono<S> save(S entity);

    Flux<UserWithPreferencesId> findAllBy(Pageable pageable);

    Flux<UserWithPreferencesId> findAll();

    Mono<UserWithPreferencesId> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<UserWithPreferencesId> findAllBy(Pageable pageable, Criteria criteria);

}
