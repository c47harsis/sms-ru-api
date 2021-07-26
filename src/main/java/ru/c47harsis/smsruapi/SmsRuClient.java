package ru.c47harsis.smsruapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import ru.c47harsis.smsruapi.adapter.ApiResponseStatusDeserializer;
import ru.c47harsis.smsruapi.adapter.BlacklistInfoDeserializer;
import ru.c47harsis.smsruapi.adapter.CostInfoDeserializer;
import ru.c47harsis.smsruapi.adapter.SendInfoDeserializer;
import ru.c47harsis.smsruapi.auth.AuthProvider;
import ru.c47harsis.smsruapi.request.ApiRequest;
import ru.c47harsis.smsruapi.response.ApiResponse;
import ru.c47harsis.smsruapi.response.BlacklistAllResponse;
import ru.c47harsis.smsruapi.response.CostResponse;
import ru.c47harsis.smsruapi.response.SendResponse;
import ru.c47harsis.smsruapi.serializer.Serializer;

import java.io.IOException;
import java.util.List;

@Builder
@RequiredArgsConstructor
public class SmsRuClient {

    @Builder.Default
    private final String apiUrl = "https://sms.ru";

    @Builder.Default
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ApiResponseStatus.class, new ApiResponseStatusDeserializer())
            .registerTypeAdapter(TypeToken.getParameterized(List.class, CostResponse.Info.class).getType(),
                    new CostInfoDeserializer())
            .registerTypeAdapter(TypeToken.getParameterized(List.class, SendResponse.Info.class).getType(),
                    new SendInfoDeserializer())
            .registerTypeAdapter(TypeToken.getParameterized(List.class, BlacklistAllResponse.Info.class).getType(),
                    new BlacklistInfoDeserializer())
            .create();

    @Builder.Default
    private final @NonNull OkHttpClient httpClient = new OkHttpClient();
    private final @NonNull AuthProvider authProvider;

    public <T extends ApiRequest<R>, R extends ApiResponse> R send(T request) {
        try {
            Response response = httpClient.newCall(buildRequest(request)).execute();
            return gson.fromJson(response.body().string(), request.getResponseClass());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends ApiRequest<R>, R extends ApiResponse> void send(final T request, final Callback<T, R> callback) {
        httpClient.newCall(buildRequest(request)).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                R result = null;
                Exception exception = null;
                try {
                    result = gson.fromJson(response.body().string(), request.getResponseClass());
                } catch (Exception e) {
                    exception = e;
                }
                if (result != null) {
                    callback.onResponse(request, result);
                    return;
                }
                if (exception != null) {
                    IOException ioEx = exception instanceof IOException
                            ? (IOException) exception : new IOException(exception);
                    callback.onFailure(request, ioEx);
                    return;
                }
                callback.onFailure(request, new IOException("Empty response"));
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onFailure(request, e);
            }
        });
    }

    public void shutdown() {
        httpClient.dispatcher().executorService().shutdown();
    }

    private Request buildRequest(ApiRequest<?> request) {
        return new Request.Builder()
                .url(apiUrl + request.getMethod())
                .post(createRequestBody(request))
                .build();
    }

    private RequestBody createRequestBody(ApiRequest<?> request) {
        FormBody.Builder builder = new FormBody.Builder();
        Serializer.serialize(request).forEach(builder::add);
        Serializer.serialize(authProvider).forEach(builder::add);
        builder.add("json", "1");
        return builder.build();
    }
}