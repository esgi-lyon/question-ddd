package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.CreatedQuestionEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CreatedQuestionEvent}, with proper type conversions.
 */
@Service
public class CreatedQuestionEventRowMapper implements BiFunction<Row, String, CreatedQuestionEvent> {

    private final ColumnConverter converter;

    public CreatedQuestionEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CreatedQuestionEvent} stored in the database.
     */
    @Override
    public CreatedQuestionEvent apply(Row row, String prefix) {
        CreatedQuestionEvent entity = new CreatedQuestionEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionAndTagId(converter.fromRow(row, prefix + "_question_and_tag_id", Long.class));
        return entity;
    }
}
