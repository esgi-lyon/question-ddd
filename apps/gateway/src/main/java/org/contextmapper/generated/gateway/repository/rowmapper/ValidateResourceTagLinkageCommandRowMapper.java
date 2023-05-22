package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.ValidateResourceTagLinkageCommand;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ValidateResourceTagLinkageCommand}, with proper type conversions.
 */
@Service
public class ValidateResourceTagLinkageCommandRowMapper implements BiFunction<Row, String, ValidateResourceTagLinkageCommand> {

    private final ColumnConverter converter;

    public ValidateResourceTagLinkageCommandRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ValidateResourceTagLinkageCommand} stored in the database.
     */
    @Override
    public ValidateResourceTagLinkageCommand apply(Row row, String prefix) {
        ValidateResourceTagLinkageCommand entity = new ValidateResourceTagLinkageCommand();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setQuestionIdId(converter.fromRow(row, prefix + "_question_id_id", Long.class));
        return entity;
    }
}
