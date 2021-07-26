package ru.c47harsis.smsruapi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import ru.c47harsis.smsruapi.response.BlacklistAddResponse;
import ru.c47harsis.smsruapi.serializer.Param;

@Value
@AllArgsConstructor
@Builder(toBuilder = true)
@SuppressWarnings("SpellCheckingInspection")
public class BlacklistAddRequest implements ApiRequest<BlacklistAddResponse> {

    @Param("stoplist_phone") @NonNull  String phone;
    @Param("stoplist_text")            String reason;

    public BlacklistAddRequest(@NonNull String phone) {
        this.phone = phone;
        this.reason = "";
    }

    @Override
    public String getMethod() {
        return "/stoplist/add";
    }

    @Override
    public Class<? extends BlacklistAddResponse> getResponseClass() {
        return BlacklistAddResponse.class;
    }
}
