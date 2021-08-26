package com.anthonyguidotti.spotify_api.jackson;

import com.anthonyguidotti.spotify_api.response.ErrorResponse;
import com.anthonyguidotti.spotify_api.response.SpotifyAPIResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Flow;

public class JacksonDeserializerBodySubscriber implements HttpResponse.BodySubscriber<SpotifyAPIResponse> {
    private static final Logger logger = LoggerFactory.getLogger(JacksonDeserializerBodySubscriber.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private List<ByteBuffer> response;
    private final CompletableFuture<SpotifyAPIResponse> future = new CompletableFuture<>();
    private final Class<? extends SpotifyAPIResponse> type;
    private volatile Flow.Subscription subscription;

    public JacksonDeserializerBodySubscriber(Class<? extends SpotifyAPIResponse> type) {
        this.type = type;
    }

    @Override
    public CompletionStage<SpotifyAPIResponse> getBody() {
        return future;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        response = new ArrayList<>();
        this.subscription = subscription;
        this.subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(List<ByteBuffer> items) {
        response.addAll(items);
    }

    @Override
    public void onError(Throwable throwable) {
        throw new RuntimeException(throwable);
    }

    @Override
    public void onComplete() {
        List<byte[]> byteArrays = new ArrayList<>();
        int totalSize = 0;
        for (ByteBuffer byteBuffer : response) {
            int size = byteBuffer.remaining();
            totalSize += size;
            byte[] byteArray = new byte[size];
            byteBuffer.get(byteArray);
            byteArrays.add(byteArray);
        }

        int i = 0;
        byte[] bytes = new byte[totalSize];
        for (byte[] byteArray : byteArrays) {
            for (byte b : byteArray) {
                 bytes[i] = b;
                 i++;
            }
        }

        try {
            JsonNode json = objectMapper.readTree(bytes);
            if (json.has("error")) {
                future.complete(objectMapper.readValue(bytes, ErrorResponse.class));
            } else {
                future.complete(objectMapper.readValue(bytes, type));
            }
        } catch (IOException e) {
            logger.error("Could not deserialize response body into {}", type, e);
            future.complete(null);
        }
    }
}