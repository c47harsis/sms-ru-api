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
public class CostResponse implements ApiResponse {

    @SerializedName("status_code")  ApiResponseStatus status;
    @SerializedName("total_cost")   Double totalCost;
    @SerializedName("total_sms")    Integer messagesCount;
    @SerializedName("status_text")  String statusMessage;
    @SerializedName("sms")          List<Info> info;

    public List<Info> getInfo() {
        return new ArrayList<>(info);
    }

    @Value
    @Builder(toBuilder = true)
    public static class Info {
        String phone;
        ApiResponseStatus status;
        String statusMessage;
        Double cost;
        Integer messagesCount;
    }
}
