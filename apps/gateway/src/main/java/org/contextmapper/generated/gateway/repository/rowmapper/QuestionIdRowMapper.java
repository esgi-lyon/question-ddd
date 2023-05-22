package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.QuestionId;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link QuestionId}, with proper type conversions.
 */
@Service
public class QuestionIdRowMapper implements BiFunction<Row, String, QuestionId> {

    private final ColumnConverter converter;

    public QuestionIdRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link QuestionId} stored in the database.
     */
    @Override
    public QuestionId apply(Row row, String prefix) {
        QuestionId entity = new QuestionId();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionId(converter.fromRow(row, prefix + "_question_id", Integer.class));
        return entity;
    }
}
