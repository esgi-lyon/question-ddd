package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.AnsweredTag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the AnsweredTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnsweredTagRepository extends ReactiveCrudRepository<AnsweredTag, Long>, AnsweredTagRepositoryInternal {
    @Override
    <S extends AnsweredTag> Mono<S> save(S entity);

    @Override
    Flux<AnsweredTag> findAll();

    @Override
    Mono<AnsweredTag> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AnsweredTagRepositoryInternal {
    <S extends AnsweredTag> Mono<S> save(S entity);

    Flux<AnsweredTag> findAllBy(Pageable pageable);

    Flux<AnsweredTag> findAll();

    Mono<AnsweredTag> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AnsweredTag> findAllBy(Pageable pageable, Criteria criteria);

}
