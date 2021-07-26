package ru.c47harsis.smsruapi;

import ru.c47harsis.smsruapi.request.ApiRequest;
import ru.c47harsis.smsruapi.response.ApiResponse;

import java.io.IOException;

public interface Callback<T extends ApiRequest<R>, R extends ApiResponse> {

    void onResponse(T request, R response);

    void onFailure(T request, IOException e);
}