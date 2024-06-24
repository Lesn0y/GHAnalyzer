package com.lesnoy.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.lesnoy.model.GHRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GHRepositoryDeserializer extends StdDeserializer<GHRepository> {

    private static final Log log = LogFactory.getLog(GHRepositoryDeserializer.class);

    public GHRepositoryDeserializer() {
        this(null);
    }

    public GHRepositoryDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GHRepository deserialize(JsonParser jp, DeserializationContext context) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);
        String fullName = node.get("full_name").asText();
        String repUrl = node.get("url").asText();
        String language = node.get("language").asText();
        Date createdAt = null;
        Date updatedAt = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            createdAt = sdf.parse(node.get("created_at").asText());
            updatedAt = sdf.parse(node.get("updated_at").asText());
        } catch (ParseException e) {
            log.error(e);
        }

        return new GHRepository(fullName, repUrl, createdAt, updatedAt, language);
    }
}
