package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static ru.netology.web.data.DataHelper.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @Test
    void shouldReplenishFirstCard() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(getCardInfo(1));
        int balanceCardSecond = dashboardPage.getCardBalance(getCardInfo(2));
        val moneyTransferPage = dashboardPage.replenishCard(getCardInfo(1));
        int sum = 5000;
        moneyTransferPage.replenishCardAction(sum,2);
        assertEquals (balanceCardFirst+sum, dashboardPage.getCardBalance(getCardInfo(1)));
        assertEquals (balanceCardSecond-sum, dashboardPage.getCardBalance(getCardInfo(2)));
    }

    @Test
    void shouldReplenishSecondCard() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(getCardInfo(1));
        int balanceCardSecond = dashboardPage.getCardBalance(getCardInfo(2));
        val moneyTransferPage = dashboardPage.replenishCard(getCardInfo(2));
        int sum = 5000;
        moneyTransferPage.replenishCardAction(sum,1);
        assertEquals (balanceCardFirst-sum, dashboardPage.getCardBalance(getCardInfo(1)));
        assertEquals (balanceCardSecond+sum, dashboardPage.getCardBalance(getCardInfo(2)));
    }
    @Test
    void shouldReplenishFirstCardNull() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(getCardInfo(1));
        int balanceCardSecond = dashboardPage.getCardBalance(getCardInfo(2));
        val moneyTransferPage = dashboardPage.replenishCard(getCardInfo(1));
        int sum = 0;
        moneyTransferPage.replenishCardAction(sum,2);
        assertEquals (balanceCardFirst+sum, dashboardPage.getCardBalance(getCardInfo(1)));
        assertEquals (balanceCardSecond-sum, dashboardPage.getCardBalance(getCardInfo(2)));
    }

    @Test
    void shouldReplenishSecondCardNull() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(getCardInfo(1));
        int balanceCardSecond = dashboardPage.getCardBalance(getCardInfo(2));
        val moneyTransferPage = dashboardPage.replenishCard(getCardInfo(2));
        int sum = 0;
        moneyTransferPage.replenishCardAction(sum,1);
        assertEquals (balanceCardFirst-sum, dashboardPage.getCardBalance(getCardInfo(1)));
        assertEquals (balanceCardSecond+sum, dashboardPage.getCardBalance(getCardInfo(2)));
    }

    @Test
    void shouldReplenishFirstCardOverLimit() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(getCardInfo(1));
        int balanceCardSecond = dashboardPage.getCardBalance(getCardInfo(2));
        val moneyTransferPage = dashboardPage.replenishCard(getCardInfo(1));
        int sum = 500000;
        moneyTransferPage.replenishCardAction(sum,2);
        assertEquals (balanceCardFirst, dashboardPage.getCardBalance(getCardInfo(1)));
        assertEquals (balanceCardSecond, dashboardPage.getCardBalance(getCardInfo(2)));
    }

    @Test
    void shouldReplenishSecondCardOverLimit() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(getCardInfo(1));
        int balanceCardSecond = dashboardPage.getCardBalance(getCardInfo(2));
        val moneyTransferPage = dashboardPage.replenishCard(getCardInfo(2));
        int sum = 500000;
        moneyTransferPage.replenishCardAction(sum,1);
        assertEquals (balanceCardFirst, dashboardPage.getCardBalance(getCardInfo(1)));
        assertEquals (balanceCardSecond, dashboardPage.getCardBalance(getCardInfo(2)));
    }

}
