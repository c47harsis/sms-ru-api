package ru.c47harsis.smsruapi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import ru.c47harsis.smsruapi.response.BlacklistRemoveResponse;
import ru.c47harsis.smsruapi.serializer.Param;

@Value
@AllArgsConstructor
@Builder(toBuilder = true)
@SuppressWarnings("SpellCheckingInspection")
public class BlacklistRemoveRequest implements ApiRequest<BlacklistRemoveResponse> {

    @Param("stoplist_phone") @NonNull String phone;

    @Override
    public String getMethod() {
        return "/stoplist/del";
    }

    @Override
    public Class<? extends BlacklistRemoveResponse> getResponseClass() {
        return BlacklistRemoveResponse.class;
    }
}
