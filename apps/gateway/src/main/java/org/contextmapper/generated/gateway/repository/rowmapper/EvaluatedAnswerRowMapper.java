package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.EvaluatedAnswer;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link EvaluatedAnswer}, with proper type conversions.
 */
@Service
public class EvaluatedAnswerRowMapper implements BiFunction<Row, String, EvaluatedAnswer> {

    private final ColumnConverter converter;

    public EvaluatedAnswerRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link EvaluatedAnswer} stored in the database.
     */
    @Override
    public EvaluatedAnswer apply(Row row, String prefix) {
        EvaluatedAnswer entity = new EvaluatedAnswer();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAnswerId(converter.fromRow(row, prefix + "_answer_id", Integer.class));
        return entity;
    }
}
