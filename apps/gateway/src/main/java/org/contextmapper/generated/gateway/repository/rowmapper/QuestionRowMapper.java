package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.Question;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Question}, with proper type conversions.
 */
@Service
public class QuestionRowMapper implements BiFunction<Row, String, Question> {

    private final ColumnConverter converter;

    public QuestionRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Question} stored in the database.
     */
    @Override
    public Question apply(Row row, String prefix) {
        Question entity = new Question();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setResourceCorrectTagId(converter.fromRow(row, prefix + "_resource_correct_tag_id", Long.class));
        return entity;
    }
}
