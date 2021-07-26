package ru.c47harsis.smsruapi.auth;

import lombok.NonNull;
import lombok.Value;
import ru.c47harsis.smsruapi.serializer.Param;

@Value
public class ApiTokenAuthProvider implements AuthProvider {

    @Param("api_id") @NonNull String token;
}