package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.CheckAnswerCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CheckAnswerCommand}, with proper type conversions.
 */
@Service
public class CheckAnswerCommandRowMapper implements BiFunction<Row, String, CheckAnswerCommand> {

    private final ColumnConverter converter;

    public CheckAnswerCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CheckAnswerCommand} stored in the database.
     */
    @Override
    public CheckAnswerCommand apply(Row row, String prefix) {
        CheckAnswerCommand entity = new CheckAnswerCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAnswerId(converter.fromRow(row, prefix + "_answer_id", Long.class));
        return entity;
    }
}
