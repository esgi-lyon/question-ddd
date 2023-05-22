package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.EvaluationId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the EvaluationId entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluationIdRepository extends ReactiveCrudRepository<EvaluationId, Long>, EvaluationIdRepositoryInternal {
    @Query("SELECT * FROM evaluation_id entity WHERE entity.leader_board_id = :id")
    Flux<EvaluationId> findByLeaderBoard(Long id);

    @Query("SELECT * FROM evaluation_id entity WHERE entity.leader_board_id IS NULL")
    Flux<EvaluationId> findAllWhereLeaderBoardIsNull();

    @Override
    <S extends EvaluationId> Mono<S> save(S entity);

    @Override
    Flux<EvaluationId> findAll();

    @Override
    Mono<EvaluationId> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface EvaluationIdRepositoryInternal {
    <S extends EvaluationId> Mono<S> save(S entity);

    Flux<EvaluationId> findAllBy(Pageable pageable);

    Flux<EvaluationId> findAll();

    Mono<EvaluationId> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<EvaluationId> findAllBy(Pageable pageable, Criteria criteria);

}
