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
public class SendersResponse implements ApiResponse {

    @SerializedName("status_code")  ApiResponseStatus status;
    @SerializedName("senders")      List<String> senders;

    public List<String> getSenders() {
        return new ArrayList<>(senders);
    }
}
