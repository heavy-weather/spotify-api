package com.anthonyguidotti.spotify_api.jackson;

import com.anthonyguidotti.spotify_api.model.RestrictionReason;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class RestrictionReasonDeserializer extends StdDeserializer<RestrictionReason> {
    protected RestrictionReasonDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RestrictionReason deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String data = p.getCodec().readValue(p, String.class);
        for (RestrictionReason reason : RestrictionReason.values()) {
            if (reason.getName().equals(data)) {
                return reason;
            }
        }

        throw new RuntimeException("Could not recognize provided RestrictionReason: " + data);
    }
}
