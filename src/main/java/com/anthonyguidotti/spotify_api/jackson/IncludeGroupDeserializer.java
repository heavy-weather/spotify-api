package com.anthonyguidotti.spotify_api.jackson;

import com.anthonyguidotti.spotify_api.model.IncludeGroup;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class IncludeGroupDeserializer extends StdDeserializer<IncludeGroup> {

    protected IncludeGroupDeserializer() {
        this(null);
    }
    protected IncludeGroupDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public IncludeGroup deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String data = p.getCodec().readValue(p, String.class);
        for (IncludeGroup includeGroup : IncludeGroup.values()) {
            if (includeGroup.getName().equals(data)) {
                return includeGroup;
            }
        }

        throw new RuntimeException("Could not recognize provided RestrictionReason: " + data);
    }
}
