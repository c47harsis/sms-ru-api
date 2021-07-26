package ru.c47harsis.smsruapi;

public enum ApiResponseStatus {
    SMS_NOT_FOUND(-1),                  // Message has not been found
    OK(100),                            // Request completed or queued
    IN_PROGRESS(101),                   // Message is sending to mobile network operator
    SENT(102),                          // Message sent but not delivered yet
    DELIVERED(103),                     // Message has been delivered
    DELIVERY_ERROR_TIMEOUT(104),        // Message could not be delivered: lifetime expired
    DELIVERY_ERROR_DELETED(105),        // Message could not be delivered: removed by mobile network operator
    DELIVERY_ERROR_PHONE_FAILURE(106),  // Message could not be delivered: cellphone error
    DELIVERY_ERROR_UNKNOWN(107),        // Message could not be delivered: unknown reason
    DELIVERY_ERROR_REJECTED(108),       // Message could not be delivered: rejected
    MESSAGE_READ(110),                  // Message has been read (Viber)
    DELIVERY_ERROR_ROUTE_NOT_FOUND(150),// Message could not be delivered: route not found
    WRONG_API_ID(200),                  // Invalid "api_id"
    BALANCE_ERROR(201),                 // Insufficient funds
    SENDER_ERROR(202),                  // Invalid receiver's phone number or there is no route to it
    TEXT_ERROR(203),                    // Message has no text
    MODERATION_ERROR(204),              // Sender's name was not approved by administration
    MESSAGE_TOO_LONG(205),              // Message is too long, exceeds 8 messages limit
    DAILY_LIMIT(206),                   // Daily limit for sending messages will be exceeded or already is
    RECEIVER_ERROR(207),                // You can't send message to this number or there are more than 100 receivers
    TIME_ERROR(208),                    // Wrong format of "time" parameter
    BLACK_LIST(209),                    // A number (or at least one of them) was added to your black list
    HTTP_FORMAT_ERROR(210),             // GET HTTP method was used instead of POST
    METHOD_NOT_FOUND(211),              // Method not found
    ENCODING_ERROR(212),                // Message has wrong encoding, should be UTF-8
    MORE_THAN_1000(213),                // More than 1000 numbers specified as recipients
    NUMBER_ABROAD(214),                 // The number is abroad but enabled sending to russian numbers only
    UNAVAILABLE(220),                   // Service is temporary unavailable, try later
    DAILY_PHONE_LIMIT(230),             // Daily limit for sending messages to the number exceeded
    PHONE_TEXT_MINUTE_LIMIT(231),       // Limit of similar messages to the phone in minute exceeded
    PHONE_TEXT_DAILY_LIMIT(232),        // Daily limit of similar messages to the phone exceeded

    FRAUD_REPEATED(233),                // Repeated messages with code sending limit to this number in a short period of
                                        // time has been exceeded ("protection against fraudsters", you can turn it off)

    WRONG_TOKEN(300),                   // Incorrect authentication token, probably expired or your IP has been changed
    WRONG_PASSWORD(301),                // Invalid credentials
    ACCOUNT_NOT_APPROVED(302),          // User is authorized but account has not been confirmed (SMS code confirmation)
    CONFIRMATION_CODE_INVALID(303),     // Confirmation code invalid
    CONFIRMATION_SENDING_LIMIT(304),    // Too many confirmation codes sent, try later
    CONFIRMATION_TRIES_LIMIT(305),      // Too many failed attempts to enter confirmation code, try later
    SERVER_ERROR(500),                  // Server error, retry
    INVALID_CALLBACK(901),              // Invalid callback (starts not with "http://")
    CALLBACK_HANDLER_NOT_FOUND(902)     // Callback handler not found (maybe was deleted earlier)
    ;

    public final int code;

    ApiResponseStatus(int code) {
        this.code = code;
    }

    public static ApiResponseStatus of(int code) {
        for (ApiResponseStatus status : ApiResponseStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }
}
