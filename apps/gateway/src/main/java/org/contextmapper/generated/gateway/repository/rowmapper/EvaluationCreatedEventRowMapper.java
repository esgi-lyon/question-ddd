package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.EvaluationCreatedEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link EvaluationCreatedEvent}, with proper type conversions.
 */
@Service
public class EvaluationCreatedEventRowMapper implements BiFunction<Row, String, EvaluationCreatedEvent> {

    private final ColumnConverter converter;

    public EvaluationCreatedEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link EvaluationCreatedEvent} stored in the database.
     */
    @Override
    public EvaluationCreatedEvent apply(Row row, String prefix) {
        EvaluationCreatedEvent entity = new EvaluationCreatedEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setEvaluationId(converter.fromRow(row, prefix + "_evaluation_id", Long.class));
        return entity;
    }
}
