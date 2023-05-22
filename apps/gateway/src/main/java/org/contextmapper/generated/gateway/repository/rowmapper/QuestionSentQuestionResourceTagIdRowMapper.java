package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.QuestionSentQuestionResourceTagId;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link QuestionSentQuestionResourceTagId}, with proper type conversions.
 */
@Service
public class QuestionSentQuestionResourceTagIdRowMapper implements BiFunction<Row, String, QuestionSentQuestionResourceTagId> {

    private final ColumnConverter converter;

    public QuestionSentQuestionResourceTagIdRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link QuestionSentQuestionResourceTagId} stored in the database.
     */
    @Override
    public QuestionSentQuestionResourceTagId apply(Row row, String prefix) {
        QuestionSentQuestionResourceTagId entity = new QuestionSentQuestionResourceTagId();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Integer.class));
        return entity;
    }
}
