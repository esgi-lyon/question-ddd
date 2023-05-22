package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.CreateQuestionCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CreateQuestionCommand}, with proper type conversions.
 */
@Service
public class CreateQuestionCommandRowMapper implements BiFunction<Row, String, CreateQuestionCommand> {

    private final ColumnConverter converter;

    public CreateQuestionCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CreateQuestionCommand} stored in the database.
     */
    @Override
    public CreateQuestionCommand apply(Row row, String prefix) {
        CreateQuestionCommand entity = new CreateQuestionCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setResourceId(converter.fromRow(row, prefix + "_resource_id", Long.class));
        return entity;
    }
}
