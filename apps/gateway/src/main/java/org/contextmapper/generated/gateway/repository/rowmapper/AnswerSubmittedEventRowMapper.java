package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.AnswerSubmittedEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link AnswerSubmittedEvent}, with proper type conversions.
 */
@Service
public class AnswerSubmittedEventRowMapper implements BiFunction<Row, String, AnswerSubmittedEvent> {

    private final ColumnConverter converter;

    public AnswerSubmittedEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link AnswerSubmittedEvent} stored in the database.
     */
    @Override
    public AnswerSubmittedEvent apply(Row row, String prefix) {
        AnswerSubmittedEvent entity = new AnswerSubmittedEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAnswerId(converter.fromRow(row, prefix + "_answer_id", Long.class));
        return entity;
    }
}
