package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.CreateEvaluationCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the CreateEvaluationCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreateEvaluationCommandRepository
    extends ReactiveCrudRepository<CreateEvaluationCommand, Long>, CreateEvaluationCommandRepositoryInternal {
    @Query("SELECT * FROM create_evaluation_command entity WHERE entity.answer_id = :id")
    Flux<CreateEvaluationCommand> findByAnswer(Long id);

    @Query("SELECT * FROM create_evaluation_command entity WHERE entity.answer_id IS NULL")
    Flux<CreateEvaluationCommand> findAllWhereAnswerIsNull();

    @Override
    <S extends CreateEvaluationCommand> Mono<S> save(S entity);

    @Override
    Flux<CreateEvaluationCommand> findAll();

    @Override
    Mono<CreateEvaluationCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CreateEvaluationCommandRepositoryInternal {
    <S extends CreateEvaluationCommand> Mono<S> save(S entity);

    Flux<CreateEvaluationCommand> findAllBy(Pageable pageable);

    Flux<CreateEvaluationCommand> findAll();

    Mono<CreateEvaluationCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CreateEvaluationCommand> findAllBy(Pageable pageable, Criteria criteria);

}
