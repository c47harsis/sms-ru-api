package ru.c47harsis.smsruapi.request;

import ru.c47harsis.smsruapi.response.ApiResponse;

public interface ApiRequest<R extends ApiResponse> {
    String getMethod();
    Class<? extends R> getResponseClass();
}
