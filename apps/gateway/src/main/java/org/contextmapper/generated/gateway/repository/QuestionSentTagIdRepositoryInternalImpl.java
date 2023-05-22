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
import org.contextmapper.generated.gateway.domain.QuestionSentTagId;
import org.contextmapper.generated.gateway.repository.rowmapper.QuestionSentRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.QuestionSentTagIdRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the QuestionSentTagId entity.
 */
@SuppressWarnings("unused")
class QuestionSentTagIdRepositoryInternalImpl
    extends SimpleR2dbcRepository<QuestionSentTagId, Long>
    implements QuestionSentTagIdRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final QuestionSentRowMapper questionsentMapper;
    private final QuestionSentTagIdRowMapper questionsenttagidMapper;

    private static final Table entityTable = Table.aliased("question_sent_tag_id", EntityManager.ENTITY_ALIAS);
    private static final Table questionSentTable = Table.aliased("question_sent", "questionSent");

    public QuestionSentTagIdRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        QuestionSentRowMapper questionsentMapper,
        QuestionSentTagIdRowMapper questionsenttagidMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(QuestionSentTagId.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.questionsentMapper = questionsentMapper;
        this.questionsenttagidMapper = questionsenttagidMapper;
    }

    @Override
    public Flux<QuestionSentTagId> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<QuestionSentTagId> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = QuestionSentTagIdSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(QuestionSentSqlHelper.getColumns(questionSentTable, "questionSent"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(questionSentTable)
            .on(Column.create("question_sent_id", entityTable))
            .equals(Column.create("id", questionSentTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, QuestionSentTagId.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<QuestionSentTagId> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<QuestionSentTagId> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private QuestionSentTagId process(Row row, RowMetadata metadata) {
        QuestionSentTagId entity = questionsenttagidMapper.apply(row, "e");
        entity.setQuestionSent(questionsentMapper.apply(row, "questionSent"));
        return entity;
    }

    @Override
    public <S extends QuestionSentTagId> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
