package org.contextmapper.generated.gateway.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiFunction;
import org.apache.commons.lang3.StringUtils;
import org.contextmapper.generated.gateway.domain.QuestionStatsViewedEvent;
import org.contextmapper.generated.gateway.repository.rowmapper.QuestionStatsViewedEventRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.StatisticSubjectQuestionRowMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoinCondition;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC custom repository implementation for the QuestionStatsViewedEvent entity.
 */
@SuppressWarnings("unused")
class QuestionStatsViewedEventRepositoryInternalImpl
    extends SimpleR2dbcRepository<QuestionStatsViewedEvent, Long>
    implements QuestionStatsViewedEventRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final StatisticSubjectQuestionRowMapper statisticsubjectquestionMapper;
    private final QuestionStatsViewedEventRowMapper questionstatsviewedeventMapper;

    private static final Table entityTable = Table.aliased("question_stats_viewed_event", EntityManager.ENTITY_ALIAS);
    private static final Table questionTable = Table.aliased("statistic_subject_question", "question");

    public QuestionStatsViewedEventRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        StatisticSubjectQuestionRowMapper statisticsubjectquestionMapper,
        QuestionStatsViewedEventRowMapper questionstatsviewedeventMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(
                converter.getMappingContext().getRequiredPersistentEntity(QuestionStatsViewedEvent.class)
            ),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.statisticsubjectquestionMapper = statisticsubjectquestionMapper;
        this.questionstatsviewedeventMapper = questionstatsviewedeventMapper;
    }

    @Override
    public Flux<QuestionStatsViewedEvent> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<QuestionStatsViewedEvent> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = QuestionStatsViewedEventSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(StatisticSubjectQuestionSqlHelper.getColumns(questionTable, "question"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(questionTable)
            .on(Column.create("question_id", entityTable))
            .equals(Column.create("id", questionTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, QuestionStatsViewedEvent.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<QuestionStatsViewedEvent> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<QuestionStatsViewedEvent> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private QuestionStatsViewedEvent process(Row row, RowMetadata metadata) {
        QuestionStatsViewedEvent entity = questionstatsviewedeventMapper.apply(row, "e");
        entity.setQuestion(statisticsubjectquestionMapper.apply(row, "question"));
        return entity;
    }

    @Override
    public <S extends QuestionStatsViewedEvent> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
