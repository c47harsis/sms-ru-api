package ru.c47harsis.smsruapi.request;

import lombok.*;
import ru.c47harsis.smsruapi.serializer.Param;
import ru.c47harsis.smsruapi.response.SendResponse;

import java.time.LocalDateTime;

// In accordance with https://sms.ru/api/send
@Value
@AllArgsConstructor
@Builder(toBuilder = true)
@SuppressWarnings("SpellCheckingInspection")
public class SendRequest implements ApiRequest<SendResponse> {

    @Param("to")  @NonNull  String receiver;
    @Param("msg") @NonNull  String text;
    @Param("from")          String senderName;
    @Param("ip")            String receiverIp;
    @Param("time")          LocalDateTime timeToSend;
    @Param("ttl")           Integer ttlMinutes; // from 1 to 1440
    @Param("daytime")       Boolean daytimeOnly;
    @Param("translit")      Boolean translit;
    @Param("test")          Boolean test;
    @Param("partner_id")    String partnerId;

    public SendRequest(@NonNull String receiver, @NonNull String text) {
        this(receiver, text, null, null, null, null, null, null, null, null);
    }

    @Override
    public String getMethod() {
        return "/sms/send";
    }

    @Override
    public Class<? extends SendResponse> getResponseClass() {
        return SendResponse.class;
    }
}
