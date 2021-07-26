package ru.c47harsis.smsruapi.request;

import lombok.Value;
import ru.c47harsis.smsruapi.response.BlacklistAllResponse;

@Value
@SuppressWarnings("SpellCheckingInspection")
public class BlacklistAllRequest implements ApiRequest<BlacklistAllResponse> {

    @Override
    public String getMethod() {
        return "/stoplist/get";
    }

    @Override
    public Class<? extends BlacklistAllResponse> getResponseClass() {
        return BlacklistAllResponse.class;
    }
}
