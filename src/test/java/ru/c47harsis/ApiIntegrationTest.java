package ru.c47harsis;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.c47harsis.smsruapi.ApiResponseStatus;
import ru.c47harsis.smsruapi.SmsRuClient;
import ru.c47harsis.smsruapi.auth.ApiTokenAuthProvider;
import ru.c47harsis.smsruapi.request.*;
import ru.c47harsis.smsruapi.response.*;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class ApiIntegrationTest {

    private final Properties props = initProperties("test.properties");
    private final String token = getProperty("TOKEN");
    private final SmsRuClient client = SmsRuClient.builder()
            .authProvider(new ApiTokenAuthProvider(token))
            .build();

    @SneakyThrows
    @SuppressWarnings("SameParameterValue")
    private Properties initProperties(String filename) {
        Properties props = new Properties();
        props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename));
        return props;
    }

    @SuppressWarnings("SameParameterValue")
    private String getProperty(String name) {
        return props.getProperty(name, System.getenv(name));
    }

    @Test
    public void balanceRequestTest() {
        BalanceResponse result = client.send(new BalanceRequest());
        assertEquals(ApiResponseStatus.OK, result.getStatus());
        assertNotNull(result.getBalance());
    }

    private void blacklistAddTest() {
        BlacklistAddResponse result = client.send(
                BlacklistAddRequest.builder().phone("77777777777").reason("dummy").build()
        );
        assertEquals(ApiResponseStatus.OK, result.getStatus());
        result = client.send(
                BlacklistAddRequest.builder().phone("+79999999999").build()
        );
        assertEquals(ApiResponseStatus.OK, result.getStatus());
    }

    private void blacklistRemoveTest() {
        BlacklistRemoveResponse result = client.send(
                BlacklistRemoveRequest.builder().phone("+77777777777").build()
        );
        assertEquals(ApiResponseStatus.OK, result.getStatus());
        result = client.send(
                BlacklistRemoveRequest.builder().phone("79999999999").build()
        );
        assertEquals(ApiResponseStatus.OK, result.getStatus());
    }

    @Test
    @Disabled("Bug at the sms.ru side, bug report created")
    public void blacklistTest() {
        blacklistAddTest();
        BlacklistAllResponse result = client.send(new BlacklistAllRequest());

        BlacklistAllResponse.Info info1 = BlacklistAllResponse.Info.builder()
                .phone("77777777777").reason("dummy")
                .build();

        BlacklistAllResponse.Info info2 = BlacklistAllResponse.Info.builder()
                .phone("79999999999")
                .build();

        assertEquals(ApiResponseStatus.OK, result.getStatus());
        assertTrue(result.getList().contains(info1));
        assertTrue(result.getList().contains(info2));
        blacklistRemoveTest();
        result = client.send(new BlacklistAllRequest());
        assertEquals(ApiResponseStatus.OK, result.getStatus());
        assertFalse(result.getList().contains(info1));
        assertFalse(result.getList().contains(info2));
    }

    @Test
    public void costTest() {
        CostResponse result = client.send(
                CostRequest.builder().receiver("+79999999999").text("Hello").build()
        );
        assertEquals(ApiResponseStatus.OK, result.getStatus());
        assertNotNull(result.getTotalCost());
        assertEquals(1, result.getInfo().size());

        CostResponse.Info info = result.getInfo().get(0);
        assertTrue(phonesEqual("+79999999999", info.getPhone()));
        assertEquals(ApiResponseStatus.OK, info.getStatus());
        assertNotNull(info.getCost());
        assertEquals(1, (int) info.getMessagesCount());
    }

    @Test
    public void freeTest() {
        FreeResponse result = client.send(new FreeRequest());
        assertEquals(ApiResponseStatus.OK, result.getStatus());
        assertNotNull(result.getTotalFree());
    }

    @Test
    public void limitTest() {
        LimitResponse result = client.send(new LimitRequest());
        assertEquals(ApiResponseStatus.OK, result.getStatus());
        assertNotNull(result.getTotalLimit());
    }

    @Test
    public void sendersTest() {
        SendersResponse result = client.send(new SendersRequest());
        assertEquals(ApiResponseStatus.OK, result.getStatus());
    }

    @Test
    public void sendTest() {
        SendResponse result = client.send(SendRequest.builder()
                .test(true)
                .receiver("+77777777777")
                .text("Hello")
                .build()
        );
        assertEquals(ApiResponseStatus.OK, result.getStatus());
        assertNotNull(result.getBalance());
        assertEquals(1, result.getInfo().size());
        SendResponse.Info info = result.getInfo().get(0);
        assertEquals(ApiResponseStatus.OK, info.getStatus());
        assertTrue(phonesEqual("+77777777777", info.getPhone()));
        assertNotNull(info.getMessageId());
    }

    private static boolean phonesEqual(String first, String second) {
        return first.replaceAll("/+", "").equals(second.replaceAll("/+", ""));
    }
}
