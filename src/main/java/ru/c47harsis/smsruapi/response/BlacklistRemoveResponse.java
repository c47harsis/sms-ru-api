package ru.c47harsis.smsruapi.response;

import com.google.gson.annotations.SerializedName;
import lombok.Value;
import ru.c47harsis.smsruapi.ApiResponseStatus;

@Value
public class BlacklistRemoveResponse implements ApiResponse {

    @SerializedName("status_code") ApiResponseStatus status;
}
