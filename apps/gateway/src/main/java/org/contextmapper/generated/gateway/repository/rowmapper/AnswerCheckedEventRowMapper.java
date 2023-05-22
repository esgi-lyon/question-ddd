package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.AnswerCheckedEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link AnswerCheckedEvent}, with proper type conversions.
 */
@Service
public class AnswerCheckedEventRowMapper implements BiFunction<Row, String, AnswerCheckedEvent> {

    private final ColumnConverter converter;

    public AnswerCheckedEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link AnswerCheckedEvent} stored in the database.
     */
    @Override
    public AnswerCheckedEvent apply(Row row, String prefix) {
        AnswerCheckedEvent entity = new AnswerCheckedEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAnswerId(converter.fromRow(row, prefix + "_answer_id", Long.class));
        return entity;
    }
}
