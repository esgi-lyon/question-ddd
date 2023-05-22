package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.QuestionSentTagInfos;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link QuestionSentTagInfos}, with proper type conversions.
 */
@Service
public class QuestionSentTagInfosRowMapper implements BiFunction<Row, String, QuestionSentTagInfos> {

    private final ColumnConverter converter;

    public QuestionSentTagInfosRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link QuestionSentTagInfos} stored in the database.
     */
    @Override
    public QuestionSentTagInfos apply(Row row, String prefix) {
        QuestionSentTagInfos entity = new QuestionSentTagInfos();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Integer.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setQuestionId(converter.fromRow(row, prefix + "_question_id", Long.class));
        return entity;
    }
}
