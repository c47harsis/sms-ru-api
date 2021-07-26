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
public class SendResponse implements ApiResponse {

    @SerializedName("status_code")  ApiResponseStatus status;
    @SerializedName("balance")      Double balance;
    @SerializedName("sms")          List<Info> info;

    public List<Info> getInfo() {
        return new ArrayList<>(info);
    }

    @Value
    @Builder(toBuilder = true)
    public static class Info {
        String phone;
        ApiResponseStatus status;
        String messageId;
        String statusMessage;
    }
}
