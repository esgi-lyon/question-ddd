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
import org.contextmapper.generated.gateway.domain.EvaluationId;
import org.contextmapper.generated.gateway.repository.rowmapper.EvaluationIdRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.LeaderBoardRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the EvaluationId entity.
 */
@SuppressWarnings("unused")
class EvaluationIdRepositoryInternalImpl extends SimpleR2dbcRepository<EvaluationId, Long> implements EvaluationIdRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final LeaderBoardRowMapper leaderboardMapper;
    private final EvaluationIdRowMapper evaluationidMapper;

    private static final Table entityTable = Table.aliased("evaluation_id", EntityManager.ENTITY_ALIAS);
    private static final Table leaderBoardTable = Table.aliased("leader_board", "leaderBoard");

    public EvaluationIdRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        LeaderBoardRowMapper leaderboardMapper,
        EvaluationIdRowMapper evaluationidMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(EvaluationId.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.leaderboardMapper = leaderboardMapper;
        this.evaluationidMapper = evaluationidMapper;
    }

    @Override
    public Flux<EvaluationId> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<EvaluationId> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = EvaluationIdSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(LeaderBoardSqlHelper.getColumns(leaderBoardTable, "leaderBoard"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(leaderBoardTable)
            .on(Column.create("leader_board_id", entityTable))
            .equals(Column.create("id", leaderBoardTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, EvaluationId.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<EvaluationId> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<EvaluationId> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private EvaluationId process(Row row, RowMetadata metadata) {
        EvaluationId entity = evaluationidMapper.apply(row, "e");
        entity.setLeaderBoard(leaderboardMapper.apply(row, "leaderBoard"));
        return entity;
    }

    @Override
    public <S extends EvaluationId> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
