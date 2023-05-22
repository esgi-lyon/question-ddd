package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.AnswerSubmitCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link AnswerSubmitCommand}, with proper type conversions.
 */
@Service
public class AnswerSubmitCommandRowMapper implements BiFunction<Row, String, AnswerSubmitCommand> {

    private final ColumnConverter converter;

    public AnswerSubmitCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link AnswerSubmitCommand} stored in the database.
     */
    @Override
    public AnswerSubmitCommand apply(Row row, String prefix) {
        AnswerSubmitCommand entity = new AnswerSubmitCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAnswerId(converter.fromRow(row, prefix + "_answer_id", Long.class));
        return entity;
    }
}
