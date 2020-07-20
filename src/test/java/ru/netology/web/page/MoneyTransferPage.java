
package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement buttonActionTransfer = $("[data-test-id=action-transfer]");

    public MoneyTransferPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage replenishCardAction(int sum, int card){
        amount.setValue(String.valueOf(sum));
        from.setValue(DataHelper.getCardInfo(card).getNumber());
        buttonActionTransfer.click();
        return new DashboardPage();
    }
}
