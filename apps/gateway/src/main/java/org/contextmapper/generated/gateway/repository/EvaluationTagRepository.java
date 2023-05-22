package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.EvaluationTag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the EvaluationTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluationTagRepository extends ReactiveCrudRepository<EvaluationTag, Long>, EvaluationTagRepositoryInternal {
    @Override
    <S extends EvaluationTag> Mono<S> save(S entity);

    @Override
    Flux<EvaluationTag> findAll();

    @Override
    Mono<EvaluationTag> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface EvaluationTagRepositoryInternal {
    <S extends EvaluationTag> Mono<S> save(S entity);

    Flux<EvaluationTag> findAllBy(Pageable pageable);

    Flux<EvaluationTag> findAll();

    Mono<EvaluationTag> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<EvaluationTag> findAllBy(Pageable pageable, Criteria criteria);

}
