package ru.c47harsis.smsruapi.request;

import lombok.Value;
import ru.c47harsis.smsruapi.response.FreeResponse;

@Value
public class FreeRequest implements ApiRequest<FreeResponse> {

    @Override
    public String getMethod() {
        return "/my/free";
    }

    @Override
    public Class<? extends FreeResponse> getResponseClass() {
        return FreeResponse.class;
    }
}
