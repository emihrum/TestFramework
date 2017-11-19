package com.liligo.test.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

@Lazy
@Component
public class SearchResultPage extends AbstractPage {
    @FindBy(className = "flight-offer-summary-table")
    private WebElementFacade sumaryTable;
    @FindBy(className = "flight-offer-summary-table-cell")
    private List<WebElementFacade> sumaryTableColumns;
    @FindBy(className = "flight-alert")
    private WebElementFacade flightAlert;
    @FindBy(className = "flight-offer-summary-next")
    private WebElementFacade summaryTableNext;

    public List<String> getSummaryPricesForOneStop() {
        List<String> oneStopPrices = new ArrayList<String>();
        oneStopPrices.addAll(getVisibleOnePricesExceptTheCheapest());
        while (!summaryTableNext.getAttribute("class").contains("flight-offer-summary-nav-disabled")) {
            summaryTableNext.click();
            oneStopPrices.addAll(getVisibleOnePricesExceptTheCheapest());
        }
        oneStopPrices.removeAll(Arrays.asList(null, ""));
        return oneStopPrices;
    }

    private List<String> getVisibleOnePricesExceptTheCheapest() {
        List<String> prices = new ArrayList<String>();
        for (int i = 2; i < sumaryTableColumns.size(); i++) {
            prices.add(getSummaryTableEntry(1, i).replaceAll("\\D+", ""));
        }
        return prices;
    }

    public String getCheapestOneStopPrice() {
        return getSummaryTableEntry(1, 1).replaceAll("\\D+", "");
    }

    private String getSummaryTableEntry(int row, int column) {
        WebElement summaryTableColumn = sumaryTableColumns.get(column);
        WebElement summaryTableRow = summaryTableColumn.findElements(By.className("flight-offer-summary-table-row"))
                .get(row);
        return summaryTableRow.getText();
    }

    public void closeFlightAlertPopup() {
        this.waitFor(flightAlert);
        getDriver().findElement(By.className("flight-alert-close")).click();
    }
}
