package ru.c47harsis;

import lombok.experimental.UtilityClass;
import ru.c47harsis.smsruapi.request.*;

@UtilityClass
public class Responses {

    //@formatter:off
    @SuppressWarnings("rawtypes")
    public String getSuccess(Class<? extends ApiRequest> type) {
        if (type == BalanceRequest.class) {
            return
                    "{\n" +
                    "    \"status\": \"OK\",\n" +
                    "    \"status_code\": 100,\n" +
                    "    \"balance\": 103.87\n" +
                    "}";
        }
        if (type == BlacklistAddRequest.class) {
            return
                    "{\n" +
                    "    \"status\": \"OK\",\n" +
                    "    \"status_code\": 100\n" +
                    "}";
        }
        if (type == BlacklistAllRequest.class) {
            return
                    "{\n" +
                    "    \"status\": \"OK\",\n" +
                    "    \"status_code\": 100,\n" +
                    "    \"stoplist\": {\n" +
                    "        \"77777777777\": \"banned\",\n" +
                    "        \"79999999999\": \"\"\n" +
                    "    }\n" +
                    "}";
        }
        if (type == BlacklistRemoveRequest.class) {
            return
                    "{\n" +
                    "    \"status\": \"OK\",\n" +
                    "    \"status_code\": 100\n" +
                    "}";
        }
        if (type == CostRequest.class) {
            return
                    "{\n" +
                    "    \"status\": \"OK\",\n" +
                    "    \"status_code\": 100,\n" +
                    "    \"sms\": {\n" +
                    "        \"79636692625\": {\n" +
                    "            \"status\": \"OK\",\n" +
                    "            \"status_code\": 100,\n" +
                    "            \"cost\": 0.67,\n" +
                    "            \"sms\": 1\n" +
                    "        },\n" +
                    "        \"74993221627\": {\n" +
                    "            \"status\": \"ERROR\",\n" +
                    "            \"status_code\": 207," +
                    "            \"status_text\": \"На этот номер (или один из номеров) нельзя отправлять сообщения, " +
                                                   "либо указано более 100 номеров в списке получателей\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"total_cost\": 0.67,\n" +
                    "    \"total_sms\": 1\n" +
                    "}";
        }
        if (type == FreeRequest.class) {
            return
                    "{\n" +
                    "    \"status\": \"OK\",\n" +
                    "    \"status_code\": 100,\n" +
                    "    \"total_free\": 5,\n" +
                    "    \"used_today\": 3\n" +
                    "}";
        }
        if (type == LimitRequest.class) {
            return
                    "{\n" +
                    "    \"status\": \"OK\",\n" +
                    "    \"status_code\": 100,\n" +
                    "    \"total_limit\": 100,\n" +
                    "    \"used_today\": 7\n" +
                    "}";
        }
        if (type == SendersRequest.class) {
            return
                    "{\n" +
                    "    \"status\": \"OK\",\n" +
                    "    \"status_code\": 100,\n" +
                    "    \"senders\": [\n" +
                    "        \"sender1\",\n" +
                    "        \"sender2\"\n" +
                    "    ]\n" +
                    "}";
        }
        if (type == SendRequest.class) {
            return
                    "{\n" +
                    "    \"status\": \"OK\",\n" +
                    "    \"status_code\": 100,\n" +
                    "    \"sms\": {\n" +
                    "        \"79636692625\": {\n" +
                    "            \"status\": \"OK\",\n" +
                    "            \"status_code\": 100,\n" +
                    "            \"sms_id\": \"000000-10000000\"" +
                    "        },\n" +
                    "        \"74993221627\": {\n" +
                    "            \"status\": \"ERROR\",\n" +
                    "            \"status_code\": 207,\n" +
                    "            \"status_text\": \"На этот номер (или один из номеров) нельзя отправлять сообщения, " +
                                                   "либо указано более 100 номеров в списке получателей\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    \"balance\": 4122.56\n" +
                    "}";
        }
        throw new IllegalStateException("Unsupported request type");
    }
}
