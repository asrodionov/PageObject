package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardNumber {
        int idCard;
        private String number;
    }

    public static CardNumber getCardNumber(int id) {
        if (id==1){
            return new CardNumber(1,"5559 0000 0000 0001");
        } else if (id==2){
            return new CardNumber(2,"5559 0000 0000 0002");
        }
        return null;
    }


}
