package ru.c47harsis.smsruapi.request;

import lombok.*;
import ru.c47harsis.smsruapi.response.BalanceResponse;

@Value
public class BalanceRequest implements ApiRequest<BalanceResponse> {

    @Override
    public String getMethod() {
        return "/my/balance";
    }

    @Override
    public Class<? extends BalanceResponse> getResponseClass() {
        return BalanceResponse.class;
    }
}
