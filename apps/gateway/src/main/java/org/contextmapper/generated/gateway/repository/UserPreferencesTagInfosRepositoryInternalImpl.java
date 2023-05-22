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
import org.contextmapper.generated.gateway.domain.UserPreferencesTagInfos;
import org.contextmapper.generated.gateway.repository.rowmapper.UserPreferencesRowMapper;
import org.contextmapper.generated.gateway.repository.rowmapper.UserPreferencesTagInfosRowMapper;
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
 * Spring Data R2DBC custom repository implementation for the UserPreferencesTagInfos entity.
 */
@SuppressWarnings("unused")
class UserPreferencesTagInfosRepositoryInternalImpl
    extends SimpleR2dbcRepository<UserPreferencesTagInfos, Long>
    implements UserPreferencesTagInfosRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final UserPreferencesRowMapper userpreferencesMapper;
    private final UserPreferencesTagInfosRowMapper userpreferencestaginfosMapper;

    private static final Table entityTable = Table.aliased("user_preferences_tag_infos", EntityManager.ENTITY_ALIAS);
    private static final Table userPreferencesTable = Table.aliased("user_preferences", "userPreferences");

    public UserPreferencesTagInfosRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        UserPreferencesRowMapper userpreferencesMapper,
        UserPreferencesTagInfosRowMapper userpreferencestaginfosMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(
                converter.getMappingContext().getRequiredPersistentEntity(UserPreferencesTagInfos.class)
            ),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.userpreferencesMapper = userpreferencesMapper;
        this.userpreferencestaginfosMapper = userpreferencestaginfosMapper;
    }

    @Override
    public Flux<UserPreferencesTagInfos> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<UserPreferencesTagInfos> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = UserPreferencesTagInfosSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(UserPreferencesSqlHelper.getColumns(userPreferencesTable, "userPreferences"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(userPreferencesTable)
            .on(Column.create("user_preferences_id", entityTable))
            .equals(Column.create("id", userPreferencesTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, UserPreferencesTagInfos.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<UserPreferencesTagInfos> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<UserPreferencesTagInfos> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private UserPreferencesTagInfos process(Row row, RowMetadata metadata) {
        UserPreferencesTagInfos entity = userpreferencestaginfosMapper.apply(row, "e");
        entity.setUserPreferences(userpreferencesMapper.apply(row, "userPreferences"));
        return entity;
    }

    @Override
    public <S extends UserPreferencesTagInfos> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
