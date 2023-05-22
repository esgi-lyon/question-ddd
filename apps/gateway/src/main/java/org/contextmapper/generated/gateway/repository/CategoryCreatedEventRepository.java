package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.CategoryCreatedEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CategoryCreatedEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryCreatedEventRepository
    extends ReactiveCrudRepository<CategoryCreatedEvent, Long>, CategoryCreatedEventRepositoryInternal {
    @Query("SELECT * FROM category_created_event entity WHERE entity.category_id_id = :id")
    Flux<CategoryCreatedEvent> findByCategoryId(Long id);

    @Query("SELECT * FROM category_created_event entity WHERE entity.category_id_id IS NULL")
    Flux<CategoryCreatedEvent> findAllWhereCategoryIdIsNull();

    @Override
    <S extends CategoryCreatedEvent> Mono<S> save(S entity);

    @Override
    Flux<CategoryCreatedEvent> findAll();

    @Override
    Mono<CategoryCreatedEvent> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CategoryCreatedEventRepositoryInternal {
    <S extends CategoryCreatedEvent> Mono<S> save(S entity);

    Flux<CategoryCreatedEvent> findAllBy(Pageable pageable);

    Flux<CategoryCreatedEvent> findAll();

    Mono<CategoryCreatedEvent> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CategoryCreatedEvent> findAllBy(Pageable pageable, Criteria criteria);

}
