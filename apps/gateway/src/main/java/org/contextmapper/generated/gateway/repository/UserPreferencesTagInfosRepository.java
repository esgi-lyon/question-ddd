package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.UserPreferencesTagInfos;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the UserPreferencesTagInfos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserPreferencesTagInfosRepository
    extends ReactiveCrudRepository<UserPreferencesTagInfos, Long>, UserPreferencesTagInfosRepositoryInternal {
    @Query("SELECT * FROM user_preferences_tag_infos entity WHERE entity.user_preferences_id = :id")
    Flux<UserPreferencesTagInfos> findByUserPreferences(Long id);

    @Query("SELECT * FROM user_preferences_tag_infos entity WHERE entity.user_preferences_id IS NULL")
    Flux<UserPreferencesTagInfos> findAllWhereUserPreferencesIsNull();

    @Override
    <S extends UserPreferencesTagInfos> Mono<S> save(S entity);

    @Override
    Flux<UserPreferencesTagInfos> findAll();

    @Override
    Mono<UserPreferencesTagInfos> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface UserPreferencesTagInfosRepositoryInternal {
    <S extends UserPreferencesTagInfos> Mono<S> save(S entity);

    Flux<UserPreferencesTagInfos> findAllBy(Pageable pageable);

    Flux<UserPreferencesTagInfos> findAll();

    Mono<UserPreferencesTagInfos> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<UserPreferencesTagInfos> findAllBy(Pageable pageable, Criteria criteria);

}
