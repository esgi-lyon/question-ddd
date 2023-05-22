package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.StatisticSubjectUser;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link StatisticSubjectUser}, with proper type conversions.
 */
@Service
public class StatisticSubjectUserRowMapper implements BiFunction<Row, String, StatisticSubjectUser> {

    private final ColumnConverter converter;

    public StatisticSubjectUserRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link StatisticSubjectUser} stored in the database.
     */
    @Override
    public StatisticSubjectUser apply(Row row, String prefix) {
        StatisticSubjectUser entity = new StatisticSubjectUser();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setUserId(converter.fromRow(row, prefix + "_user_id", Integer.class));
        return entity;
    }
}
