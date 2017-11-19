package com.liligo.test.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.liligo.test.end_to_end.Flight;
import com.liligo.test.end_to_end.FlightDate;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

@Lazy
@Component
public class LandingPage extends AbstractPage {
    @FindBy(className = "hp-searchform-radio-nav")
    private WebElementFacade flightTypeSelector;
    @FindBy(id = "air-from")
    private WebElementFacade flightSource;
    @FindBy(id = "air-to")
    private WebElementFacade flightDestination;
    @FindBy(id = "air-out-date")
    private WebElementFacade departureDate;
    @FindBy(id = "air-ret-date")
    private WebElementFacade returnDate;
    @FindBy(id = "air-adults")
    private WebElementFacade numberOfAdultsDropdown;
    @FindBy(id = "air-class")
    private WebElementFacade airClassDropdown;
    @FindBy(id = "air-submit")
    private WebElementFacade flightSearchButton;

    @Autowired
    DatePickerWidget datePickerWidget;

    public void initiateFlightSearch(Flight flight) {
        setFlightType(flight.getFlightType());
        fillInFlightSource(flight.getFlightFrom());
        fillInFlightDestination(flight.getFlightTo());
        setDepartureDate(flight.getDepartureDate());
        setNumberOfAdults(flight.getNumberOfAdults());
        setAirClass(flight.getAirClass());
        clickOnFlightSearchButton();
    }

    public void setFlightType(String flightTypeToSet) {
        flightTypeSelector.findElement(By.xpath("//li[contains(., '" + flightTypeToSet + "')]")).click();
    }

    public void fillInFlightSource(String sourceCityOrCountry) {
        typeInto(flightSource, sourceCityOrCountry);
    }

    public void fillInFlightDestination(String destinationCityOrCountry) {
        typeInto(flightDestination, destinationCityOrCountry);
    }

    public void setDepartureDate(FlightDate flightDate) {
        departureDate.click();
        datePickerWidget.selectDate(flightDate);
    }

    public void setNumberOfAdults(String numberOfAdultsToSet) {
        numberOfAdultsDropdown.click();
        numberOfAdultsDropdown.findElement(By.xpath("//li[contains(., '" + numberOfAdultsToSet + "')]")).click();
    }

    public void setAirClass(String airClassToSet) {
        airClassDropdown.click();
        airClassDropdown.findElement(By.xpath("//li[contains(., '" + airClassToSet + "')]")).click();
    }

    public void clickOnFlightSearchButton() {
        flightSearchButton.click();
    }
}
