package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.SendQuestionByTagsPreferencesCommand;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SendQuestionByTagsPreferencesCommand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SendQuestionByTagsPreferencesCommandRepository
    extends ReactiveCrudRepository<SendQuestionByTagsPreferencesCommand, Long>, SendQuestionByTagsPreferencesCommandRepositoryInternal {
    @Query("SELECT * FROM send_question_by_tags_preferences_command entity WHERE entity.question_sent_id = :id")
    Flux<SendQuestionByTagsPreferencesCommand> findByQuestionSent(Long id);

    @Query("SELECT * FROM send_question_by_tags_preferences_command entity WHERE entity.question_sent_id IS NULL")
    Flux<SendQuestionByTagsPreferencesCommand> findAllWhereQuestionSentIsNull();

    @Override
    <S extends SendQuestionByTagsPreferencesCommand> Mono<S> save(S entity);

    @Override
    Flux<SendQuestionByTagsPreferencesCommand> findAll();

    @Override
    Mono<SendQuestionByTagsPreferencesCommand> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SendQuestionByTagsPreferencesCommandRepositoryInternal {
    <S extends SendQuestionByTagsPreferencesCommand> Mono<S> save(S entity);

    Flux<SendQuestionByTagsPreferencesCommand> findAllBy(Pageable pageable);

    Flux<SendQuestionByTagsPreferencesCommand> findAll();

    Mono<SendQuestionByTagsPreferencesCommand> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SendQuestionByTagsPreferencesCommand> findAllBy(Pageable pageable, Criteria criteria);

}
