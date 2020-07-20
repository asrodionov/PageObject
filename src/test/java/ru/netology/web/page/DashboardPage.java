package ru.netology.web.page;

import ru.netology.web.data.DataHelper;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cardsInfo = $$(".list__item [data-test-id]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public MoneyTransferPage replenishCard(DataHelper.CardInfo card){
        cardsInfo.find(attribute("data-test-id",card.getTestId())).lastChild().click();
        return new MoneyTransferPage();
    }

    public int getCardBalance(DataHelper.CardInfo card) {
        val text = cardsInfo.find(attribute("data-test-id",card.getTestId())).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
