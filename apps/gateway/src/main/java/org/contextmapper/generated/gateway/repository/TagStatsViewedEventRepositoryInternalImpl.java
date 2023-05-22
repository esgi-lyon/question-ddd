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
import org.contextmapper.generated.gateway.domain.TagStatsViewedEvent;
import org.contextmapper.generated.gateway.repository.rowmapper.StatisticSubjectTagRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.TagStatsViewedEventRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the TagStatsViewedEvent entity.
 */
@SuppressWarnings("unused")
class TagStatsViewedEventRepositoryInternalImpl
    extends SimpleR2dbcRepository<TagStatsViewedEvent, Long>
    implements TagStatsViewedEventRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final StatisticSubjectTagRowMapper statisticsubjecttagMapper;
    private final TagStatsViewedEventRowMapper tagstatsviewedeventMapper;

    private static final Table entityTable = Table.aliased("tag_stats_viewed_event", EntityManager.ENTITY_ALIAS);
    private static final Table tagTable = Table.aliased("statistic_subject_tag", "tag");

    public TagStatsViewedEventRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        StatisticSubjectTagRowMapper statisticsubjecttagMapper,
        TagStatsViewedEventRowMapper tagstatsviewedeventMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(TagStatsViewedEvent.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.statisticsubjecttagMapper = statisticsubjecttagMapper;
        this.tagstatsviewedeventMapper = tagstatsviewedeventMapper;
    }

    @Override
    public Flux<TagStatsViewedEvent> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<TagStatsViewedEvent> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = TagStatsViewedEventSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(StatisticSubjectTagSqlHelper.getColumns(tagTable, "tag"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(tagTable)
            .on(Column.create("tag_id", entityTable))
            .equals(Column.create("id", tagTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, TagStatsViewedEvent.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<TagStatsViewedEvent> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<TagStatsViewedEvent> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private TagStatsViewedEvent process(Row row, RowMetadata metadata) {
        TagStatsViewedEvent entity = tagstatsviewedeventMapper.apply(row, "e");
        entity.setTag(statisticsubjecttagMapper.apply(row, "tag"));
        return entity;
    }

    @Override
    public <S extends TagStatsViewedEvent> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
