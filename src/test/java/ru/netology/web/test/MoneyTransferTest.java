package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

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
}
