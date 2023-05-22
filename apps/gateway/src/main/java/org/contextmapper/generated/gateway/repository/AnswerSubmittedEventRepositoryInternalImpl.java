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
import org.contextmapper.generated.gateway.domain.AnswerSubmittedEvent;
import org.contextmapper.generated.gateway.repository.rowmapper.AnswerRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.AnswerSubmittedEventRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the AnswerSubmittedEvent entity.
 */
@SuppressWarnings("unused")
class AnswerSubmittedEventRepositoryInternalImpl
    extends SimpleR2dbcRepository<AnswerSubmittedEvent, Long>
    implements AnswerSubmittedEventRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final AnswerRowMapper answerMapper;
    private final AnswerSubmittedEventRowMapper answersubmittedeventMapper;

    private static final Table entityTable = Table.aliased("answer_submitted_event", EntityManager.ENTITY_ALIAS);
    private static final Table answerTable = Table.aliased("answer", "answer");

    public AnswerSubmittedEventRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        AnswerRowMapper answerMapper,
        AnswerSubmittedEventRowMapper answersubmittedeventMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(AnswerSubmittedEvent.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.answerMapper = answerMapper;
        this.answersubmittedeventMapper = answersubmittedeventMapper;
    }

    @Override
    public Flux<AnswerSubmittedEvent> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<AnswerSubmittedEvent> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = AnswerSubmittedEventSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(AnswerSqlHelper.getColumns(answerTable, "answer"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(answerTable)
            .on(Column.create("answer_id", entityTable))
            .equals(Column.create("id", answerTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, AnswerSubmittedEvent.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<AnswerSubmittedEvent> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<AnswerSubmittedEvent> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private AnswerSubmittedEvent process(Row row, RowMetadata metadata) {
        AnswerSubmittedEvent entity = answersubmittedeventMapper.apply(row, "e");
        entity.setAnswer(answerMapper.apply(row, "answer"));
        return entity;
    }

    @Override
    public <S extends AnswerSubmittedEvent> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
