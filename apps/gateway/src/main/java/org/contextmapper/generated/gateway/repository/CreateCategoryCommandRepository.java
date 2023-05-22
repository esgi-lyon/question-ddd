package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.CreateCategoryCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CreateCategoryCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreateCategoryCommandRepository
    extends ReactiveCrudRepository<CreateCategoryCommand, Long>, CreateCategoryCommandRepositoryInternal {
    @Query("SELECT * FROM create_category_command entity WHERE entity.category_id = :id")
    Flux<CreateCategoryCommand> findByCategory(Long id);

    @Query("SELECT * FROM create_category_command entity WHERE entity.category_id IS NULL")
    Flux<CreateCategoryCommand> findAllWhereCategoryIsNull();

    @Override
    <S extends CreateCategoryCommand> Mono<S> save(S entity);

    @Override
    Flux<CreateCategoryCommand> findAll();

    @Override
    Mono<CreateCategoryCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CreateCategoryCommandRepositoryInternal {
    <S extends CreateCategoryCommand> Mono<S> save(S entity);

    Flux<CreateCategoryCommand> findAllBy(Pageable pageable);

    Flux<CreateCategoryCommand> findAll();

    Mono<CreateCategoryCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CreateCategoryCommand> findAllBy(Pageable pageable, Criteria criteria);

}
