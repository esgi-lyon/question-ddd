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
import org.contextmapper.generated.gateway.domain.ViewStatsCommand;
import org.contextmapper.generated.gateway.repository.rowmapper.StatisticSubjectQuestionRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.StatisticSubjectTagRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.StatisticSubjectUserRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.ViewStatsCommandRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the ViewStatsCommand entity.
 */
@SuppressWarnings("unused")
class ViewStatsCommandRepositoryInternalImpl
    extends SimpleR2dbcRepository<ViewStatsCommand, Long>
    implements ViewStatsCommandRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final StatisticSubjectUserRowMapper statisticsubjectuserMapper;
    private final StatisticSubjectQuestionRowMapper statisticsubjectquestionMapper;
    private final StatisticSubjectTagRowMapper statisticsubjecttagMapper;
    private final ViewStatsCommandRowMapper viewstatscommandMapper;

    private static final Table entityTable = Table.aliased("view_stats_command", EntityManager.ENTITY_ALIAS);
    private static final Table userTable = Table.aliased("statistic_subject_user", "e_user");
    private static final Table questionTable = Table.aliased("statistic_subject_question", "question");
    private static final Table tagTable = Table.aliased("statistic_subject_tag", "tag");

    public ViewStatsCommandRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        StatisticSubjectUserRowMapper statisticsubjectuserMapper,
        StatisticSubjectQuestionRowMapper statisticsubjectquestionMapper,
        StatisticSubjectTagRowMapper statisticsubjecttagMapper,
        ViewStatsCommandRowMapper viewstatscommandMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(ViewStatsCommand.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.statisticsubjectuserMapper = statisticsubjectuserMapper;
        this.statisticsubjectquestionMapper = statisticsubjectquestionMapper;
        this.statisticsubjecttagMapper = statisticsubjecttagMapper;
        this.viewstatscommandMapper = viewstatscommandMapper;
    }

    @Override
    public Flux<ViewStatsCommand> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<ViewStatsCommand> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = ViewStatsCommandSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(StatisticSubjectUserSqlHelper.getColumns(userTable, "user"));
        columns.addAll(StatisticSubjectQuestionSqlHelper.getColumns(questionTable, "question"));
        columns.addAll(StatisticSubjectTagSqlHelper.getColumns(tagTable, "tag"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(userTable)
            .on(Column.create("user_id", entityTable))
            .equals(Column.create("id", userTable))
            .leftOuterJoin(questionTable)
            .on(Column.create("question_id", entityTable))
            .equals(Column.create("id", questionTable))
            .leftOuterJoin(tagTable)
            .on(Column.create("tag_id", entityTable))
            .equals(Column.create("id", tagTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, ViewStatsCommand.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<ViewStatsCommand> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<ViewStatsCommand> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private ViewStatsCommand process(Row row, RowMetadata metadata) {
        ViewStatsCommand entity = viewstatscommandMapper.apply(row, "e");
        entity.setUser(statisticsubjectuserMapper.apply(row, "user"));
        entity.setQuestion(statisticsubjectquestionMapper.apply(row, "question"));
        entity.setTag(statisticsubjecttagMapper.apply(row, "tag"));
        return entity;
    }

    @Override
    public <S extends ViewStatsCommand> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
