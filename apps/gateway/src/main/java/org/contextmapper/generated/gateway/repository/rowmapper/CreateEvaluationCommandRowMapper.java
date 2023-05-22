package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.CreateEvaluationCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CreateEvaluationCommand}, with proper type conversions.
 */
@Service
public class CreateEvaluationCommandRowMapper implements BiFunction<Row, String, CreateEvaluationCommand> {

    private final ColumnConverter converter;

    public CreateEvaluationCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CreateEvaluationCommand} stored in the database.
     */
    @Override
    public CreateEvaluationCommand apply(Row row, String prefix) {
        CreateEvaluationCommand entity = new CreateEvaluationCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAnswerId(converter.fromRow(row, prefix + "_answer_id", Long.class));
        return entity;
    }
}
