package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.AnswerSubmitCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the AnswerSubmitCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswerSubmitCommandRepository
    extends ReactiveCrudRepository<AnswerSubmitCommand, Long>, AnswerSubmitCommandRepositoryInternal {
    @Query("SELECT * FROM answer_submit_command entity WHERE entity.answer_id = :id")
    Flux<AnswerSubmitCommand> findByAnswer(Long id);

    @Query("SELECT * FROM answer_submit_command entity WHERE entity.answer_id IS NULL")
    Flux<AnswerSubmitCommand> findAllWhereAnswerIsNull();

    @Override
    <S extends AnswerSubmitCommand> Mono<S> save(S entity);

    @Override
    Flux<AnswerSubmitCommand> findAll();

    @Override
    Mono<AnswerSubmitCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AnswerSubmitCommandRepositoryInternal {
    <S extends AnswerSubmitCommand> Mono<S> save(S entity);

    Flux<AnswerSubmitCommand> findAllBy(Pageable pageable);

    Flux<AnswerSubmitCommand> findAll();

    Mono<AnswerSubmitCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AnswerSubmitCommand> findAllBy(Pageable pageable, Criteria criteria);

}
