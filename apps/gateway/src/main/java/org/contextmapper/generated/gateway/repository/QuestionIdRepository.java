package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.QuestionId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the QuestionId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionIdRepository extends ReactiveCrudRepository<QuestionId, Long>, QuestionIdRepositoryInternal {
    @Override
    <S extends QuestionId> Mono<S> save(S entity);

    @Override
    Flux<QuestionId> findAll();

    @Override
    Mono<QuestionId> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface QuestionIdRepositoryInternal {
    <S extends QuestionId> Mono<S> save(S entity);

    Flux<QuestionId> findAllBy(Pageable pageable);

    Flux<QuestionId> findAll();

    Mono<QuestionId> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<QuestionId> findAllBy(Pageable pageable, Criteria criteria);

}
