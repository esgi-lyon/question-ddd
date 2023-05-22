package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.TagInfos;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the TagInfos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TagInfosRepository extends ReactiveCrudRepository<TagInfos, Long>, TagInfosRepositoryInternal {
    @Override
    <S extends TagInfos> Mono<S> save(S entity);

    @Override
    Flux<TagInfos> findAll();

    @Override
    Mono<TagInfos> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface TagInfosRepositoryInternal {
    <S extends TagInfos> Mono<S> save(S entity);

    Flux<TagInfos> findAllBy(Pageable pageable);

    Flux<TagInfos> findAll();

    Mono<TagInfos> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<TagInfos> findAllBy(Pageable pageable, Criteria criteria);

}
