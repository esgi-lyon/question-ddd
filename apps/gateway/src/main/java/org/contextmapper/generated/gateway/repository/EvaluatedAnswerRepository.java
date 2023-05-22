package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.EvaluatedAnswer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the EvaluatedAnswer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluatedAnswerRepository extends ReactiveCrudRepository<EvaluatedAnswer, Long>, EvaluatedAnswerRepositoryInternal {
    @Override
    <S extends EvaluatedAnswer> Mono<S> save(S entity);

    @Override
    Flux<EvaluatedAnswer> findAll();

    @Override
    Mono<EvaluatedAnswer> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface EvaluatedAnswerRepositoryInternal {
    <S extends EvaluatedAnswer> Mono<S> save(S entity);

    Flux<EvaluatedAnswer> findAllBy(Pageable pageable);

    Flux<EvaluatedAnswer> findAll();

    Mono<EvaluatedAnswer> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<EvaluatedAnswer> findAllBy(Pageable pageable, Criteria criteria);

}
