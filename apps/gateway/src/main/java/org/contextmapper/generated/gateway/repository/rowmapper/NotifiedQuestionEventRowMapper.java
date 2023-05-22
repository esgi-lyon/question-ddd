package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.NotifiedQuestionEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link NotifiedQuestionEvent}, with proper type conversions.
 */
@Service
public class NotifiedQuestionEventRowMapper implements BiFunction<Row, String, NotifiedQuestionEvent> {

    private final ColumnConverter converter;

    public NotifiedQuestionEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link NotifiedQuestionEvent} stored in the database.
     */
    @Override
    public NotifiedQuestionEvent apply(Row row, String prefix) {
        NotifiedQuestionEvent entity = new NotifiedQuestionEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionResourceId(converter.fromRow(row, prefix + "_question_resource_id", Long.class));
        return entity;
    }
}
