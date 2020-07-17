package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @Test
    void shouldReplenishFirstCard() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(0);
        int balanceCardSecond = dashboardPage.getCardBalance(1);
        val moneyTransferPage = dashboardPage.replenishCard(0);
        int sum = 5000;
        moneyTransferPage.replenishCardAction(sum,2);
        assertEquals (balanceCardFirst+sum, dashboardPage.getCardBalance(0));
        assertEquals (balanceCardSecond-sum, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldReplenishSecondCard() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(0);
        int balanceCardSecond = dashboardPage.getCardBalance(1);
        val moneyTransferPage = dashboardPage.replenishCard(1);
        int sum = 5000;
        moneyTransferPage.replenishCardAction(sum,1);
        assertEquals (balanceCardFirst-sum, dashboardPage.getCardBalance(0));
        assertEquals (balanceCardSecond+sum, dashboardPage.getCardBalance(1));
    }
    @Test
    void shouldReplenishFirstCardNull() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(0);
        int balanceCardSecond = dashboardPage.getCardBalance(1);
        val moneyTransferPage = dashboardPage.replenishCard(0);
        int sum = 0;
        moneyTransferPage.replenishCardAction(sum,2);
        assertEquals (balanceCardFirst+sum, dashboardPage.getCardBalance(0));
        assertEquals (balanceCardSecond-sum, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldReplenishSecondCardNull() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(0);
        int balanceCardSecond = dashboardPage.getCardBalance(1);
        val moneyTransferPage = dashboardPage.replenishCard(1);
        int sum = 0;
        moneyTransferPage.replenishCardAction(sum,1);
        assertEquals (balanceCardFirst-sum, dashboardPage.getCardBalance(0));
        assertEquals (balanceCardSecond+sum, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldReplenishFirstCardOverLimit() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(0);
        int balanceCardSecond = dashboardPage.getCardBalance(1);
        val moneyTransferPage = dashboardPage.replenishCard(0);
        int sum = 500000;
        moneyTransferPage.replenishCardAction(sum,2);
        assertEquals (balanceCardFirst, dashboardPage.getCardBalance(0));
        assertEquals (balanceCardSecond, dashboardPage.getCardBalance(1));
    }

    @Test
    void shouldReplenishSecondCardOverLimit() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceCardFirst = dashboardPage.getCardBalance(0);
        int balanceCardSecond = dashboardPage.getCardBalance(1);
        val moneyTransferPage = dashboardPage.replenishCard(1);
        int sum = 500000;
        moneyTransferPage.replenishCardAction(sum,1);
        assertEquals (balanceCardFirst, dashboardPage.getCardBalance(0));
        assertEquals (balanceCardSecond, dashboardPage.getCardBalance(1));
    }

}
