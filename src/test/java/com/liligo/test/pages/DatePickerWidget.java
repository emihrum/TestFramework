package com.liligo.test.pages;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.liligo.test.end_to_end.FlightDate;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

@Lazy
@Component
public class DatePickerWidget extends AbstractPage {
    @FindBy(className = "datepicker")
    private WebElementFacade datePickerWidget;
    @FindBy(className = "dpMonth1")
    private WebElementFacade leftCalendar;
    @FindBy(className = "dpPrev")
    private WebElementFacade calendarLeftArrow;
    @FindBy(className = "dpNext")
    private WebElementFacade calendarRightArrow;

    public void selectDate(FlightDate flightDate) {
        String leftCalendarDate = leftCalendar.findElement(By.className("dpMonthHeader")).getText();
        String yearMonthToSet = flightDate.getMonth() + ", " + flightDate.getYear();
        int monthDifference = (int) getMonthDifference(leftCalendarDate, yearMonthToSet);

        String dayToSet = flightDate.getDay();

        if (monthDifference < 0) {
            clickOnCalendarLeftArrow(Math.abs(monthDifference));
        } else if (monthDifference > 0) {
            clickOnCalendarRightArrow(monthDifference);
        }

        leftCalendar.findElement(By.xpath("//td/a[text()='" + dayToSet + "']")).click();
    }

    public void clickOnCalendarLeftArrow(int numberOfClicks) {
        for (int i = 0; i < numberOfClicks; i++) {
            calendarLeftArrow.click();
        }
    }

    public void clickOnCalendarRightArrow(int numberOfClicks) {
        for (int i = 0; i < numberOfClicks; i++) {
            calendarRightArrow.click();
        }
    }

    private long getMonthDifference(String date1, String date2) {
        DateTimeFormatter monthYearFormat = DateTimeFormatter.ofPattern("MMMM, uuuu");
        YearMonth monthYear1 = YearMonth.parse(date1, monthYearFormat);
        YearMonth monthYear2 = YearMonth.parse(date2, monthYearFormat);
        return monthYear1.until(monthYear2, ChronoUnit.MONTHS);
    }
}
