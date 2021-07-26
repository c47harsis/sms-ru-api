package ru.c47harsis;


import lombok.SneakyThrows;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.c47harsis.smsruapi.ApiResponseStatus;
import ru.c47harsis.smsruapi.SmsRuClient;
import ru.c47harsis.smsruapi.auth.ApiTokenAuthProvider;
import ru.c47harsis.smsruapi.request.*;
import ru.c47harsis.smsruapi.response.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


public class ClientTest {

    private SmsRuClient client;

    @Test
    public void testBalanceResponse() {
        checkResponse(new BalanceRequest(), new BalanceResponse(ApiResponseStatus.OK, 103.87));
    }

    @Test
    public void testBlacklistAddResponse() {
        checkResponse(new BlacklistAddRequest("+77777777777"), new BlacklistAddResponse(ApiResponseStatus.OK));
    }

    @Test
    public void testBlacklistAllResponse() {
        List<BlacklistAllResponse.Info> blackListInfo = new ArrayList<>();
        blackListInfo.add(BlacklistAllResponse.Info.builder().phone("77777777777").reason("banned").build());
        blackListInfo.add(BlacklistAllResponse.Info.builder().phone("79999999999").build());
        checkResponse(new BlacklistAllRequest(), new BlacklistAllResponse(ApiResponseStatus.OK, blackListInfo));
    }

    @Test
    public void testBlacklistRemoveResponse() {
        checkResponse(new BlacklistRemoveRequest("77777777777"), new BlacklistRemoveResponse(ApiResponseStatus.OK));
    }

    @Test
    public void testCostResponse() {
        List<CostResponse.Info> costInfo = new ArrayList<>();
        costInfo.add(CostResponse.Info.builder()
                .phone("79636692625")
                .status(ApiResponseStatus.OK)
                .cost(0.67)
                .messagesCount(1)
                .build());
        costInfo.add(CostResponse.Info.builder()
                .phone("74993221627")
                .status(ApiResponseStatus.RECEIVER_ERROR)
                .statusMessage(
                        "На этот номер (или один из номеров) нельзя отправлять сообщения, " +
                                "либо указано более 100 номеров в списке получателей"
                ).build());
        checkResponse(new CostRequest("77777777777", "hello"),
                new CostResponse(ApiResponseStatus.OK, 0.67, 1, null, costInfo));
    }

    @Test
    public void testFreeResponse() {
        checkResponse(new FreeRequest(), new FreeResponse(ApiResponseStatus.OK, 5, 3));
    }

    @Test
    public void testLimitResponse() {
        checkResponse(new LimitRequest(), new LimitResponse(ApiResponseStatus.OK, 100, 7));
    }

    @Test
    public void testSendersResponse() {
        List<String> senders = new ArrayList<>();
        senders.add("sender1");
        senders.add("sender2");
        checkResponse(new SendersRequest(), new SendersResponse(ApiResponseStatus.OK, senders));
    }

    @Test
    public void testSendResponse() {
        List<SendResponse.Info> sendInfo = new ArrayList<>();
        sendInfo.add(SendResponse.Info.builder()
                .phone("79636692625")
                .status(ApiResponseStatus.OK)
                .messageId("000000-10000000")
                .build());
        sendInfo.add(SendResponse.Info.builder()
                .phone("74993221627")
                .status(ApiResponseStatus.RECEIVER_ERROR)
                .statusMessage(
                        "На этот номер (или один из номеров) нельзя отправлять сообщения, " +
                                "либо указано более 100 номеров в списке получателей"
                )
                .build());
        checkResponse(new SendRequest("+799999999999", "Hello"),
                new SendResponse(ApiResponseStatus.OK, 4122.56, sendInfo));
    }

    @SneakyThrows
    private SmsRuClient createTestClient(OkHttpClient httpClient) {
        return SmsRuClient.builder()
                .authProvider(new ApiTokenAuthProvider("token"))
                .httpClient(httpClient)
                .build();
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    private void prepareFor(Class<? extends ApiRequest> type) {
        Call httpCall = Mockito.mock(Call.class);
        Mockito.doReturn(
                new Response.Builder()
                        .code(200)
                        .request(new Request.Builder().url("http://dummy.api.url").build())
                        .protocol(Protocol.HTTP_1_1)
                        .message("")
                        .body(ResponseBody.create(Responses.getSuccess(type), MediaType.get("application/json")))
                        .build()
        ).when(httpCall).execute();
        OkHttpClient httpClient = Mockito.mock(OkHttpClient.class);
        Mockito.when(httpClient.newCall(any(Request.class))).thenReturn(httpCall);
        client = createTestClient(httpClient);
    }

    private void checkResponse(ApiRequest<?> request, ApiResponse expected) {
        prepareFor(request.getClass());
        assertEquals(expected, client.send(request));
    }
}
