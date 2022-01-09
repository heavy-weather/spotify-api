package com.anthonyguidotti.spotify_api.jackson;

import com.anthonyguidotti.spotify_api.model.Episode;
import com.anthonyguidotti.spotify_api.model.Track;
import com.anthonyguidotti.spotify_api.model.TrackOrEpisode;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class TrackOrEpisodeDeserializer extends StdDeserializer<TrackOrEpisode> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    protected TrackOrEpisodeDeserializer() {
        this(null);
    }

    protected TrackOrEpisodeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public TrackOrEpisode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode json = p.getCodec().readTree(p);

        if (json.has("album") ||
                json.has("artists") ||
                json.has("disc_number") ||
                json.has("linked_from") ||
                json.has("track_number")) {
            return objectMapper.readValue(p, Track.class);
        } else {
            return objectMapper.readValue(p, Episode.class);
        }
    }
}
