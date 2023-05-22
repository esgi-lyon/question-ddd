package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.EvaluationQuestion;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link EvaluationQuestion}, with proper type conversions.
 */
@Service
public class EvaluationQuestionRowMapper implements BiFunction<Row, String, EvaluationQuestion> {

    private final ColumnConverter converter;

    public EvaluationQuestionRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link EvaluationQuestion} stored in the database.
     */
    @Override
    public EvaluationQuestion apply(Row row, String prefix) {
        EvaluationQuestion entity = new EvaluationQuestion();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionId(converter.fromRow(row, prefix + "_question_id", Integer.class));
        return entity;
    }
}
