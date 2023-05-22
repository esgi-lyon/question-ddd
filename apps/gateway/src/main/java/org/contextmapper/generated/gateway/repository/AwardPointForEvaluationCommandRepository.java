package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.AwardPointForEvaluationCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the AwardPointForEvaluationCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AwardPointForEvaluationCommandRepository
    extends ReactiveCrudRepository<AwardPointForEvaluationCommand, Long>, AwardPointForEvaluationCommandRepositoryInternal {
    @Query("SELECT * FROM award_point_for_evaluation_command entity WHERE entity.evaluation_id = :id")
    Flux<AwardPointForEvaluationCommand> findByEvaluation(Long id);

    @Query("SELECT * FROM award_point_for_evaluation_command entity WHERE entity.evaluation_id IS NULL")
    Flux<AwardPointForEvaluationCommand> findAllWhereEvaluationIsNull();

    @Override
    <S extends AwardPointForEvaluationCommand> Mono<S> save(S entity);

    @Override
    Flux<AwardPointForEvaluationCommand> findAll();

    @Override
    Mono<AwardPointForEvaluationCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AwardPointForEvaluationCommandRepositoryInternal {
    <S extends AwardPointForEvaluationCommand> Mono<S> save(S entity);

    Flux<AwardPointForEvaluationCommand> findAllBy(Pageable pageable);

    Flux<AwardPointForEvaluationCommand> findAll();

    Mono<AwardPointForEvaluationCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AwardPointForEvaluationCommand> findAllBy(Pageable pageable, Criteria criteria);

}
