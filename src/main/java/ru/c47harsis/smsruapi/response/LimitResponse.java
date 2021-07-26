package ru.c47harsis.smsruapi.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import ru.c47harsis.smsruapi.ApiResponseStatus;

@Value
@AllArgsConstructor
@Builder(toBuilder = true)
public class LimitResponse implements ApiResponse {

    @SerializedName("status_code")  ApiResponseStatus status;
    @SerializedName("total_limit")  Integer totalLimit;
    @SerializedName("used_today")   Integer usedToday;
}
