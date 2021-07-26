package ru.c47harsis.smsruapi.auth;

import lombok.NonNull;
import lombok.Value;
import ru.c47harsis.smsruapi.serializer.Param;

@Value
public class UsernamePasswordAuthProvider implements AuthProvider {

    @Param("login") @NonNull String username;
    @Param("password") @NonNull String password;
}
