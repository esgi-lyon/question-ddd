package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.AnsweredTag;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link AnsweredTag}, with proper type conversions.
 */
@Service
public class AnsweredTagRowMapper implements BiFunction<Row, String, AnsweredTag> {

    private final ColumnConverter converter;

    public AnsweredTagRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link AnsweredTag} stored in the database.
     */
    @Override
    public AnsweredTag apply(Row row, String prefix) {
        AnsweredTag entity = new AnsweredTag();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Integer.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        return entity;
    }
}
