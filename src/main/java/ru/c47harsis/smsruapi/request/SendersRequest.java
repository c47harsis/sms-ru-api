package ru.c47harsis.smsruapi.request;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.c47harsis.smsruapi.response.SendersResponse;

@ToString
@EqualsAndHashCode
public class SendersRequest implements ApiRequest<SendersResponse> {

    @Override
    public String getMethod() {
        return "/my/senders";
    }

    @Override
    public Class<? extends SendersResponse> getResponseClass() {
        return SendersResponse.class;
    }
}
