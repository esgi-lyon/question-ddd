package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.QuestionStatsViewedEvent;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link QuestionStatsViewedEvent}, with proper type conversions.
 */
@Service
public class QuestionStatsViewedEventRowMapper implements BiFunction<Row, String, QuestionStatsViewedEvent> {

    private final ColumnConverter converter;

    public QuestionStatsViewedEventRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link QuestionStatsViewedEvent} stored in the database.
     */
    @Override
    public QuestionStatsViewedEvent apply(Row row, String prefix) {
        QuestionStatsViewedEvent entity = new QuestionStatsViewedEvent();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionId(converter.fromRow(row, prefix + "_question_id", Long.class));
        return entity;
    }
}
