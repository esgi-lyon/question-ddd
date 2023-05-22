package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.StatisticSubjectTag;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link StatisticSubjectTag}, with proper type conversions.
 */
@Service
public class StatisticSubjectTagRowMapper implements BiFunction<Row, String, StatisticSubjectTag> {

    private final ColumnConverter converter;

    public StatisticSubjectTagRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link StatisticSubjectTag} stored in the database.
     */
    @Override
    public StatisticSubjectTag apply(Row row, String prefix) {
        StatisticSubjectTag entity = new StatisticSubjectTag();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Integer.class));
        return entity;
    }
}
