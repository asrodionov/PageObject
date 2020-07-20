package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CardInfo {
        private int idCard;
        private String number;
        private String testId;
    }

    public static CardInfo getCardInfo(int id) {
        if (id == 1) return new CardInfo(1, "5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
        if (id == 2) return new CardInfo(2, "5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        return new CardInfo();
    }

}
