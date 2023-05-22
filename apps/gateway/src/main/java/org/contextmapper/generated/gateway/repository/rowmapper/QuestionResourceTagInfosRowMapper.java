package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.QuestionResourceTagInfos;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link QuestionResourceTagInfos}, with proper type conversions.
 */
@Service
public class QuestionResourceTagInfosRowMapper implements BiFunction<Row, String, QuestionResourceTagInfos> {

    private final ColumnConverter converter;

    public QuestionResourceTagInfosRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link QuestionResourceTagInfos} stored in the database.
     */
    @Override
    public QuestionResourceTagInfos apply(Row row, String prefix) {
        QuestionResourceTagInfos entity = new QuestionResourceTagInfos();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Integer.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        return entity;
    }
}
