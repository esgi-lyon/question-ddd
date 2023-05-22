package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.CreateResourceCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CreateResourceCommand}, with proper type conversions.
 */
@Service
public class CreateResourceCommandRowMapper implements BiFunction<Row, String, CreateResourceCommand> {

    private final ColumnConverter converter;

    public CreateResourceCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CreateResourceCommand} stored in the database.
     */
    @Override
    public CreateResourceCommand apply(Row row, String prefix) {
        CreateResourceCommand entity = new CreateResourceCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionIdId(converter.fromRow(row, prefix + "_question_id_id", Long.class));
        return entity;
    }
}
