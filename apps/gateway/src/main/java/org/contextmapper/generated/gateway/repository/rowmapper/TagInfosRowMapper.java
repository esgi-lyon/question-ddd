package org.contextmapper.generated.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.contextmapper.generated.gateway.domain.TagInfos;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link TagInfos}, with proper type conversions.
 */
@Service
public class TagInfosRowMapper implements BiFunction<Row, String, TagInfos> {

    private final ColumnConverter converter;

    public TagInfosRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link TagInfos} stored in the database.
     */
    @Override
    public TagInfos apply(Row row, String prefix) {
        TagInfos entity = new TagInfos();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTagId(converter.fromRow(row, prefix + "_tag_id", Integer.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        return entity;
    }
}
