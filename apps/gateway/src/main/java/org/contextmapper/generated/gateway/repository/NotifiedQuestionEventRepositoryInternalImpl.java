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
import org.contextmapper.generated.gateway.domain.NotifiedQuestionEvent;
import org.contextmapper.generated.gateway.repository.rowmapper.NotifiedQuestionEventRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.QuestionSentRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the NotifiedQuestionEvent entity.
 */
@SuppressWarnings("unused")
class NotifiedQuestionEventRepositoryInternalImpl
    extends SimpleR2dbcRepository<NotifiedQuestionEvent, Long>
    implements NotifiedQuestionEventRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final QuestionSentRowMapper questionsentMapper;
    private final NotifiedQuestionEventRowMapper notifiedquestioneventMapper;

    private static final Table entityTable = Table.aliased("notified_question_event", EntityManager.ENTITY_ALIAS);
    private static final Table questionResourceTable = Table.aliased("question_sent", "questionResource");

    public NotifiedQuestionEventRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        QuestionSentRowMapper questionsentMapper,
        NotifiedQuestionEventRowMapper notifiedquestioneventMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(NotifiedQuestionEvent.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.questionsentMapper = questionsentMapper;
        this.notifiedquestioneventMapper = notifiedquestioneventMapper;
    }

    @Override
    public Flux<NotifiedQuestionEvent> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<NotifiedQuestionEvent> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = NotifiedQuestionEventSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(QuestionSentSqlHelper.getColumns(questionResourceTable, "questionResource"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(questionResourceTable)
            .on(Column.create("question_resource_id", entityTable))
            .equals(Column.create("id", questionResourceTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, NotifiedQuestionEvent.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<NotifiedQuestionEvent> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<NotifiedQuestionEvent> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private NotifiedQuestionEvent process(Row row, RowMetadata metadata) {
        NotifiedQuestionEvent entity = notifiedquestioneventMapper.apply(row, "e");
        entity.setQuestionResource(questionsentMapper.apply(row, "questionResource"));
        return entity;
    }

    @Override
    public <S extends NotifiedQuestionEvent> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
