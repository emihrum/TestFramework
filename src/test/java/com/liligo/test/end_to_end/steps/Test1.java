package com.liligo.test.end_to_end.steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.liligo.test.end_to_end.Flight;
import com.liligo.test.end_to_end.FlightDate;
import com.liligo.test.pages.LandingPage;
import com.liligo.test.pages.SearchResultPage;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class Test1 {
    @Autowired
    LandingPage landingPage;
    @Autowired
    SearchResultPage searchResultPage;

    @Given("the user is navigated to the following URL: '(.*)'")
    public void navigate(String url) {
        landingPage.openUrl(url);
    }

    @When("the user initiates a search with the following parameters:")
    public void initiateFlightSearch(DataTable parameters) {
        Map<String, String> flightParameters = parameters.asMap(String.class, String.class);

        String[] depDate = flightParameters.get("departureDate").split(" ");
        FlightDate departureDate = new FlightDate(depDate[0], depDate[1], depDate[2]);

        Flight flight = new Flight();
        flight.setFlightFrom(flightParameters.get("flightFrom"));
        flight.setFlightTo(flightParameters.get("flightTo"));
        flight.setDepartureDate(departureDate);
        flight.setNumberOfAdults(flightParameters.get("numberOfAdults"));
        flight.setAirClass(flightParameters.get("airClass"));
        flight.setFlightType(flightParameters.get("flightType"));

        landingPage.initiateFlightSearch(flight);
    }

    @Then("the cheapest value is displayed in the All results column of the search result table for '(.*)'")
    public void verifySearchResult(String searchResultRow) {
        searchResultPage.closeFlightAlertPopup();

        Integer cheapestPrice = -1;
        List<Integer> sortedPrices = new ArrayList<Integer>();

        if ("1 stop".equals(searchResultRow)) {
            cheapestPrice = Integer.parseInt(searchResultPage.getCheapestOneStopPrice());
            sortedPrices = searchResultPage.getSummaryPricesForOneStop().stream().map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
        } else if ("2 or more stops".equals(searchResultRow)) {
            // TODO
            Assert.fail("Unimplemented case!");
        } else {
            throw new RuntimeException("Unexpected summary table row: " + searchResultRow);
        }

        Assert.assertEquals(Integer.valueOf(cheapestPrice), Integer.valueOf(sortedPrices.get(0)));
    }
}
