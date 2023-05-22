package org.contextmapper.generated.gateway.repository;

import org.contextmapper.generated.gateway.domain.QuestionSentTagInfos;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the QuestionSentTagInfos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionSentTagInfosRepository
    extends ReactiveCrudRepository<QuestionSentTagInfos, Long>, QuestionSentTagInfosRepositoryInternal {
    @Query("SELECT * FROM question_sent_tag_infos entity WHERE entity.question_id = :id")
    Flux<QuestionSentTagInfos> findByQuestion(Long id);

    @Query("SELECT * FROM question_sent_tag_infos entity WHERE entity.question_id IS NULL")
    Flux<QuestionSentTagInfos> findAllWhereQuestionIsNull();

    @Override
    <S extends QuestionSentTagInfos> Mono<S> save(S entity);

    @Override
    Flux<QuestionSentTagInfos> findAll();

    @Override
    Mono<QuestionSentTagInfos> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface QuestionSentTagInfosRepositoryInternal {
    <S extends QuestionSentTagInfos> Mono<S> save(S entity);

    Flux<QuestionSentTagInfos> findAllBy(Pageable pageable);

    Flux<QuestionSentTagInfos> findAll();

    Mono<QuestionSentTagInfos> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<QuestionSentTagInfos> findAllBy(Pageable pageable, Criteria criteria);

}
