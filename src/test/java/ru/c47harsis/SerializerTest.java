package ru.c47harsis;

import org.junit.jupiter.api.Test;
import ru.c47harsis.smsruapi.request.SendRequest;
import ru.c47harsis.smsruapi.serializer.LocalDateTimeSerializer;
import ru.c47harsis.smsruapi.serializer.Param;
import ru.c47harsis.smsruapi.serializer.Serializer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("SpellCheckingInspection")
public class SerializerTest {

    @SuppressWarnings({"FieldMayBeFinal", "unused"})
    public static class Testing {
        @Param(value = "time", serializer = LocalDateTimeSerializer.class)
        private LocalDateTime time;

        public LocalDateTime getTime() {
            return time;
        }

        private Boolean flag;

        @Param(value = "flag") // BooleanSerializer.class will be chosen implicitly
        public Boolean isFlag() {
            return flag;
        }

        private final Integer ignored;
        public Integer getIgnored() {
            return ignored;
        }

        public Testing(LocalDateTime time, Boolean flag, Integer ignored) {
            this.time = time;
            this.flag = flag;
            this.ignored = ignored;
        }
    }

    @Test
    public void paramAnnotationTest() {

        LocalDateTime time = LocalDateTime.now();

        Map<String, String> expected = new HashMap<>();
        expected.put("time", String.valueOf(time.toEpochSecond(ZoneOffset.UTC)));
        expected.put("flag", "1");

        assertEquals(expected, Serializer.serialize(new Testing(time, true, -1)));
    }

    @Test
    public void sendRequestSerializeTest() {
        LocalDateTime time = LocalDateTime.now().plusDays(1);
        Map<String, String> expected = new HashMap<>();
        expected.put("to", "+77777777777");
        expected.put("msg", "Hello dude");
        expected.put("ttl", "10");
        expected.put("test", "1");
        expected.put("daytime", "1");
        expected.put("translit", "0");
        expected.put("time", String.valueOf(time.toEpochSecond(ZoneOffset.UTC)));

        Map<String, String> actual = Serializer.serialize(
                SendRequest.builder()
                        .receiver("+77777777777")
                        .text("Hello dude")
                        .daytimeOnly(true)
                        .ttlMinutes(10)
                        .test(true)
                        .translit(false)
                        .timeToSend(time)
                        .build()
        );

        assertEquals(expected, actual);
    }
}
