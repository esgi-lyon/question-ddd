package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.EvaluationTag;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link EvaluationTag}, with proper type conversions.
 */
@Service
public class EvaluationTagRowMapper implements BiFunction<Row, String, EvaluationTag> {

    private final ColumnConverter converter;

    public EvaluationTagRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link EvaluationTag} stored in the database.
     */
    @Override
    public EvaluationTag apply(Row row, String prefix) {
        EvaluationTag entity = new EvaluationTag();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Integer.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        return entity;
    }
}
