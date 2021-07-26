package ru.c47harsis.smsruapi.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import ru.c47harsis.smsruapi.ApiResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Value
@AllArgsConstructor
@Builder(toBuilder = true)
@SuppressWarnings("SpellCheckingInspection")
public class BlacklistAllResponse implements ApiResponse {

    @SerializedName("status_code") ApiResponseStatus status;
    @SerializedName("stoplist")    List<Info> list;

    public List<Info> getList() {
        return new ArrayList<>(list);
    }

    @Value
    @Builder(toBuilder = true)
    public static class Info {
        String phone;
        @Builder.Default
        String reason = "";
    }
}