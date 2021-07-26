package ru.c47harsis.smsruapi.request;

import lombok.Value;
import ru.c47harsis.smsruapi.response.LimitResponse;

@Value
public class LimitRequest implements ApiRequest<LimitResponse> {

    @Override
    public String getMethod() {
        return "/my/limit";
    }

    @Override
    public Class<? extends LimitResponse> getResponseClass() {
        return LimitResponse.class;
    }
}
