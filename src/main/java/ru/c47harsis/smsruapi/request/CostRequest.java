package ru.c47harsis.smsruapi.request;

import lombok.*;
import ru.c47harsis.smsruapi.serializer.Param;
import ru.c47harsis.smsruapi.response.CostResponse;

@Value
@AllArgsConstructor
@Builder(toBuilder = true)
public class CostRequest implements ApiRequest<CostResponse> {

    @Param("to")  @NonNull String receiver;
    @Param("msg") @NonNull String text;

    @Override
    public String getMethod() {
        return "/sms/cost";
    }

    @Override
    public Class<? extends CostResponse> getResponseClass() {
        return CostResponse.class;
    }
}
