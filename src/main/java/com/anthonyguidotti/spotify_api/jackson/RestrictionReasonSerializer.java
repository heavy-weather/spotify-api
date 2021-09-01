package com.anthonyguidotti.spotify_api.jackson;

import com.anthonyguidotti.spotify_api.model.RestrictionReason;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class RestrictionReasonSerializer extends StdSerializer<RestrictionReason> {

    protected RestrictionReasonSerializer() {
        this(null);
    }

    protected RestrictionReasonSerializer(Class<RestrictionReason> t) {
        super(t);
    }

    @Override
    public void serialize(RestrictionReason value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getName());
    }
}
